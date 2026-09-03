// GO TICKET Theme Manager - Emerald Green & Light/Dark Mode
(function() {
    const savedTheme = localStorage.getItem('goticket_theme') || localStorage.getItem('velocity_theme') || 'light';
    applyTheme(savedTheme);

    function applyTheme(theme) {
        const root = document.documentElement;
        if (theme === 'dark') {
            root.classList.add('dark');
            root.classList.remove('light');
        } else {
            root.classList.remove('dark');
            root.classList.add('light');
        }
        localStorage.setItem('goticket_theme', theme);
        localStorage.setItem('velocity_theme', theme);
        updateThemeToggleIcons(theme);
    }

    function toggleTheme() {
        const isDark = document.documentElement.classList.contains('dark');
        const nextTheme = isDark ? 'light' : 'dark';
        applyTheme(nextTheme);
    }

    function updateThemeToggleIcons(theme) {
        const btns = document.querySelectorAll('.theme-toggle-btn');
        btns.forEach(btn => {
            if (theme === 'dark') {
                btn.innerHTML = `
                    <span class="material-symbols-outlined text-emerald-400 text-[20px]">light_mode</span>
                    <span class="text-xs font-mono text-gray-200 hidden sm:inline">Light</span>
                `;
                btn.title = "Switch to Light Mode";
            } else {
                btn.innerHTML = `
                    <span class="material-symbols-outlined text-slate-700 text-[20px]">dark_mode</span>
                    <span class="text-xs font-mono text-gray-700 hidden sm:inline">Dark</span>
                `;
                btn.title = "Switch to Dark Mode";
            }
        });
    }

    window.GoTicketTheme = {
        applyTheme,
        toggleTheme
    };
    window.VelocityTheme = window.GoTicketTheme; // backwards compatibility

    document.addEventListener('DOMContentLoaded', () => {
        const current = localStorage.getItem('goticket_theme') || localStorage.getItem('velocity_theme') || 'light';
        updateThemeToggleIcons(current);
    });
})();
