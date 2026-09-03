package com.velocity.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

public class JsonUtils {

    // --- JSON SERIALIZATION ---

    public static String toJson(Object obj) {
        if (obj == null) return "null";
        StringBuilder sb = new StringBuilder();
        serialize(obj, sb);
        return sb.toString();
    }

    private static void serialize(Object obj, StringBuilder sb) {
        if (obj == null) {
            sb.append("null");
        } else if (obj instanceof String) {
            sb.append("\"").append(escapeString((String) obj)).append("\"");
        } else if (obj instanceof Number || obj instanceof Boolean) {
            sb.append(obj.toString());
        } else if (obj instanceof Enum<?>) {
            sb.append("\"").append(((Enum<?>) obj).name()).append("\"");
        } else if (obj instanceof Map<?, ?>) {
            Map<?, ?> map = (Map<?, ?>) obj;
            sb.append("{");
            boolean first = true;
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                if (!first) sb.append(",");
                first = false;
                sb.append("\"").append(escapeString(String.valueOf(entry.getKey()))).append("\":");
                serialize(entry.getValue(), sb);
            }
            sb.append("}");
        } else if (obj instanceof Iterable<?>) {
            Iterable<?> iter = (Iterable<?>) obj;
            sb.append("[");
            boolean first = true;
            for (Object item : iter) {
                if (!first) sb.append(",");
                first = false;
                serialize(item, sb);
            }
            sb.append("]");
        } else if (obj.getClass().isArray()) {
            Object[] arr = (Object[]) obj;
            sb.append("[");
            for (int i = 0; i < arr.length; i++) {
                if (i > 0) sb.append(",");
                serialize(arr[i], sb);
            }
            sb.append("]");
        } else {
            // POJO via reflection
            sb.append("{");
            boolean first = true;
            Class<?> clazz = obj.getClass();
            List<Field> fields = getAllFields(clazz);
            for (Field field : fields) {
                if (Modifier.isStatic(field.getModifiers()) || Modifier.isTransient(field.getModifiers())) {
                    continue;
                }
                field.setAccessible(true);
                try {
                    Object val = field.get(obj);
                    if (!first) sb.append(",");
                    first = false;
                    sb.append("\"").append(field.getName()).append("\":");
                    serialize(val, sb);
                } catch (IllegalAccessException ignored) {}
            }
            sb.append("}");
        }
    }

    private static List<Field> getAllFields(Class<?> type) {
        List<Field> fields = new ArrayList<>();
        for (Class<?> c = type; c != null && c != Object.class; c = c.getSuperclass()) {
            fields.addAll(Arrays.asList(c.getDeclaredFields()));
        }
        return fields;
    }

    private static String escapeString(String s) {
        if (s == null) return "";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (c) {
                case '"': sb.append("\\\""); break;
                case '\\': sb.append("\\\\"); break;
                case '\b': sb.append("\\b"); break;
                case '\f': sb.append("\\f"); break;
                case '\n': sb.append("\\n"); break;
                case '\r': sb.append("\\r"); break;
                case '\t': sb.append("\\t"); break;
                default:
                    if (c < ' ') {
                        String hex = Integer.toHexString(c);
                        sb.append("\\u");
                        for (int k = 0; k < 4 - hex.length(); k++) sb.append('0');
                        sb.append(hex);
                    } else {
                        sb.append(c);
                    }
            }
        }
        return sb.toString();
    }

    // --- JSON PARSING ---

    public static Object parse(String json) {
        if (json == null || json.trim().isEmpty()) return null;
        JsonParser parser = new JsonParser(json.trim());
        return parser.parseValue();
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Object> parseObject(String json) {
        Object res = parse(json);
        if (res instanceof Map) {
            return (Map<String, Object>) res;
        }
        return new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    public static List<Object> parseList(String json) {
        Object res = parse(json);
        if (res instanceof List) {
            return (List<Object>) res;
        }
        return new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromJson(String json, Class<T> clazz) {
        if (json == null || json.trim().isEmpty()) return null;
        Object parsed = parse(json);
        if (parsed instanceof Map) {
            return fromMap((Map<String, Object>) parsed, clazz);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static <T> T fromMap(Map<String, Object> map, Class<T> clazz) {
        if (map == null) return null;
        try {
            T instance = clazz.getDeclaredConstructor().newInstance();
            List<Field> fields = getAllFields(clazz);
            for (Field field : fields) {
                if (Modifier.isStatic(field.getModifiers()) || Modifier.isTransient(field.getModifiers())) {
                    continue;
                }
                String name = field.getName();
                if (map.containsKey(name)) {
                    Object val = map.get(name);
                    field.setAccessible(true);
                    setFieldValue(instance, field, val);
                }
            }
            return instance;
        } catch (Exception e) {
            return null;
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void setFieldValue(Object instance, Field field, Object val) throws IllegalAccessException {
        if (val == null) {
            field.set(instance, null);
            return;
        }
        Class<?> targetType = field.getType();
        if (targetType == String.class) {
            field.set(instance, String.valueOf(val));
        } else if (targetType == int.class || targetType == Integer.class) {
            if (val instanceof Number) field.set(instance, ((Number) val).intValue());
            else field.set(instance, Integer.parseInt(val.toString()));
        } else if (targetType == long.class || targetType == Long.class) {
            if (val instanceof Number) field.set(instance, ((Number) val).longValue());
            else field.set(instance, Long.parseLong(val.toString()));
        } else if (targetType == double.class || targetType == Double.class) {
            if (val instanceof Number) field.set(instance, ((Number) val).doubleValue());
            else field.set(instance, Double.parseDouble(val.toString()));
        } else if (targetType == boolean.class || targetType == Boolean.class) {
            if (val instanceof Boolean) field.set(instance, val);
            else field.set(instance, Boolean.parseBoolean(val.toString()));
        } else if (List.class.isAssignableFrom(targetType) && val instanceof List) {
            List<Object> srcList = (List<Object>) val;
            List dstList = new ArrayList<>();
            Type genericType = field.getGenericType();
            Class<?> itemClass = Object.class;
            if (genericType instanceof ParameterizedType) {
                Type[] args = ((ParameterizedType) genericType).getActualTypeArguments();
                if (args.length > 0 && args[0] instanceof Class) {
                    itemClass = (Class<?>) args[0];
                }
            }
            for (Object item : srcList) {
                if (item instanceof Map && itemClass != Object.class && itemClass != Map.class) {
                    dstList.add(fromMap((Map<String, Object>) item, itemClass));
                } else if (itemClass == String.class) {
                    dstList.add(String.valueOf(item));
                } else {
                    dstList.add(item);
                }
            }
            field.set(instance, dstList);
        } else if (val instanceof Map) {
            Object subInstance = fromMap((Map<String, Object>) val, targetType);
            field.set(instance, subInstance);
        } else {
            field.set(instance, val);
        }
    }

    private static class JsonParser {
        private final String src;
        private int pos = 0;

        public JsonParser(String src) {
            this.src = src;
        }

        public Object parseValue() {
            skipWhitespace();
            if (pos >= src.length()) return null;
            char c = src.charAt(pos);
            if (c == '{') return parseMap();
            if (c == '[') return parseArray();
            if (c == '"' || c == '\'') return parseString();
            if (c == 't' || c == 'f') return parseBoolean();
            if (c == 'n') return parseNull();
            if (Character.isDigit(c) || c == '-') return parseNumber();
            throw new RuntimeException("Unexpected character at " + pos + ": " + c);
        }

        private Map<String, Object> parseMap() {
            Map<String, Object> map = new LinkedHashMap<>();
            pos++; // skip '{'
            skipWhitespace();
            if (pos < src.length() && src.charAt(pos) == '}') {
                pos++;
                return map;
            }

            while (pos < src.length()) {
                skipWhitespace();
                String key = parseString();
                skipWhitespace();
                if (pos >= src.length() || src.charAt(pos) != ':') {
                    throw new RuntimeException("Expected ':' at " + pos);
                }
                pos++; // skip ':'
                skipWhitespace();
                Object value = parseValue();
                map.put(key, value);

                skipWhitespace();
                if (pos < src.length() && src.charAt(pos) == ',') {
                    pos++;
                } else if (pos < src.length() && src.charAt(pos) == '}') {
                    pos++;
                    break;
                } else {
                    break;
                }
            }
            return map;
        }

        private List<Object> parseArray() {
            List<Object> list = new ArrayList<>();
            pos++; // skip '['
            skipWhitespace();
            if (pos < src.length() && src.charAt(pos) == ']') {
                pos++;
                return list;
            }

            while (pos < src.length()) {
                skipWhitespace();
                list.add(parseValue());
                skipWhitespace();
                if (pos < src.length() && src.charAt(pos) == ',') {
                    pos++;
                } else if (pos < src.length() && src.charAt(pos) == ']') {
                    pos++;
                    break;
                } else {
                    break;
                }
            }
            return list;
        }

        private String parseString() {
            char quote = src.charAt(pos);
            pos++; // skip quote
            StringBuilder sb = new StringBuilder();
            while (pos < src.length()) {
                char c = src.charAt(pos++);
                if (c == quote) return sb.toString();
                if (c == '\\' && pos < src.length()) {
                    char next = src.charAt(pos++);
                    switch (next) {
                        case '"': sb.append('"'); break;
                        case '\\': sb.append('\\'); break;
                        case '/': sb.append('/'); break;
                        case 'b': sb.append('\b'); break;
                        case 'f': sb.append('\f'); break;
                        case 'n': sb.append('\n'); break;
                        case 'r': sb.append('\r'); break;
                        case 't': sb.append('\t'); break;
                        case 'u':
                            if (pos + 4 <= src.length()) {
                                String hex = src.substring(pos, pos + 4);
                                sb.append((char) Integer.parseInt(hex, 16));
                                pos += 4;
                            }
                            break;
                        default: sb.append(next);
                    }
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }

        private Boolean parseBoolean() {
            if (src.startsWith("true", pos)) {
                pos += 4;
                return Boolean.TRUE;
            }
            if (src.startsWith("false", pos)) {
                pos += 5;
                return Boolean.FALSE;
            }
            throw new RuntimeException("Invalid boolean at " + pos);
        }

        private Object parseNull() {
            if (src.startsWith("null", pos)) {
                pos += 4;
                return null;
            }
            throw new RuntimeException("Invalid null at " + pos);
        }

        private Number parseNumber() {
            int start = pos;
            if (src.charAt(pos) == '-') pos++;
            while (pos < src.length() && Character.isDigit(src.charAt(pos))) pos++;
            boolean isFloat = false;
            if (pos < src.length() && src.charAt(pos) == '.') {
                isFloat = true;
                pos++;
                while (pos < src.length() && Character.isDigit(src.charAt(pos))) pos++;
            }
            if (pos < src.length() && (src.charAt(pos) == 'e' || src.charAt(pos) == 'E')) {
                isFloat = true;
                pos++;
                if (pos < src.length() && (src.charAt(pos) == '+' || src.charAt(pos) == '-')) pos++;
                while (pos < src.length() && Character.isDigit(src.charAt(pos))) pos++;
            }
            String numStr = src.substring(start, pos);
            if (isFloat) {
                return Double.parseDouble(numStr);
            }
            try {
                return Long.parseLong(numStr);
            } catch (NumberFormatException e) {
                return Double.parseDouble(numStr);
            }
        }

        private void skipWhitespace() {
            while (pos < src.length() && Character.isWhitespace(src.charAt(pos))) {
                pos++;
            }
        }
    }
}
