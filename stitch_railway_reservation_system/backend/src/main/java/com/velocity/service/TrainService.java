package com.velocity.service;

import com.velocity.model.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class TrainService {
    private final Map<String, Station> stations = new ConcurrentHashMap<>();
    private final Map<String, Train> trains = new ConcurrentHashMap<>();
    private final List<RecentSearch> recentSearches = new ArrayList<>();

    public TrainService() {
        initStations();
        initTrains();
        initRecentSearches();
    }

    private void initStations() {
        // Northern Zone & NCR
        addStation(new Station("NDLS", "New Delhi Railway Station", "New Delhi", "Delhi", "NR", Arrays.asList("Platform 1", "Platform 2", "Platform 3", "Platform 16")));
        addStation(new Station("NZM", "Hazrat Nizamuddin", "New Delhi", "Delhi", "NR", Arrays.asList("Platform 1", "Platform 2", "Platform 3", "Platform 7")));
        addStation(new Station("ANVT", "Anand Vihar Terminal", "New Delhi", "Delhi", "NR", Arrays.asList("Platform 1", "Platform 2", "Platform 5")));
        addStation(new Station("DLI", "Old Delhi Junction", "Delhi", "Delhi", "NR", Arrays.asList("Platform 1", "Platform 3", "Platform 14")));
        addStation(new Station("BSB", "Varanasi Junction", "Varanasi", "Uttar Pradesh", "NR", Arrays.asList("Platform 1", "Platform 8", "Platform 9")));
        addStation(new Station("LKO", "Lucknow Charbagh", "Lucknow", "Uttar Pradesh", "NR", Arrays.asList("Platform 1", "Platform 2", "Platform 6")));
        addStation(new Station("CNB", "Kanpur Central", "Kanpur", "Uttar Pradesh", "NCR", Arrays.asList("Platform 1", "Platform 4", "Platform 9")));
        addStation(new Station("PRYJ", "Prayagraj Junction", "Prayagraj", "Uttar Pradesh", "NCR", Arrays.asList("Platform 1", "Platform 2", "Platform 6")));
        addStation(new Station("AGC", "Agra Cantt", "Agra", "Uttar Pradesh", "NCR", Arrays.asList("Platform 1", "Platform 2", "Platform 3")));
        addStation(new Station("GKP", "Gorakhpur Junction", "Gorakhpur", "Uttar Pradesh", "NER", Arrays.asList("Platform 1", "Platform 2")));
        addStation(new Station("MTC", "Meerut City", "Meerut", "Uttar Pradesh", "NR", Arrays.asList("Platform 1", "Platform 2")));
        addStation(new Station("BE", "Bareilly Junction", "Bareilly", "Uttar Pradesh", "NR", Arrays.asList("Platform 1", "Platform 2")));

        // North Western & Northern
        addStation(new Station("JP", "Jaipur Junction", "Jaipur", "Rajasthan", "NWR", Arrays.asList("Platform 1", "Platform 2", "Platform 3")));
        addStation(new Station("AII", "Ajmer Junction", "Ajmer", "Rajasthan", "NWR", Arrays.asList("Platform 1", "Platform 2")));
        addStation(new Station("JU", "Jodhpur Junction", "Jodhpur", "Rajasthan", "NWR", Arrays.asList("Platform 1", "Platform 2")));
        addStation(new Station("UDZ", "Udaipur City", "Udaipur", "Rajasthan", "NWR", Arrays.asList("Platform 1", "Platform 2")));
        addStation(new Station("KOTA", "Kota Junction", "Kota", "Rajasthan", "WCR", Arrays.asList("Platform 1", "Platform 2", "Platform 3")));
        addStation(new Station("ASR", "Amritsar Junction", "Amritsar", "Punjab", "NR", Arrays.asList("Platform 1", "Platform 2", "Platform 4")));
        addStation(new Station("LDH", "Ludhiana Junction", "Ludhiana", "Punjab", "NR", Arrays.asList("Platform 1", "Platform 2")));
        addStation(new Station("CDG", "Chandigarh Junction", "Chandigarh", "Chandigarh", "NR", Arrays.asList("Platform 1", "Platform 2", "Platform 3")));
        addStation(new Station("SVDK", "Shri Mata Vaishno Devi Katra", "Katra", "Jammu & Kashmir", "NR", Arrays.asList("Platform 1", "Platform 2")));
        addStation(new Station("JAT", "Jammu Tawi", "Jammu", "Jammu & Kashmir", "NR", Arrays.asList("Platform 1", "Platform 2", "Platform 3")));
        addStation(new Station("DDN", "Dehradun", "Dehradun", "Uttarakhand", "NR", Arrays.asList("Platform 1", "Platform 2")));
        addStation(new Station("HW", "Haridwar Junction", "Haridwar", "Uttarakhand", "NR", Arrays.asList("Platform 1", "Platform 2")));

        // Western & Central Zone
        addStation(new Station("MMCT", "Mumbai Central", "Mumbai", "Maharashtra", "WR", Arrays.asList("Platform 1", "Platform 2", "Platform 3", "Platform 5")));
        addStation(new Station("CSMT", "Chhatrapati Shivaji Maharaj Terminus", "Mumbai", "Maharashtra", "CR", Arrays.asList("Platform 14", "Platform 15", "Platform 16", "Platform 18")));
        addStation(new Station("BDTS", "Bandra Terminus", "Mumbai", "Maharashtra", "WR", Arrays.asList("Platform 1", "Platform 2", "Platform 3")));
        addStation(new Station("LTT", "Lokmanya Tilak Terminus", "Mumbai", "Maharashtra", "CR", Arrays.asList("Platform 1", "Platform 2", "Platform 4")));
        addStation(new Station("PUNE", "Pune Junction", "Pune", "Maharashtra", "CR", Arrays.asList("Platform 1", "Platform 2", "Platform 3", "Platform 6")));
        addStation(new Station("NGP", "Nagpur Junction", "Nagpur", "Maharashtra", "CR", Arrays.asList("Platform 1", "Platform 2", "Platform 3")));
        addStation(new Station("SUR", "Solapur", "Solapur", "Maharashtra", "CR", Arrays.asList("Platform 1", "Platform 2")));
        addStation(new Station("ADI", "Ahmedabad Junction", "Ahmedabad", "Gujarat", "WR", Arrays.asList("Platform 1", "Platform 5", "Platform 8")));
        addStation(new Station("ST", "Surat", "Surat", "Gujarat", "WR", Arrays.asList("Platform 1", "Platform 2", "Platform 3")));
        addStation(new Station("BRC", "Vadodara Junction", "Vadodara", "Gujarat", "WR", Arrays.asList("Platform 1", "Platform 2", "Platform 6")));
        addStation(new Station("RJT", "Rajkot Junction", "Rajkot", "Gujarat", "WR", Arrays.asList("Platform 1", "Platform 2")));
        addStation(new Station("MAO", "Madgaon Junction", "Goa", "Goa", "KR", Arrays.asList("Platform 1", "Platform 2", "Platform 3")));
        addStation(new Station("BPL", "Bhopal Junction", "Bhopal", "Madhya Pradesh", "WCR", Arrays.asList("Platform 1", "Platform 2", "Platform 3")));
        addStation(new Station("RKMP", "Rani Kamlapati", "Bhopal", "Madhya Pradesh", "WCR", Arrays.asList("Platform 1", "Platform 2")));
        addStation(new Station("GWL", "Gwalior Junction", "Gwalior", "Madhya Pradesh", "NCR", Arrays.asList("Platform 1", "Platform 2", "Platform 3")));
        addStation(new Station("INDB", "Indore Junction", "Indore", "Madhya Pradesh", "WR", Arrays.asList("Platform 1", "Platform 2")));
        addStation(new Station("JBP", "Jabalpur", "Jabalpur", "Madhya Pradesh", "WCR", Arrays.asList("Platform 1", "Platform 2")));

        // Southern Zone
        addStation(new Station("SBC", "KSR Bengaluru City", "Bengaluru", "Karnataka", "SWR", Arrays.asList("Platform 1", "Platform 7", "Platform 8", "Platform 10")));
        addStation(new Station("YPR", "Yesvantpur Junction", "Bengaluru", "Karnataka", "SWR", Arrays.asList("Platform 1", "Platform 2", "Platform 6")));
        addStation(new Station("MYS", "Mysuru Junction", "Mysuru", "Karnataka", "SWR", Arrays.asList("Platform 1", "Platform 2")));
        addStation(new Station("MAS", "MGR Chennai Central", "Chennai", "Tamil Nadu", "SR", Arrays.asList("Platform 1", "Platform 2", "Platform 5", "Platform 11")));
        addStation(new Station("MS", "Chennai Egmore", "Chennai", "Tamil Nadu", "SR", Arrays.asList("Platform 1", "Platform 2", "Platform 4")));
        addStation(new Station("CBE", "Coimbatore Junction", "Coimbatore", "Tamil Nadu", "SR", Arrays.asList("Platform 1", "Platform 2", "Platform 4")));
        addStation(new Station("MDU", "Madurai Junction", "Madurai", "Tamil Nadu", "SR", Arrays.asList("Platform 1", "Platform 2")));
        addStation(new Station("TPJ", "Tiruchchirappalli Junction", "Trichy", "Tamil Nadu", "SR", Arrays.asList("Platform 1", "Platform 2")));
        addStation(new Station("SC", "Secunderabad Junction", "Hyderabad", "Telangana", "SCR", Arrays.asList("Platform 1", "Platform 2", "Platform 10")));
        addStation(new Station("HYB", "Hyderabad Deccan", "Hyderabad", "Telangana", "SCR", Arrays.asList("Platform 1", "Platform 2", "Platform 3")));
        addStation(new Station("BZA", "Vijayawada Junction", "Vijayawada", "Andhra Pradesh", "SCR", Arrays.asList("Platform 1", "Platform 4", "Platform 6")));
        addStation(new Station("VSKP", "Visakhapatnam", "Visakhapatnam", "Andhra Pradesh", "ECoR", Arrays.asList("Platform 1", "Platform 4", "Platform 8")));
        addStation(new Station("RU", "Renigunta / Tirupati", "Tirupati", "Andhra Pradesh", "SCR", Arrays.asList("Platform 1", "Platform 2")));
        addStation(new Station("TVC", "Thiruvananthapuram Central", "Trivandrum", "Kerala", "SR", Arrays.asList("Platform 1", "Platform 2", "Platform 3")));
        addStation(new Station("ERS", "Ernakulam Junction", "Kochi", "Kerala", "SR", Arrays.asList("Platform 1", "Platform 2", "Platform 4")));
        addStation(new Station("CLT", "Kozhikode", "Calicut", "Kerala", "SR", Arrays.asList("Platform 1", "Platform 2")));

        // Eastern & North Eastern Zone
        addStation(new Station("HWH", "Howrah Junction", "Kolkata", "West Bengal", "ER", Arrays.asList("Platform 8", "Platform 9", "Platform 22", "Platform 23")));
        addStation(new Station("SDAH", "Sealdah", "Kolkata", "West Bengal", "ER", Arrays.asList("Platform 9", "Platform 10", "Platform 11")));
        addStation(new Station("NJP", "New Jalpaiguri", "Siliguri", "West Bengal", "NFR", Arrays.asList("Platform 1", "Platform 2")));
        addStation(new Station("PNBE", "Patna Junction", "Patna", "Bihar", "ECR", Arrays.asList("Platform 1", "Platform 2", "Platform 3")));
        addStation(new Station("GAYA", "Gaya Junction", "Gaya", "Bihar", "ECR", Arrays.asList("Platform 1", "Platform 2")));
        addStation(new Station("BBS", "Bhubaneswar", "Bhubaneswar", "Odisha", "ECoR", Arrays.asList("Platform 1", "Platform 2", "Platform 3")));
        addStation(new Station("PURI", "Puri", "Puri", "Odisha", "ECoR", Arrays.asList("Platform 1", "Platform 2")));
        addStation(new Station("R", "Raipur Junction", "Raipur", "Chhattisgarh", "SECR", Arrays.asList("Platform 1", "Platform 2")));
        addStation(new Station("BSP", "Bilaspur Junction", "Bilaspur", "Chhattisgarh", "SECR", Arrays.asList("Platform 1", "Platform 2")));
        addStation(new Station("GHY", "Guwahati", "Guwahati", "Assam", "NFR", Arrays.asList("Platform 1", "Platform 2", "Platform 3")));
    }

    private void addStation(Station s) {
        stations.put(s.getCode().toUpperCase(), s);
    }

    private void initTrains() {
        // --- ROUTE: NDLS -> BSB (New Delhi to Varanasi) ---
        addTrain(new Train("22436", "Vande Bharat Express", "Vande Bharat Express", 4.9, 4820, 98, 4.9, 4.8,
            "NDLS", "New Delhi", "BSB", "Varanasi Junction", "06:00", "14:00", "Platform 16", "Platform 1", "8h 00m", 759, "ON_TIME",
            Arrays.asList(
                new TrainClass("EC", "Executive Class (EC)", "Premium 180° rotatable seats, panoramic windows, gourmet meals.", 2400.0, "₹", 12, 14, 0, 0, Arrays.asList("180° Rotating Seats", "Gourmet Meals", "WiFi"), "https://lh3.googleusercontent.com/aida/AEtjO1X0BZze-MF7EqRYKVe3uWGj2aBXFqgyju7h0ULrIPrmgml-zVMngKrQ5RLSy18V2U2SRCTijp99qQPGAuSMaN5yvNtWvNSfzuziv4bmmNxrfuSF1Vr1BtWqfzofcVsa8mjVPO97RaOmFyqfIH2qmoWTz2QTcEm35C_W6E5th2JlshA7HwdUsXyms8tBt3bBYKmhK2IGTeSpJBo47IMcAV0REez7BdhhyShO1sIfi33S9MlwZypTuZQfKjk"),
                new TrainClass("CC", "AC Chair Car (CC)", "Ergonomic seating, charging sockets, complimentary breakfast.", 1285.0, "₹", 40, 6, 0, 0, Arrays.asList("Comfort Seating", "Onboard Meals"), "https://lh3.googleusercontent.com/aida-public/AB6AXuB4gcwWcNors0sGCrLkQaSzvFCfm4BTZI0dLb55x6ntay6nA2nt2HgHSNtXy3lAW48btse6tY0uqBnWw1WrRwtD88fWYbzsF4lFGLT3eQvqeR4ynvC7f02q8Vskmb31OVbI9ORRfuwdXCgJu9-0jF21LCVaaKd28AFVCokf-_1rs-Ied5m8oFXi30-Nhkap7L7xvDPiiajMzgMKpOQMP2Wl6cNFjXQEX-GAjJVO_bsy_fp5rDR50axn")
            ), Arrays.asList("TUESDAY", "WEDNESDAY", "FRIDAY", "SATURDAY", "SUNDAY"), Arrays.asList("New Delhi (06:00)", "Kanpur Central (10:08)", "Prayagraj Junction (12:08)", "Varanasi Junction (14:00)")));

        addTrain(new Train("22416", "Vande Bharat 2.0", "Vande Bharat Express", 4.9, 3910, 99, 4.9, 4.8,
            "NDLS", "New Delhi", "BSB", "Varanasi Junction", "15:00", "23:05", "Platform 16", "Platform 1", "8h 05m", 759, "ON_TIME",
            Arrays.asList(
                new TrainClass("EC", "Executive Class (EC)", "Luxury afternoon Vande Bharat 2.0 service with hot evening snacks & dinner.", 2400.0, "₹", 12, 8, 0, 0, Arrays.asList("180° Rotating Seats", "Dinner"), ""),
                new TrainClass("CC", "AC Chair Car (CC)", "Fast afternoon express connection.", 1285.0, "₹", 40, 18, 0, 0, Arrays.asList("AC Seating", "Tea & Snacks"), "")
            ), Arrays.asList("MONDAY", "TUESDAY", "WEDNESDAY", "FRIDAY", "SATURDAY", "SUNDAY"), Arrays.asList("New Delhi (15:00)", "Kanpur Central (19:08)", "Prayagraj Junction (21:11)", "Varanasi Junction (23:05)")));

        addTrain(new Train("12560", "Shiv Ganga Superfast Express", "Superfast Express", 4.7, 4620, 96, 4.7, 4.5,
            "NDLS", "New Delhi", "BSB", "Varanasi Junction", "20:05", "06:10", "Platform 12", "Platform 8", "10h 05m", 759, "ON_TIME",
            Arrays.asList(
                new TrainClass("1A", "First AC (1A)", "Premier overnight coupe with fresh bedding.", 3850.0, "₹", 10, 2, 0, 0, Arrays.asList("Coupe", "Bedding"), ""),
                new TrainClass("2A", "AC 2-Tier (2A)", "Spacious 2-tier overnight berths.", 2350.0, "₹", 24, 12, 0, 0, Arrays.asList("Privacy Curtains"), ""),
                new TrainClass("3A", "AC 3-Tier (3A)", "Popular sleeper coach.", 1680.0, "₹", 48, 30, 0, 0, Arrays.asList("AC Sleeper"), "")
            ), Arrays.asList("DAILY"), Arrays.asList("New Delhi (20:05)", "Kanpur Central (01:00)", "Prayagraj Junction (03:45)", "Varanasi Junction (06:10)")));

        addTrain(new Train("12582", "Banaras Superfast Express", "Superfast Express", 4.6, 3420, 95, 4.6, 4.4,
            "NDLS", "New Delhi", "BSB", "Varanasi Junction", "22:50", "10:00", "Platform 15", "Platform 9", "11h 10m", 759, "ON_TIME",
            Arrays.asList(
                new TrainClass("2A", "AC 2-Tier (2A)", "Clean overnight 2A coach.", 2250.0, "₹", 24, 6, 0, 0, Arrays.asList("Reading Lights"), ""),
                new TrainClass("3A", "AC 3-Tier (3A)", "Economical AC travel.", 1550.0, "₹", 48, 22, 0, 0, Arrays.asList("AC Berths"), "")
            ), Arrays.asList("DAILY"), Arrays.asList("New Delhi (22:50)", "Aligarh (00:40)", "Kanpur Central (04:50)", "Prayagraj (07:30)", "Varanasi (10:00)")));

        // --- ROUTE: MAS -> SBC (Chennai to Bengaluru) ---
        addTrain(new Train("20608", "Vande Bharat Express", "Vande Bharat Express", 4.9, 3940, 99, 4.9, 4.8,
            "MAS", "MGR Chennai Central", "SBC", "KSR Bengaluru", "05:50", "10:20", "Platform 5", "Platform 1", "4h 30m", 359, "ON_TIME",
            Arrays.asList(
                new TrainClass("EC", "Executive Class (EC)", "Ultra smooth ride, rotating executive seats, South Indian breakfast & filter coffee.", 1850.0, "₹", 12, 6, 0, 0, Arrays.asList("180° Rotatable", "Breakfast Box", "WiFi"), ""),
                new TrainClass("CC", "AC Chair Car (CC)", "High speed morning transit with breakfast.", 950.0, "₹", 40, 24, 0, 0, Arrays.asList("AC Seating", "Breakfast"), "")
            ), Arrays.asList("MONDAY", "TUESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"), Arrays.asList("MGR Chennai Central (05:50)", "Katpadi Junction (07:13)", "Bengaluru Cantt (09:48)", "KSR Bengaluru (10:20)")));

        addTrain(new Train("12028", "Bengaluru Shatabdi Express", "Shatabdi Express", 4.8, 5120, 97, 4.8, 4.7,
            "MAS", "MGR Chennai Central", "SBC", "KSR Bengaluru", "06:00", "11:00", "Platform 2", "Platform 7", "5h 00m", 359, "ON_TIME",
            Arrays.asList(
                new TrainClass("EC", "Executive Class (EC)", "Executive 2x2 luxury seating with hot breakfast and newspaper.", 1650.0, "₹", 12, 4, 0, 0, Arrays.asList("Executive 2x2", "Breakfast", "Newspapers"), ""),
                new TrainClass("CC", "AC Chair Car (CC)", "Reliable and popular morning intercity service.", 850.0, "₹", 40, 19, 0, 0, Arrays.asList("Comfort Seating", "Breakfast Included"), "")
            ), Arrays.asList("MONDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"), Arrays.asList("MGR Chennai Central (06:00)", "Katpadi (07:38)", "Jolarpettai (08:50)", "Bengaluru Cantt (10:35)", "KSR Bengaluru (11:00)")));

        addTrain(new Train("12639", "Brindavan Superfast Express", "Superfast Express", 4.7, 6200, 96, 4.7, 4.5,
            "MAS", "MGR Chennai Central", "SBC", "KSR Bengaluru", "07:40", "13:40", "Platform 8", "Platform 2", "6h 00m", 359, "ON_TIME",
            Arrays.asList(
                new TrainClass("CC", "AC Chair Car (CC)", "Iconic intercity express with air-conditioned comfort.", 620.0, "₹", 40, 32, 0, 0, Arrays.asList("AC Chair", "Snack Service"), ""),
                new TrainClass("2S", "Second Sitting (2S)", "Standard reserved daytime seating.", 160.0, "₹", 60, 45, 0, 0, Arrays.asList("Window/Aisle Seating"), "")
            ), Arrays.asList("DAILY"), Arrays.asList("MGR Chennai Central (07:40)", "Arakkonam (08:38)", "Katpadi (09:28)", "Bangarapet (11:58)", "KSR Bengaluru (13:40)")));

        addTrain(new Train("12607", "Lalbagh Superfast Express", "Superfast Express", 4.7, 5400, 96, 4.7, 4.5,
            "MAS", "MGR Chennai Central", "SBC", "KSR Bengaluru", "15:30", "21:35", "Platform 4", "Platform 1", "6h 05m", 359, "ON_TIME",
            Arrays.asList(
                new TrainClass("CC", "AC Chair Car (CC)", "Evening express with dinner options.", 620.0, "₹", 40, 15, 0, 0, Arrays.asList("AC Seating", "Pantry Meals"), ""),
                new TrainClass("2S", "Second Sitting (2S)", "Economical evening express.", 160.0, "₹", 60, 28, 0, 0, Arrays.asList("Standard Seat"), "")
            ), Arrays.asList("DAILY"), Arrays.asList("MGR Chennai Central (15:30)", "Katpadi (17:28)", "Jolarpettai (18:48)", "Krishnarajapuram (20:45)", "KSR Bengaluru (21:35)")));

        // --- ROUTE: SBC -> MAS (Bengaluru to Chennai) ---
        addTrain(new Train("20607", "Vande Bharat Express", "Vande Bharat Express", 4.9, 3810, 99, 4.9, 4.8,
            "SBC", "KSR Bengaluru", "MAS", "MGR Chennai Central", "05:45", "10:10", "Platform 1", "Platform 5", "4h 25m", 359, "ON_TIME",
            Arrays.asList(
                new TrainClass("EC", "Executive Class (EC)", "Ultra-fast morning connection to Chennai.", 1850.0, "₹", 12, 5, 0, 0, Arrays.asList("180° Rotatable", "Breakfast Box"), ""),
                new TrainClass("CC", "AC Chair Car (CC)", "High speed intercity commute.", 950.0, "₹", 40, 20, 0, 0, Arrays.asList("AC Seating", "Breakfast"), "")
            ), Arrays.asList("MONDAY", "TUESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"), Arrays.asList("KSR Bengaluru (05:45)", "Bengaluru Cantt (05:55)", "Katpadi Junction (08:28)", "MGR Chennai Central (10:10)")));

        addTrain(new Train("12027", "Chennai Shatabdi Express", "Shatabdi Express", 4.8, 4980, 97, 4.8, 4.7,
            "SBC", "KSR Bengaluru", "MAS", "MGR Chennai Central", "06:00", "11:00", "Platform 7", "Platform 2", "5h 00m", 359, "ON_TIME",
            Arrays.asList(
                new TrainClass("EC", "Executive Class (EC)", "Morning executive train.", 1650.0, "₹", 12, 8, 0, 0, Arrays.asList("Executive 2x2", "Breakfast"), ""),
                new TrainClass("CC", "AC Chair Car (CC)", "Comfortable seating.", 850.0, "₹", 40, 22, 0, 0, Arrays.asList("AC Seating", "Meals"), "")
            ), Arrays.asList("MONDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"), Arrays.asList("KSR Bengaluru (06:00)", "Bengaluru Cantt (06:10)", "Katpadi (09:18)", "MGR Chennai Central (11:00)")));

        addTrain(new Train("12640", "Brindavan Superfast Express", "Superfast Express", 4.7, 5800, 96, 4.7, 4.5,
            "SBC", "KSR Bengaluru", "MAS", "MGR Chennai Central", "15:10", "21:10", "Platform 2", "Platform 8", "6h 00m", 359, "ON_TIME",
            Arrays.asList(
                new TrainClass("CC", "AC Chair Car (CC)", "Afternoon express.", 620.0, "₹", 40, 28, 0, 0, Arrays.asList("AC Chair"), ""),
                new TrainClass("2S", "Second Sitting (2S)", "Standard seating.", 160.0, "₹", 60, 40, 0, 0, Arrays.asList("Standard Seat"), "")
            ), Arrays.asList("DAILY"), Arrays.asList("KSR Bengaluru (15:10)", "Bangarapet (16:20)", "Katpadi (18:48)", "MGR Chennai Central (21:10)")));

        addTrain(new Train("12608", "Lalbagh Superfast Express", "Superfast Express", 4.7, 5100, 96, 4.7, 4.5,
            "SBC", "KSR Bengaluru", "MAS", "MGR Chennai Central", "06:30", "12:35", "Platform 1", "Platform 4", "6h 05m", 359, "ON_TIME",
            Arrays.asList(
                new TrainClass("CC", "AC Chair Car (CC)", "Morning express.", 620.0, "₹", 40, 18, 0, 0, Arrays.asList("AC Seating"), ""),
                new TrainClass("2S", "Second Sitting (2S)", "Standard seating.", 160.0, "₹", 60, 35, 0, 0, Arrays.asList("Standard Seat"), "")
            ), Arrays.asList("DAILY"), Arrays.asList("KSR Bengaluru (06:30)", "Krishnarajapuram (06:50)", "Katpadi (10:13)", "MGR Chennai Central (12:35)")));

        // --- ROUTE: MMCT -> NDLS (Mumbai to New Delhi) ---
        addTrain(new Train("12951", "Mumbai Rajdhani Express", "Rajdhani Express", 4.8, 5630, 96, 4.8, 4.7,
            "MMCT", "Mumbai Central", "NDLS", "New Delhi", "17:00", "08:32", "Platform 1", "Platform 2", "15h 32m", 1386, "ON_TIME",
            Arrays.asList(
                new TrainClass("1A", "First AC (1A)", "Private lockable coupe with luxury bedding & multi-course dinner.", 4750.0, "₹", 10, 2, 0, 0, Arrays.asList("Private Coupe", "Gourmet Catering"), ""),
                new TrainClass("2A", "AC 2-Tier (2A)", "Spacious 2-tier berths with meals.", 2850.0, "₹", 24, 18, 0, 0, Arrays.asList("Privacy Curtains", "Dinner"), ""),
                new TrainClass("3A", "AC 3-Tier (3A)", "Popular overnight travel.", 2050.0, "₹", 48, 34, 0, 0, Arrays.asList("AC Sleeper", "Bedroll"), "")
            ), Arrays.asList("DAILY"), Arrays.asList("Mumbai Central (17:00)", "Surat (19:37)", "Vadodara (21:10)", "Kota (03:15)", "New Delhi (08:32)")));

        addTrain(new Train("12953", "August Kranti Rajdhani", "Rajdhani Express", 4.8, 4890, 96, 4.8, 4.7,
            "MMCT", "Mumbai Central", "NDLS", "New Delhi", "17:10", "09:43", "Platform 2", "Platform 3", "16h 33m", 1377, "ON_TIME",
            Arrays.asList(
                new TrainClass("1A", "First AC (1A)", "Premier Rajdhani service.", 4750.0, "₹", 10, 4, 0, 0, Arrays.asList("Private Coupe", "Catering"), ""),
                new TrainClass("2A", "AC 2-Tier (2A)", "Comfortable 2A.", 2850.0, "₹", 24, 14, 0, 0, Arrays.asList("Privacy Curtains"), ""),
                new TrainClass("3A", "AC 3-Tier (3A)", "Standard 3A coach.", 2050.0, "₹", 48, 26, 0, 0, Arrays.asList("AC Berths"), "")
            ), Arrays.asList("DAILY"), Arrays.asList("Mumbai Central (17:10)", "Andheri (17:33)", "Surat (20:05)", "Vadodara (21:40)", "Kota (04:10)", "New Delhi (09:43)")));

        addTrain(new Train("12925", "Paschim Superfast Express", "Superfast Express", 4.6, 5210, 94, 4.6, 4.4,
            "MMCT", "Mumbai Central", "NDLS", "New Delhi", "11:25", "10:40", "Platform 3", "Platform 1", "23h 15m", 1386, "ON_TIME",
            Arrays.asList(
                new TrainClass("2A", "AC 2-Tier (2A)", "Day and night express.", 2450.0, "₹", 24, 8, 0, 0, Arrays.asList("Curtains"), ""),
                new TrainClass("3A", "AC 3-Tier (3A)", "Economical AC.", 1750.0, "₹", 48, 20, 0, 0, Arrays.asList("AC Sleeper"), ""),
                new TrainClass("SL", "Sleeper Class (SL)", "Standard non-AC sleeper.", 640.0, "₹", 72, 35, 0, 0, Arrays.asList("Reserved Berth"), "")
            ), Arrays.asList("DAILY"), Arrays.asList("Mumbai Central (11:25)", "Surat (15:20)", "Vadodara (17:30)", "Ratlam (21:40)", "Kota (01:50)", "New Delhi (10:40)")));

        addTrain(new Train("12903", "Golden Temple Mail", "Superfast Express", 4.7, 4980, 95, 4.7, 4.5,
            "MMCT", "Mumbai Central", "NDLS", "New Delhi", "18:45", "13:50", "Platform 5", "Platform 4", "19h 05m", 1386, "ON_TIME",
            Arrays.asList(
                new TrainClass("1A", "First AC (1A)", "Historic heritage superfast.", 4250.0, "₹", 10, 3, 0, 0, Arrays.asList("Coupe"), ""),
                new TrainClass("2A", "AC 2-Tier (2A)", "Overnight 2A.", 2650.0, "₹", 24, 10, 0, 0, Arrays.asList("Berths"), ""),
                new TrainClass("3A", "AC 3-Tier (3A)", "Overnight 3A.", 1850.0, "₹", 48, 28, 0, 0, Arrays.asList("AC Sleeper"), "")
            ), Arrays.asList("DAILY"), Arrays.asList("Mumbai Central (18:45)", "Surat (22:40)", "Vadodara (00:25)", "Kota (07:15)", "New Delhi (13:50)")));

        // --- ROUTE: NDLS -> MMCT (New Delhi to Mumbai) ---
        addTrain(new Train("12952", "New Delhi Rajdhani Express", "Rajdhani Express", 4.8, 5120, 97, 4.8, 4.7,
            "NDLS", "New Delhi", "MMCT", "Mumbai Central", "16:55", "08:35", "Platform 2", "Platform 1", "15h 40m", 1386, "ON_TIME",
            Arrays.asList(
                new TrainClass("1A", "First AC (1A)", "Royal Mumbai Rajdhani suite experience.", 4750.0, "₹", 10, 3, 0, 0, Arrays.asList("Private Coupe", "Gourmet Catering"), ""),
                new TrainClass("2A", "AC 2-Tier (2A)", "Spacious 2-tier berths with meals.", 2850.0, "₹", 24, 8, 0, 0, Arrays.asList("Privacy Curtains", "Dinner"), ""),
                new TrainClass("3A", "AC 3-Tier (3A)", "Popular executive travel.", 2050.0, "₹", 48, 15, 0, 0, Arrays.asList("AC Sleeper", "Bedroll"), "")
            ), Arrays.asList("DAILY"), Arrays.asList("New Delhi (16:55)", "Kota (21:30)", "Ratlam (00:05)", "Vadodara (03:22)", "Surat (05:13)", "Mumbai Central (08:35)")));

        addTrain(new Train("12954", "August Kranti Rajdhani", "Rajdhani Express", 4.8, 4610, 96, 4.8, 4.7,
            "NDLS", "New Delhi", "MMCT", "Mumbai Central", "17:15", "10:05", "Platform 3", "Platform 2", "16h 50m", 1377, "ON_TIME",
            Arrays.asList(
                new TrainClass("1A", "First AC (1A)", "Premier Rajdhani express.", 4750.0, "₹", 10, 5, 0, 0, Arrays.asList("Coupe"), ""),
                new TrainClass("2A", "AC 2-Tier (2A)", "Overnight 2A.", 2850.0, "₹", 24, 12, 0, 0, Arrays.asList("Meals"), ""),
                new TrainClass("3A", "AC 3-Tier (3A)", "Comfortable 3A.", 2050.0, "₹", 48, 22, 0, 0, Arrays.asList("AC Sleeper"), "")
            ), Arrays.asList("DAILY"), Arrays.asList("New Delhi (17:15)", "Kota (22:00)", "Ratlam (01:20)", "Vadodara (05:10)", "Surat (06:55)", "Mumbai Central (10:05)")));

        // --- ROUTE: NDLS -> HWH (New Delhi to Howrah/Kolkata) ---
        addTrain(new Train("12302", "Howrah Rajdhani Express", "Rajdhani Express", 4.7, 4920, 95, 4.7, 4.6,
            "NDLS", "New Delhi", "HWH", "Howrah Junction", "16:55", "09:55", "Platform 1", "Platform 9", "17h 00m", 1451, "ON_TIME",
            Arrays.asList(
                new TrainClass("1A", "First AC (1A)", "Royal Kolkata suite experience.", 4950.0, "₹", 10, 1, 0, 0, Arrays.asList("Private Coupe", "Royal Menu"), ""),
                new TrainClass("2A", "AC 2-Tier (2A)", "Overnight comfort with dinner.", 2950.0, "₹", 24, 10, 0, 0, Arrays.asList("Privacy Curtains", "Meals"), ""),
                new TrainClass("3A", "AC 3-Tier (3A)", "Popular family travel option.", 2100.0, "₹", 48, 14, 0, 0, Arrays.asList("AC Berths", "Catering"), "")
            ), Arrays.asList("DAILY"), Arrays.asList("New Delhi (16:55)", "Kanpur Central (21:32)", "Prayagraj (23:43)", "Pt DD Upadhyaya (01:47)", "Gaya (03:58)", "Howrah (09:55)")));

        addTrain(new Train("12306", "Kolkata Rajdhani (via Patna)", "Rajdhani Express", 4.7, 4120, 95, 4.7, 4.6,
            "NDLS", "New Delhi", "HWH", "Howrah Junction", "16:55", "12:15", "Platform 2", "Platform 8", "19h 20m", 1530, "ON_TIME",
            Arrays.asList(
                new TrainClass("1A", "First AC (1A)", "First class suite via Patna.", 4950.0, "₹", 10, 3, 0, 0, Arrays.asList("Private Coupe"), ""),
                new TrainClass("2A", "AC 2-Tier (2A)", "2A sleeper.", 2950.0, "₹", 24, 8, 0, 0, Arrays.asList("Meals"), ""),
                new TrainClass("3A", "AC 3-Tier (3A)", "3A sleeper.", 2100.0, "₹", 48, 19, 0, 0, Arrays.asList("AC Sleeper"), "")
            ), Arrays.asList("FRIDAY"), Arrays.asList("New Delhi (16:55)", "Kanpur (21:32)", "Prayagraj (23:43)", "Patna (04:20)", "Howrah (12:15)")));

        addTrain(new Train("12312", "Netaji Superfast Express", "Superfast Express", 4.6, 5180, 94, 4.6, 4.4,
            "NDLS", "New Delhi", "HWH", "Howrah Junction", "06:15", "08:05", "Platform 11", "Platform 14", "25h 50m", 1451, "ON_TIME",
            Arrays.asList(
                new TrainClass("2A", "AC 2-Tier (2A)", "Classic historic express.", 2650.0, "₹", 24, 14, 0, 0, Arrays.asList("2A Berths"), ""),
                new TrainClass("3A", "AC 3-Tier (3A)", "AC 3 Tier.", 1850.0, "₹", 48, 25, 0, 0, Arrays.asList("AC Sleeper"), "")
            ), Arrays.asList("DAILY"), Arrays.asList("New Delhi (06:15)", "Aligarh (08:30)", "Kanpur (13:50)", "Prayagraj (17:10)", "Gaya (23:30)", "Howrah (08:05)")));

        addTrain(new Train("12324", "New Delhi - Howrah SF Express", "Superfast Express", 4.6, 3820, 95, 4.6, 4.4,
            "NDLS", "New Delhi", "HWH", "Howrah Junction", "07:10", "06:40", "Platform 14", "Platform 9", "23h 30m", 1451, "ON_TIME",
            Arrays.asList(
                new TrainClass("2A", "AC 2-Tier (2A)", "Superfast 2A.", 2550.0, "₹", 24, 9, 0, 0, Arrays.asList("Berths"), ""),
                new TrainClass("3A", "AC 3-Tier (3A)", "Superfast 3A.", 1780.0, "₹", 48, 31, 0, 0, Arrays.asList("AC Sleeper"), "")
            ), Arrays.asList("TUESDAY", "FRIDAY"), Arrays.asList("New Delhi (07:10)", "Kanpur (13:30)", "Prayagraj (15:55)", "Dhanbad (01:20)", "Howrah (06:40)")));

        // --- ROUTE: ADI -> MMCT (Ahmedabad to Mumbai) ---
        addTrain(new Train("82902", "Mumbai Tejas Express", "Tejas Express", 4.8, 3120, 97, 4.8, 4.7,
            "ADI", "Ahmedabad Junction", "MMCT", "Mumbai Central", "06:40", "13:05", "Platform 5", "Platform 3", "6h 25m", 493, "ON_TIME",
            Arrays.asList(
                new TrainClass("EC", "Executive Chair Car", "Hostess service, automatic sliding doors, LED entertainment, gourmet breakfast.", 2150.0, "₹", 12, 4, 0, 0, Arrays.asList("Hostess Service", "Gourmet Breakfast", "Free WiFi"), ""),
                new TrainClass("CC", "AC Chair Car", "Modern business class express seating.", 1120.0, "₹", 40, 22, 0, 0, Arrays.asList("AC Seating", "Breakfast Box"), "")
            ), Arrays.asList("MONDAY", "TUESDAY", "WEDNESDAY", "FRIDAY", "SATURDAY", "SUNDAY"), Arrays.asList("Ahmedabad (06:40)", "Vadodara (07:33)", "Surat (08:58)", "Mumbai Central (13:05)")));

        addTrain(new Train("20902", "Vande Bharat Express", "Vande Bharat Express", 4.9, 4100, 99, 4.9, 4.8,
            "ADI", "Ahmedabad Junction", "MMCT", "Mumbai Central", "15:00", "20:25", "Platform 1", "Platform 5", "5h 25m", 493, "ON_TIME",
            Arrays.asList(
                new TrainClass("EC", "Executive Class (EC)", "Ultra-fast afternoon run to Mumbai.", 2150.0, "₹", 12, 6, 0, 0, Arrays.asList("180° Rotatable", "Dinner"), ""),
                new TrainClass("CC", "AC Chair Car (CC)", "AC Chair Car with evening refreshments.", 1120.0, "₹", 40, 18, 0, 0, Arrays.asList("AC Seating", "Refreshments"), "")
            ), Arrays.asList("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"), Arrays.asList("Ahmedabad (15:00)", "Vadodara (15:53)", "Surat (17:10)", "Mumbai Central (20:25)")));

        addTrain(new Train("12010", "Mumbai Shatabdi Express", "Shatabdi Express", 4.8, 3840, 97, 4.8, 4.6,
            "ADI", "Ahmedabad Junction", "MMCT", "Mumbai Central", "15:10", "21:45", "Platform 2", "Platform 1", "6h 35m", 493, "ON_TIME",
            Arrays.asList(
                new TrainClass("EC", "Executive Class", "Executive seating with high tea and dinner.", 1950.0, "₹", 12, 5, 0, 0, Arrays.asList("Executive 2x2", "Dinner"), ""),
                new TrainClass("CC", "AC Chair Car", "Popular evening commuter express.", 1020.0, "₹", 40, 27, 0, 0, Arrays.asList("AC Seating", "Dinner Included"), "")
            ), Arrays.asList("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"), Arrays.asList("Ahmedabad (15:10)", "Nadiad (15:45)", "Vadodara (16:30)", "Surat (18:15)", "Mumbai Central (21:45)")));

        addTrain(new Train("12932", "Double Decker Express", "Superfast Express", 4.7, 4290, 96, 4.7, 4.5,
            "ADI", "Ahmedabad Junction", "MMCT", "Mumbai Central", "06:00", "13:00", "Platform 6", "Platform 2", "7h 00m", 493, "ON_TIME",
            Arrays.asList(
                new TrainClass("CC", "AC Chair Car (Upper/Lower Deck)", "Panoramic double-decker AC seating.", 780.0, "₹", 80, 48, 0, 0, Arrays.asList("Double Decker AC", "Snack Service"), "")
            ), Arrays.asList("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"), Arrays.asList("Ahmedabad (06:00)", "Vadodara (07:05)", "Surat (08:45)", "Borivali (12:15)", "Mumbai Central (13:00)")));

        // --- ROUTE: NDLS -> LKO (New Delhi to Lucknow) ---
        addTrain(new Train("12004", "Lucknow Shatabdi Express", "Shatabdi Express", 4.7, 4310, 96, 4.7, 4.6,
            "NDLS", "New Delhi", "LKO", "Lucknow Charbagh", "06:10", "12:40", "Platform 2", "Platform 1", "6h 30m", 512, "ON_TIME",
            Arrays.asList(
                new TrainClass("EC", "Executive Class", "Spacious 2x2 luxury seating with hot breakfast.", 1980.0, "₹", 12, 7, 0, 0, Arrays.asList("Executive 2x2", "Breakfast"), ""),
                new TrainClass("CC", "AC Chair Car", "Reliable and fast day train connectivity.", 1050.0, "₹", 40, 15, 0, 0, Arrays.asList("AC Seating", "Breakfast Included"), "")
            ), Arrays.asList("DAILY"), Arrays.asList("New Delhi (06:10)", "Ghaziabad (06:48)", "Aligarh (07:49)", "Kanpur Central (11:20)", "Lucknow (12:40)")));

        addTrain(new Train("82502", "Lucknow Tejas Express", "Tejas Express", 4.8, 3620, 97, 4.8, 4.7,
            "NDLS", "New Delhi", "LKO", "Lucknow Charbagh", "15:40", "22:05", "Platform 5", "Platform 6", "6h 25m", 512, "ON_TIME",
            Arrays.asList(
                new TrainClass("EC", "Executive Chair Car", "Modern hostess service with evening snacks & dinner.", 2150.0, "₹", 12, 6, 0, 0, Arrays.asList("Executive 2x2", "Hostess Service"), ""),
                new TrainClass("CC", "AC Chair Car", "Comfortable evening express.", 1120.0, "₹", 40, 24, 0, 0, Arrays.asList("AC Seating", "Dinner Included"), "")
            ), Arrays.asList("MONDAY", "TUESDAY", "WEDNESDAY", "FRIDAY", "SATURDAY", "SUNDAY"), Arrays.asList("New Delhi (15:40)", "Ghaziabad (16:13)", "Kanpur Central (20:35)", "Lucknow (22:05)")));

        addTrain(new Train("12430", "Lucknow AC Superfast Express", "Superfast Express", 4.6, 3210, 95, 4.6, 4.4,
            "NDLS", "New Delhi", "LKO", "Lucknow Charbagh", "23:25", "07:25", "Platform 8", "Platform 2", "8h 00m", 512, "ON_TIME",
            Arrays.asList(
                new TrainClass("1A", "First AC (1A)", "Overnight AC coupe.", 3150.0, "₹", 10, 3, 0, 0, Arrays.asList("Coupe"), ""),
                new TrainClass("2A", "AC 2-Tier (2A)", "Overnight 2A.", 1950.0, "₹", 24, 11, 0, 0, Arrays.asList("Berths"), ""),
                new TrainClass("3A", "AC 3-Tier (3A)", "Economical 3A.", 1380.0, "₹", 48, 26, 0, 0, Arrays.asList("AC Sleeper"), "")
            ), Arrays.asList("DAILY"), Arrays.asList("New Delhi (23:25)", "Ghaziabad (00:05)", "Moradabad (02:40)", "Bareilly (04:18)", "Lucknow (07:25)")));

        addTrain(new Train("12230", "Lucknow Mail", "Superfast Express", 4.7, 4950, 96, 4.7, 4.5,
            "NDLS", "New Delhi", "LKO", "Lucknow Charbagh", "22:00", "06:50", "Platform 16", "Platform 1", "8h 50m", 512, "ON_TIME",
            Arrays.asList(
                new TrainClass("1A", "First AC (1A)", "Premier heritage mail.", 3150.0, "₹", 10, 2, 0, 0, Arrays.asList("Coupe"), ""),
                new TrainClass("2A", "AC 2-Tier (2A)", "Spacious 2A.", 1950.0, "₹", 24, 9, 0, 0, Arrays.asList("Berths"), ""),
                new TrainClass("3A", "AC 3-Tier (3A)", "Popular 3A.", 1380.0, "₹", 48, 20, 0, 0, Arrays.asList("AC Sleeper"), "")
            ), Arrays.asList("DAILY"), Arrays.asList("New Delhi (22:00)", "Ghaziabad (22:45)", "Hapur (23:20)", "Moradabad (01:10)", "Bareilly (02:45)", "Lucknow (06:50)")));

        // --- ROUTE: PUNE -> CSMT (Pune to Mumbai) ---
        addTrain(new Train("22226", "Vande Bharat Express", "Vande Bharat Express", 4.9, 2980, 98, 4.9, 4.8,
            "PUNE", "Pune Junction", "CSMT", "Mumbai CSMT", "09:15", "12:35", "Platform 2", "Platform 18", "3h 20m", 192, "ON_TIME",
            Arrays.asList(
                new TrainClass("EC", "Executive Class (EC)", "Scenic Bhor Ghat transit with luxury 180° rotatable seating and hot snacks.", 1450.0, "₹", 12, 8, 0, 0, Arrays.asList("180° Rotate", "Ghat Views", "Hot Snacks"), ""),
                new TrainClass("CC", "AC Chair Car (CC)", "Express morning connection to Mumbai.", 750.0, "₹", 40, 26, 0, 0, Arrays.asList("AC Seating", "Tea & Snacks"), "")
            ), Arrays.asList("MONDAY", "TUESDAY", "WEDNESDAY", "FRIDAY", "SATURDAY", "SUNDAY"), Arrays.asList("Pune Junction (09:15)", "Kalyan Junction (11:33)", "Dadar (12:12)", "Mumbai CSMT (12:35)")));

        addTrain(new Train("12124", "Deccan Queen Superfast", "Superfast Express", 4.9, 6100, 99, 4.9, 4.8,
            "PUNE", "Pune Junction", "CSMT", "Mumbai CSMT", "07:15", "10:25", "Platform 1", "Platform 14", "3h 10m", 192, "ON_TIME",
            Arrays.asList(
                new TrainClass("CC", "AC Chair Car (Dining Car Included)", "World famous Deccan Queen Dining Car and AC Chair Car.", 580.0, "₹", 40, 14, 0, 0, Arrays.asList("Dining Car", "AC Chair"), ""),
                new TrainClass("2S", "Second Sitting (2S)", "Heritage daily commuter seating.", 120.0, "₹", 60, 30, 0, 0, Arrays.asList("Standard Seat"), "")
            ), Arrays.asList("DAILY"), Arrays.asList("Pune (07:15)", "Lonavala (08:05)", "Khandala (08:15)", "Dadar (10:05)", "Mumbai CSMT (10:25)")));

        addTrain(new Train("12126", "Pragati Superfast Express", "Superfast Express", 4.7, 3400, 96, 4.7, 4.5,
            "PUNE", "Pune Junction", "CSMT", "Mumbai CSMT", "07:50", "11:25", "Platform 3", "Platform 15", "3h 35m", 192, "ON_TIME",
            Arrays.asList(
                new TrainClass("CC", "AC Chair Car", "Morning business connection.", 580.0, "₹", 40, 22, 0, 0, Arrays.asList("AC Seating"), ""),
                new TrainClass("2S", "Second Sitting (2S)", "Reserved second seating.", 120.0, "₹", 60, 42, 0, 0, Arrays.asList("Reserved Seat"), "")
            ), Arrays.asList("DAILY"), Arrays.asList("Pune (07:50)", "Lonavala (08:40)", "Panvel (09:55)", "Thane (10:28)", "Dadar (11:00)", "Mumbai CSMT (11:25)")));

        addTrain(new Train("12128", "Pune - Mumbai Intercity SF", "Superfast Express", 4.7, 3800, 96, 4.7, 4.5,
            "PUNE", "Pune Junction", "CSMT", "Mumbai CSMT", "17:55", "21:05", "Platform 4", "Platform 16", "3h 10m", 192, "ON_TIME",
            Arrays.asList(
                new TrainClass("CC", "AC Chair Car", "Evening fast intercity commute.", 580.0, "₹", 40, 18, 0, 0, Arrays.asList("AC Chair"), ""),
                new TrainClass("2S", "Second Sitting (2S)", "Evening commuter express.", 120.0, "₹", 60, 36, 0, 0, Arrays.asList("Standard Seat"), "")
            ), Arrays.asList("DAILY"), Arrays.asList("Pune (17:55)", "Lonavala (18:45)", "Thane (20:18)", "Dadar (20:42)", "Mumbai CSMT (21:05)")));

        // --- ROUTE: SC -> VSKP (Secunderabad/Hyderabad to Visakhapatnam) ---
        addTrain(new Train("20834", "Vande Bharat Express", "Vande Bharat Express", 4.9, 3620, 98, 4.9, 4.8,
            "SC", "Secunderabad Junction", "VSKP", "Visakhapatnam", "15:00", "23:30", "Platform 10", "Platform 1", "8h 30m", 699, "ON_TIME",
            Arrays.asList(
                new TrainClass("EC", "Executive Class (EC)", "Fastest connection between Hyderabad and coastal Andhra.", 2250.0, "₹", 12, 4, 0, 0, Arrays.asList("Executive 2x2", "Hot Dinner", "WiFi"), ""),
                new TrainClass("CC", "AC Chair Car (CC)", "Evening express travel with dinner.", 1180.0, "₹", 40, 19, 0, 0, Arrays.asList("AC Seating", "Dinner Included"), "")
            ), Arrays.asList("MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY"), Arrays.asList("Secunderabad (15:00)", "Warangal (16:30)", "Khammam (17:45)", "Vijayawada (19:00)", "Rajahmundry (20:58)", "Visakhapatnam (23:30)")));

        addTrain(new Train("12728", "Godavari Superfast Express", "Superfast Express", 4.8, 5400, 97, 4.8, 4.6,
            "SC", "Secunderabad Junction", "VSKP", "Visakhapatnam", "17:05", "05:35", "Platform 8", "Platform 2", "12h 30m", 699, "ON_TIME",
            Arrays.asList(
                new TrainClass("1A", "First AC (1A)", "Overnight coupe.", 3450.0, "₹", 10, 2, 0, 0, Arrays.asList("Coupe"), ""),
                new TrainClass("2A", "AC 2-Tier (2A)", "Spacious 2A.", 2150.0, "₹", 24, 10, 0, 0, Arrays.asList("Privacy Curtains"), ""),
                new TrainClass("3A", "AC 3-Tier (3A)", "Popular 3A.", 1480.0, "₹", 48, 25, 0, 0, Arrays.asList("AC Sleeper"), "")
            ), Arrays.asList("DAILY"), Arrays.asList("Secunderabad (17:05)", "Kazipet (19:00)", "Vijayawada (22:50)", "Eluru (23:45)", "Rajahmundry (01:20)", "Visakhapatnam (05:35)")));

        addTrain(new Train("12740", "Visakhapatnam Garib Rath", "Garib Rath Express", 4.7, 4200, 96, 4.7, 4.5,
            "SC", "Secunderabad Junction", "VSKP", "Visakhapatnam", "20:30", "07:40", "Platform 6", "Platform 3", "11h 10m", 699, "ON_TIME",
            Arrays.asList(
                new TrainClass("3A", "AC 3-Tier (Garib Rath)", "Affordable all-AC overnight travel.", 920.0, "₹", 64, 38, 0, 0, Arrays.asList("Economical AC Sleeper"), "")
            ), Arrays.asList("DAILY"), Arrays.asList("Secunderabad (20:30)", "Warangal (22:15)", "Vijayawada (01:45)", "Rajahmundry (04:10)", "Visakhapatnam (07:40)")));

        addTrain(new Train("12806", "Janmabhoomi Express", "Superfast Express", 4.6, 3900, 95, 4.6, 4.4,
            "SC", "Secunderabad Junction", "VSKP", "Visakhapatnam", "06:15", "19:40", "Platform 10", "Platform 8", "13h 25m", 699, "ON_TIME",
            Arrays.asList(
                new TrainClass("CC", "AC Chair Car", "Day express.", 850.0, "₹", 40, 24, 0, 0, Arrays.asList("AC Seating"), ""),
                new TrainClass("2S", "Second Sitting (2S)", "Second class reserved.", 220.0, "₹", 60, 40, 0, 0, Arrays.asList("Reserved Seat"), "")
            ), Arrays.asList("DAILY"), Arrays.asList("Secunderabad (06:15)", "Nalgonda (07:40)", "Guntur (11:30)", "Vijayawada (12:45)", "Rajahmundry (15:10)", "Visakhapatnam (19:40)")));

        // --- ROUTE: JP -> NDLS (Jaipur to New Delhi) ---
        addTrain(new Train("20978", "Vande Bharat Express", "Vande Bharat Express", 4.9, 3210, 98, 4.9, 4.8,
            "JP", "Jaipur Junction", "NDLS", "New Delhi", "07:50", "11:35", "Platform 1", "Platform 3", "3h 45m", 308, "ON_TIME",
            Arrays.asList(
                new TrainClass("EC", "Executive Class (EC)", "Ultra-fast Pink City morning express.", 1650.0, "₹", 12, 6, 0, 0, Arrays.asList("180° Rotate", "Breakfast Box"), ""),
                new TrainClass("CC", "AC Chair Car (CC)", "Comfortable morning run.", 880.0, "₹", 40, 22, 0, 0, Arrays.asList("AC Seating", "Breakfast"), "")
            ), Arrays.asList("MONDAY", "TUESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"), Arrays.asList("Jaipur (07:50)", "Gandhinagar JPR (08:00)", "Alwar (09:40)", "Gurgaon (11:05)", "Delhi Cantt (11:35)")));

        addTrain(new Train("12016", "Ajmer Shatabdi Express", "Shatabdi Express", 4.8, 4400, 97, 4.8, 4.7,
            "JP", "Jaipur Junction", "NDLS", "New Delhi", "17:50", "22:40", "Platform 1", "Platform 2", "4h 50m", 308, "ON_TIME",
            Arrays.asList(
                new TrainClass("EC", "Executive Class", "Executive express with dinner.", 1450.0, "₹", 12, 5, 0, 0, Arrays.asList("Executive 2x2", "Dinner"), ""),
                new TrainClass("CC", "AC Chair Car", "Evening return train to capital.", 790.0, "₹", 40, 25, 0, 0, Arrays.asList("AC Seating", "Dinner Included"), "")
            ), Arrays.asList("DAILY"), Arrays.asList("Jaipur (17:50)", "Alwar (19:50)", "Rewari (21:05)", "Gurgaon (21:55)", "New Delhi (22:40)")));

        addTrain(new Train("12985", "Jaipur Double Decker", "Superfast Express", 4.7, 3900, 96, 4.7, 4.5,
            "JP", "Jaipur Junction", "NDLS", "New Delhi", "06:00", "10:25", "Platform 2", "Platform 5", "4h 25m", 308, "ON_TIME",
            Arrays.asList(
                new TrainClass("CC", "AC Chair Car", "Double decker morning commute.", 560.0, "₹", 80, 50, 0, 0, Arrays.asList("Double Decker AC"), "")
            ), Arrays.asList("DAILY"), Arrays.asList("Jaipur (06:00)", "Gandhinagar (06:12)", "Alwar (08:05)", "Delhi Sarai Rohilla (10:05)", "New Delhi (10:25)")));

        addTrain(new Train("12413", "Puja Superfast Express", "Superfast Express", 4.6, 2800, 95, 4.6, 4.4,
            "JP", "Jaipur Junction", "NDLS", "New Delhi", "16:10", "21:45", "Platform 3", "Platform 4", "5h 35m", 308, "ON_TIME",
            Arrays.asList(
                new TrainClass("2A", "AC 2-Tier (2A)", "Afternoon express.", 1180.0, "₹", 24, 12, 0, 0, Arrays.asList("Berths"), ""),
                new TrainClass("3A", "AC 3-Tier (3A)", "Standard 3A.", 780.0, "₹", 48, 28, 0, 0, Arrays.asList("AC Sleeper"), "")
            ), Arrays.asList("DAILY"), Arrays.asList("Jaipur (16:10)", "Dausa (16:55)", "Alwar (18:40)", "Delhi Cantt (21:15)", "Old Delhi (21:45)")));
    }

    private void addTrain(Train t) {
        initTrainSeats(t, t.getClasses().get(0).getId(), t.getClasses().size() > 1 ? t.getClasses().get(1).getId() : t.getClasses().get(0).getId());
        trains.put(t.getTrainNumber(), t);
    }

    private void initTrainSeats(Train train, String premiumClass, String standardClass) {
        List<Seat> seats = new ArrayList<>();
        String[] execSeatNums = {"14A", "14B", "15A", "15B", "16A", "16B", "17A", "17B", "18A", "18B", "19A", "19B"};
        for (int i = 0; i < execSeatNums.length; i++) {
            String seatNum = execSeatNums[i];
            boolean isWindow = seatNum.endsWith("A");
            boolean isAisle = seatNum.endsWith("B");
            String status = (i >= 4 && i < 8) ? "OCCUPIED" : "AVAILABLE";
            seats.add(new Seat("SEAT-" + train.getTrainNumber() + "-E1-" + seatNum, "E1", seatNum, premiumClass, status, isWindow, isAisle, Arrays.asList("180° Rotate", "Catering", "Power"), 2400.0));
        }

        String[] standardSeatNums = {
            "01A", "01B", "01C", "01D", "02A", "02B", "02C", "02D",
            "03A", "03B", "03C", "03D", "04A", "04B", "04C", "04D",
            "05A", "05B", "05C", "05D", "06A", "06B", "06C", "06D"
        };
        for (int i = 0; i < standardSeatNums.length; i++) {
            String seatNum = standardSeatNums[i];
            boolean isWindow = seatNum.endsWith("A") || seatNum.endsWith("D");
            boolean isAisle = seatNum.endsWith("B") || seatNum.endsWith("C");
            String status = (i % 3 == 0) ? "OCCUPIED" : "AVAILABLE";
            seats.add(new Seat("SEAT-" + train.getTrainNumber() + "-C1-" + seatNum, "C1", seatNum, standardClass, status, isWindow, isAisle, Arrays.asList("Power", "Recline"), 1285.0));
        }

        train.setSeats(seats);
    }

    private void initRecentSearches() {
        recentSearches.add(new RecentSearch("MAS", "MGR Chennai Central", "SBC", "KSR Bengaluru", "Popular Corridor", "2026-10-24"));
        recentSearches.add(new RecentSearch("NDLS", "New Delhi", "BSB", "Varanasi Junction", "High Demand", "2026-10-24"));
        recentSearches.add(new RecentSearch("MMCT", "Mumbai Central", "NDLS", "New Delhi", "Overnight Superfast", "2026-10-24"));
        recentSearches.add(new RecentSearch("ADI", "Ahmedabad Junction", "MMCT", "Mumbai Central", "Express Route", "2026-10-24"));
    }

    public List<Station> getAllStations() {
        return new ArrayList<>(stations.values());
    }

    public Station getStation(String code) {
        if (code == null) return null;
        return stations.get(code.toUpperCase());
    }

    public List<Train> getAllTrains() {
        return new ArrayList<>(trains.values());
    }

    public Train getTrain(String trainNumber) {
        if (trainNumber == null) return null;
        return trains.get(trainNumber);
    }

    public List<Train> searchTrains(String from, String to, String date) {
        String fromNorm = from != null ? from.trim().toUpperCase() : "";
        String toNorm = to != null ? to.trim().toUpperCase() : "";

        if (fromNorm.isEmpty() && toNorm.isEmpty()) {
            return new ArrayList<>(trains.values());
        }

        // 1. Direct matching trains
        List<Train> directMatches = trains.values().stream().filter(t -> {
            boolean fromMatch = fromNorm.isEmpty() ||
                t.getFromStationCode().equalsIgnoreCase(fromNorm) ||
                t.getFromStationName().toUpperCase().contains(fromNorm);

            boolean toMatch = toNorm.isEmpty() ||
                t.getToStationCode().equalsIgnoreCase(toNorm) ||
                t.getToStationName().toUpperCase().contains(toNorm);

            return fromMatch && toMatch;
        }).collect(Collectors.toList());

        if (directMatches.size() >= 4) {
            return directMatches;
        }

        // 2. If fewer than 4 direct matches, generate realistic superfast corridor services between the requested stations
        List<Train> results = new ArrayList<>(directMatches);
        Station fromStation = getStation(fromNorm);
        Station toStation = getStation(toNorm);

        String fromName = fromStation != null ? fromStation.getName() : fromNorm;
        String toName = toStation != null ? toStation.getName() : toNorm;

        // Realistic template trains to ensure 4+ high quality services
        String[][] templateServices = {
            {"20891", "Vande Bharat Express", "Vande Bharat Express", "06:00", "12:15", "6h 15m", "4.9", "4120", "98", "Platform 1", "Platform 2"},
            {"12295", "Rajdhani Express (Superfast)", "Rajdhani Express", "16:45", "07:30", "14h 45m", "4.8", "5310", "96", "Platform 2", "Platform 1"},
            {"12089", "Shatabdi Express (Intercity)", "Shatabdi Express", "07:15", "13:45", "6h 30m", "4.8", "4200", "97", "Platform 3", "Platform 4"},
            {"12681", "Superfast Mail/Express", "Superfast Express", "21:30", "08:15", "10h 45m", "4.7", "4900", "95", "Platform 4", "Platform 3"},
            {"22691", "Garib Rath Express", "Garib Rath Express", "19:15", "06:00", "10h 45m", "4.7", "3750", "95", "Platform 5", "Platform 2"}
        };

        for (String[] tpl : templateServices) {
            if (results.size() >= 4) break;
            String trainNo = tpl[0];
            // Check if already in direct matches
            boolean exists = results.stream().anyMatch(t -> t.getTrainNumber().equals(trainNo));
            if (!exists) {
                String name = tpl[1];
                String type = tpl[2];
                String depTime = tpl[3];
                String arrTime = tpl[4];
                String duration = tpl[5];
                double rating = Double.parseDouble(tpl[6]);
                int totalRatings = Integer.parseInt(tpl[7]);
                int punctuality = Integer.parseInt(tpl[8]);
                String depPlat = tpl[9];
                String arrPlat = tpl[10];

                List<TrainClass> classes = new ArrayList<>();
                if (type.contains("Vande Bharat") || type.contains("Shatabdi") || type.contains("Tejas")) {
                    classes.add(new TrainClass("EC", "Executive Class (EC)", "180° Rotatable luxury seating with gourmet catering.", 1950.0, "₹", 12, 6, 0, 0, Arrays.asList("180° Rotatable", "Gourmet Meals", "WiFi"), ""));
                    classes.add(new TrainClass("CC", "AC Chair Car (CC)", "Ergonomic AC seating with hot refreshments.", 980.0, "₹", 40, 22, 0, 0, Arrays.asList("AC Seating", "Refreshments"), ""));
                } else if (type.contains("Rajdhani")) {
                    classes.add(new TrainClass("1A", "First AC (1A)", "Private lockable coupe with gourmet meals.", 4250.0, "₹", 10, 3, 0, 0, Arrays.asList("Private Coupe", "Gourmet Meals"), ""));
                    classes.add(new TrainClass("2A", "AC 2-Tier (2A)", "Spacious 2-tier berths.", 2650.0, "₹", 24, 12, 0, 0, Arrays.asList("Privacy Curtains", "Dinner"), ""));
                    classes.add(new TrainClass("3A", "AC 3-Tier (3A)", "Popular 3-tier sleeper.", 1850.0, "₹", 48, 28, 0, 0, Arrays.asList("AC Sleeper", "Bedding"), ""));
                } else {
                    classes.add(new TrainClass("2A", "AC 2-Tier (2A)", "Comfortable 2-tier berths.", 2150.0, "₹", 24, 14, 0, 0, Arrays.asList("Berths"), ""));
                    classes.add(new TrainClass("3A", "AC 3-Tier (3A)", "Standard 3-tier sleeper.", 1450.0, "₹", 48, 30, 0, 0, Arrays.asList("AC Sleeper"), ""));
                    classes.add(new TrainClass("SL", "Sleeper Class (SL)", "Standard non-AC sleeper.", 520.0, "₹", 72, 45, 0, 0, Arrays.asList("Reserved Berth"), ""));
                }

                Train synTrain = new Train(
                    trainNo, name, type, rating, totalRatings, punctuality, 4.8, 4.7,
                    fromNorm, fromName, toNorm, toName,
                    depTime, arrTime, depPlat, arrPlat,
                    duration, 580, "ON_TIME",
                    classes,
                    Arrays.asList("DAILY"),
                    Arrays.asList(fromName + " (" + depTime + ")", "Major Junction Stop", toName + " (" + arrTime + ")")
                );
                initTrainSeats(synTrain, classes.get(0).getId(), classes.size() > 1 ? classes.get(1).getId() : classes.get(0).getId());
                trains.put(synTrain.getTrainNumber(), synTrain);
                results.add(synTrain);
            }
        }

        return results;
    }

    public List<RecentSearch> getRecentSearches() {
        return recentSearches;
    }

    public void addRecentSearch(RecentSearch search) {
        recentSearches.add(0, search);
        if (recentSearches.size() > 6) {
            recentSearches.remove(recentSearches.size() - 1);
        }
    }
}
