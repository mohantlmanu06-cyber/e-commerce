// RailOne - Indian Railways REST API Client
const RailOneAPI = {
    baseUrl: '/api',

    async getStations() {
        const res = await fetch(`${this.baseUrl}/stations`);
        return res.json();
    },

    async getStation(code) {
        const res = await fetch(`${this.baseUrl}/stations/${encodeURIComponent(code)}`);
        return res.json();
    },

    async searchTrains(from = '', to = '', date = '') {
        const params = new URLSearchParams();
        if (from) params.append('from', from);
        if (to) params.append('to', to);
        if (date) params.append('date', date);
        const res = await fetch(`${this.baseUrl}/trains/search?${params.toString()}`);
        return res.json();
    },

    async getTrain(trainNumber) {
        const res = await fetch(`${this.baseUrl}/trains/${encodeURIComponent(trainNumber)}`);
        return res.json();
    },

    async getTrainSeats(trainNumber) {
        const res = await fetch(`${this.baseUrl}/trains/${encodeURIComponent(trainNumber)}/seats`);
        return res.json();
    },

    async calculatePrice(priceRequest) {
        const res = await fetch(`${this.baseUrl}/pricing/calculate`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(priceRequest)
        });
        return res.json();
    },

    async createBooking(bookingRequest) {
        const res = await fetch(`${this.baseUrl}/bookings`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(bookingRequest)
        });
        return res.json();
    },

    async getBookings(filter = '') {
        const query = filter ? `?filter=${encodeURIComponent(filter)}` : '';
        const res = await fetch(`${this.baseUrl}/bookings${query}`);
        return res.json();
    },

    async getBookingByPnr(pnr) {
        const res = await fetch(`${this.baseUrl}/bookings/${encodeURIComponent(pnr)}`);
        return res.json();
    },

    async previewCancellation(pnr) {
        const res = await fetch(`${this.baseUrl}/bookings/${encodeURIComponent(pnr)}/cancel-preview`);
        return res.json();
    },

    async cancelBooking(pnr) {
        const res = await fetch(`${this.baseUrl}/bookings/${encodeURIComponent(pnr)}/cancel`, {
            method: 'POST'
        });
        return res.json();
    },

    async getRecentSearches() {
        const res = await fetch(`${this.baseUrl}/recent-searches`);
        return res.json();
    }
};

const GoTicketAPI = RailOneAPI;
const VelocityAPI = RailOneAPI; // Backwards compatibility
