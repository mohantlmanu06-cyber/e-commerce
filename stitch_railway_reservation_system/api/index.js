// Vercel Serverless Function Handler for GO TICKET API
// Supports: Station queries, Real Trains search (4+ trains), Dynamic Pricing, Bookings, E-Tickets & Refunds

const STATIONS = [
    { code: "NDLS", name: "New Delhi Railway Station", city: "New Delhi", state: "Delhi", zone: "NR", platforms: ["Platform 1", "Platform 2", "Platform 16"] },
    { code: "MAS", name: "MGR Chennai Central", city: "Chennai", state: "Tamil Nadu", zone: "SR", platforms: ["Platform 1", "Platform 5", "Platform 11"] },
    { code: "SBC", name: "KSR Bengaluru", city: "Bengaluru", state: "Karnataka", zone: "SWR", platforms: ["Platform 1", "Platform 7", "Platform 10"] },
    { code: "MMCT", name: "Mumbai Central", city: "Mumbai", state: "Maharashtra", zone: "WR", platforms: ["Platform 1", "Platform 2", "Platform 5"] },
    { code: "BSB", name: "Varanasi Junction", city: "Varanasi", state: "Uttar Pradesh", zone: "NR", platforms: ["Platform 1", "Platform 8", "Platform 9"] },
    { code: "HWH", name: "Howrah Junction", city: "Kolkata", state: "West Bengal", zone: "ER", platforms: ["Platform 8", "Platform 9", "Platform 22"] },
    { code: "ADI", name: "Ahmedabad Junction", city: "Ahmedabad", state: "Gujarat", zone: "WR", platforms: ["Platform 1", "Platform 5", "Platform 8"] },
    { code: "PUNE", name: "Pune Junction", city: "Pune", state: "Maharashtra", zone: "CR", platforms: ["Platform 1", "Platform 2", "Platform 3"] },
    { code: "SC", name: "Secunderabad Junction", city: "Hyderabad", state: "Telangana", zone: "SCR", platforms: ["Platform 1", "Platform 2", "Platform 10"] },
    { code: "VSKP", name: "Visakhapatnam", city: "Visakhapatnam", state: "Andhra Pradesh", zone: "ECoR", platforms: ["Platform 1", "Platform 4", "Platform 8"] },
    { code: "JP", name: "Jaipur Junction", city: "Jaipur", state: "Rajasthan", zone: "NWR", platforms: ["Platform 1", "Platform 2", "Platform 3"] },
    { code: "LKO", name: "Lucknow Charbagh", city: "Lucknow", state: "Uttar Pradesh", zone: "NR", platforms: ["Platform 1", "Platform 2", "Platform 6"] },
    { code: "CNB", name: "Kanpur Central", city: "Kanpur", state: "Uttar Pradesh", zone: "NCR", platforms: ["Platform 1", "Platform 4"] },
    { code: "PRYJ", name: "Prayagraj Junction", city: "Prayagraj", state: "Uttar Pradesh", zone: "NCR", platforms: ["Platform 1", "Platform 2"] }
];

const TRAINS = [
    // MAS -> SBC
    {
        trainNumber: "20608", name: "Vande Bharat Express", trainType: "Vande Bharat Express", rating: 4.9, totalRatings: 3940, punctualityScore: 99,
        fromStationCode: "MAS", fromStationName: "MGR Chennai Central", toStationCode: "SBC", toStationName: "KSR Bengaluru",
        departureTime: "05:50", arrivalTime: "10:20", departurePlatform: "Platform 5", arrivalPlatform: "Platform 1",
        duration: "4h 30m", distanceKm: 359, status: "ON_TIME",
        classes: [
            { id: "EC", name: "Executive Class (EC)", price: 1850, currency: "₹", totalSeats: 52, availableSeats: 6, availabilityStatus: "FILLING_FAST" },
            { id: "CC", name: "AC Chair Car (CC)", price: 950, currency: "₹", totalSeats: 400, availableSeats: 24, availabilityStatus: "AVAILABLE" }
        ],
        intermediateStops: ["MGR Chennai Central (05:50)", "Katpadi Jn (07:13)", "Bengaluru Cantt (09:48)", "KSR Bengaluru (10:20)"]
    },
    {
        trainNumber: "12028", name: "Bengaluru Shatabdi Express", trainType: "Shatabdi Express", rating: 4.8, totalRatings: 5120, punctualityScore: 97,
        fromStationCode: "MAS", fromStationName: "MGR Chennai Central", toStationCode: "SBC", toStationName: "KSR Bengaluru",
        departureTime: "06:00", arrivalTime: "11:00", departurePlatform: "Platform 2", arrivalPlatform: "Platform 7",
        duration: "5h 00m", distanceKm: 359, status: "ON_TIME",
        classes: [
            { id: "EC", name: "Executive Class (EC)", price: 1650, currency: "₹", totalSeats: 52, availableSeats: 4, availabilityStatus: "FEW_SEATS" },
            { id: "CC", name: "AC Chair Car (CC)", price: 850, currency: "₹", totalSeats: 400, availableSeats: 19, availabilityStatus: "AVAILABLE" }
        ],
        intermediateStops: ["MGR Chennai Central (06:00)", "Katpadi (07:38)", "Jolarpettai (08:50)", "KSR Bengaluru (11:00)"]
    },
    {
        trainNumber: "12639", name: "Brindavan Superfast Express", trainType: "Superfast Express", rating: 4.7, totalRatings: 6200, punctualityScore: 96,
        fromStationCode: "MAS", fromStationName: "MGR Chennai Central", toStationCode: "SBC", toStationName: "KSR Bengaluru",
        departureTime: "07:40", arrivalTime: "13:40", departurePlatform: "Platform 8", arrivalPlatform: "Platform 2",
        duration: "6h 00m", distanceKm: 359, status: "ON_TIME",
        classes: [
            { id: "CC", name: "AC Chair Car (CC)", price: 620, currency: "₹", totalSeats: 120, availableSeats: 32, availabilityStatus: "AVAILABLE" },
            { id: "2S", name: "Second Sitting (2S)", price: 160, currency: "₹", totalSeats: 600, availableSeats: 45, availabilityStatus: "AVAILABLE" }
        ],
        intermediateStops: ["MGR Chennai Central (07:40)", "Arakkonam (08:38)", "Katpadi (09:28)", "KSR Bengaluru (13:40)"]
    },
    {
        trainNumber: "12607", name: "Lalbagh Superfast Express", trainType: "Superfast Express", rating: 4.7, totalRatings: 5400, punctualityScore: 96,
        fromStationCode: "MAS", fromStationName: "MGR Chennai Central", toStationCode: "SBC", toStationName: "KSR Bengaluru",
        departureTime: "15:30", arrivalTime: "21:35", departurePlatform: "Platform 4", arrivalPlatform: "Platform 1",
        duration: "6h 05m", distanceKm: 359, status: "ON_TIME",
        classes: [
            { id: "CC", name: "AC Chair Car (CC)", price: 620, currency: "₹", totalSeats: 120, availableSeats: 15, availabilityStatus: "AVAILABLE" },
            { id: "2S", name: "Second Sitting (2S)", price: 160, currency: "₹", totalSeats: 600, availableSeats: 28, availabilityStatus: "AVAILABLE" }
        ],
        intermediateStops: ["MGR Chennai Central (15:30)", "Katpadi (17:28)", "Jolarpettai (18:48)", "KSR Bengaluru (21:35)"]
    },

    // NDLS -> BSB
    {
        trainNumber: "22436", name: "Vande Bharat Express", trainType: "Vande Bharat Express", rating: 4.9, totalRatings: 4820, punctualityScore: 98,
        fromStationCode: "NDLS", fromStationName: "New Delhi", toStationCode: "BSB", toStationName: "Varanasi Junction",
        departureTime: "06:00", arrivalTime: "14:00", departurePlatform: "Platform 16", arrivalPlatform: "Platform 1",
        duration: "8h 00m", distanceKm: 759, status: "ON_TIME",
        classes: [
            { id: "EC", name: "Executive Class (EC)", price: 2400, currency: "₹", totalSeats: 52, availableSeats: 14, availabilityStatus: "AVAILABLE" },
            { id: "CC", name: "AC Chair Car (CC)", price: 1285, currency: "₹", totalSeats: 400, availableSeats: 6, availabilityStatus: "FEW_SEATS" }
        ],
        intermediateStops: ["New Delhi (06:00)", "Kanpur Central (10:08)", "Prayagraj Jn (12:08)", "Varanasi Jn (14:00)"]
    },
    {
        trainNumber: "22416", name: "Vande Bharat 2.0", trainType: "Vande Bharat Express", rating: 4.9, totalRatings: 3910, punctualityScore: 99,
        fromStationCode: "NDLS", fromStationName: "New Delhi", toStationCode: "BSB", toStationName: "Varanasi Junction",
        departureTime: "15:00", arrivalTime: "23:05", departurePlatform: "Platform 16", arrivalPlatform: "Platform 1",
        duration: "8h 05m", distanceKm: 759, status: "ON_TIME",
        classes: [
            { id: "EC", name: "Executive Class (EC)", price: 2400, currency: "₹", totalSeats: 52, availableSeats: 8, availabilityStatus: "AVAILABLE" },
            { id: "CC", name: "AC Chair Car (CC)", price: 1285, currency: "₹", totalSeats: 400, availableSeats: 18, availabilityStatus: "AVAILABLE" }
        ],
        intermediateStops: ["New Delhi (15:00)", "Kanpur Central (19:08)", "Prayagraj (21:11)", "Varanasi (23:05)"]
    },
    {
        trainNumber: "12560", name: "Shiv Ganga Superfast Express", trainType: "Superfast Express", rating: 4.7, totalRatings: 4620, punctualityScore: 96,
        fromStationCode: "NDLS", fromStationName: "New Delhi", toStationCode: "BSB", toStationName: "Varanasi Junction",
        departureTime: "20:05", arrivalTime: "06:10", departurePlatform: "Platform 12", arrivalPlatform: "Platform 8",
        duration: "10h 05m", distanceKm: 759, status: "ON_TIME",
        classes: [
            { id: "1A", name: "First AC (1A)", price: 3850, currency: "₹", totalSeats: 24, availableSeats: 2, availabilityStatus: "FEW_SEATS" },
            { id: "2A", name: "AC 2-Tier (2A)", price: 2350, currency: "₹", totalSeats: 96, availableSeats: 12, availabilityStatus: "AVAILABLE" },
            { id: "3A", name: "AC 3-Tier (3A)", price: 1680, currency: "₹", totalSeats: 256, availableSeats: 30, availabilityStatus: "AVAILABLE" }
        ],
        intermediateStops: ["New Delhi (20:05)", "Kanpur (01:00)", "Prayagraj (03:45)", "Varanasi (06:10)"]
    },
    {
        trainNumber: "12582", name: "Banaras Superfast Express", trainType: "Superfast Express", rating: 4.6, totalRatings: 3420, punctualityScore: 95,
        fromStationCode: "NDLS", fromStationName: "New Delhi", toStationCode: "BSB", toStationName: "Varanasi Junction",
        departureTime: "22:50", arrivalTime: "10:00", departurePlatform: "Platform 15", arrivalPlatform: "Platform 9",
        duration: "11h 10m", distanceKm: 759, status: "ON_TIME",
        classes: [
            { id: "2A", name: "AC 2-Tier (2A)", price: 2250, currency: "₹", totalSeats: 96, availableSeats: 6, availabilityStatus: "FEW_SEATS" },
            { id: "3A", name: "AC 3-Tier (3A)", price: 1550, currency: "₹", totalSeats: 256, availableSeats: 22, availabilityStatus: "AVAILABLE" }
        ],
        intermediateStops: ["New Delhi (22:50)", "Aligarh (00:40)", "Kanpur (04:50)", "Varanasi (10:00)"]
    }
];

// Global in-memory serverless bookings store
let memoryBookings = [];

module.exports = async (req, res) => {
    // Enable CORS
    res.setHeader('Access-Control-Allow-Credentials', true);
    res.setHeader('Access-Control-Allow-Origin', '*');
    res.setHeader('Access-Control-Allow-Methods', 'GET,OPTIONS,PATCH,DELETE,POST,PUT');
    res.setHeader('Access-Control-Allow-Headers', 'X-CSRF-Token, X-Requested-With, Accept, Accept-Version, Content-Length, Content-MD5, Content-Type, Date, X-Api-Version');

    if (req.method === 'OPTIONS') {
        return res.status(200).end();
    }

    const url = new URL(req.url, `http://${req.headers.host || 'localhost'}`);
    const pathname = url.pathname;

    // 1. Health Check
    if (pathname === '/api/health') {
        return res.status(200).json({
            success: true,
            message: "Success",
            data: { service: "GO TICKET - Vercel Serverless Backend", version: "2.0.0", status: "UP", timestamp: Date.now() }
        });
    }

    // 2. Stations
    if (pathname === '/api/stations') {
        return res.status(200).json({ success: true, message: "Success", data: STATIONS });
    }

    // 3. Train Search (Guarantees 4+ trains)
    if (pathname === '/api/trains/search') {
        const from = (url.searchParams.get('from') || '').toUpperCase();
        const to = (url.searchParams.get('to') || '').toUpperCase();
        
        let matches = TRAINS.filter(t => {
            const fMatch = !from || t.fromStationCode === from || t.fromStationName.toUpperCase().includes(from);
            const tMatch = !to || t.toStationCode === to || t.toStationName.toUpperCase().includes(to);
            return fMatch && tMatch;
        });

        // If fewer than 4 matches, generate realistic corridor trains
        if (matches.length < 4 && from && to) {
            const fromSt = STATIONS.find(s => s.code === from) || { name: from };
            const toSt = STATIONS.find(s => s.code === to) || { name: to };
            const templates = [
                { num: "20891", name: "Vande Bharat Express", type: "Vande Bharat Express", dep: "06:00", arr: "12:15", dur: "6h 15m", price: 1950, cc: 980 },
                { num: "12295", name: "Rajdhani Express (Superfast)", type: "Rajdhani Express", dep: "16:45", arr: "07:30", dur: "14h 45m", price: 4250, cc: 2650 },
                { num: "12089", name: "Shatabdi Express (Intercity)", type: "Shatabdi Express", dep: "07:15", arr: "13:45", dur: "6h 30m", price: 1650, cc: 850 },
                { num: "12681", name: "Superfast Mail/Express", type: "Superfast Express", dep: "21:30", arr: "08:15", dur: "10h 45m", price: 2150, cc: 1450 }
            ];

            for (const tpl of templates) {
                if (matches.length >= 4) break;
                if (!matches.some(m => m.trainNumber === tpl.num)) {
                    matches.push({
                        trainNumber: tpl.num, name: tpl.name, trainType: tpl.type, rating: 4.8, totalRatings: 4200, punctualityScore: 97,
                        fromStationCode: from, fromStationName: fromSt.name, toStationCode: to, toStationName: toSt.name,
                        departureTime: tpl.dep, arrivalTime: tpl.arr, departurePlatform: "Platform 1", arrivalPlatform: "Platform 2",
                        duration: tpl.dur, distanceKm: 580, status: "ON_TIME",
                        classes: [
                            { id: "EC", name: "Executive Class (EC)", price: tpl.price, currency: "₹", totalSeats: 52, availableSeats: 8, availabilityStatus: "AVAILABLE" },
                            { id: "CC", name: "AC Chair Car (CC)", price: tpl.cc, currency: "₹", totalSeats: 400, availableSeats: 22, availabilityStatus: "AVAILABLE" }
                        ],
                        intermediateStops: [`${fromSt.name} (${tpl.dep})`, "Major Junction Stop", `${toSt.name} (${tpl.arr})`]
                    });
                }
            }
        }

        return res.status(200).json({ success: true, message: "Success", data: matches.length > 0 ? matches : TRAINS });
    }

    // 4. Single Train
    if (pathname.startsWith('/api/trains/')) {
        const trainNo = pathname.split('/')[3];
        const train = TRAINS.find(t => t.trainNumber === trainNo) || TRAINS[0];
        return res.status(200).json({ success: true, message: "Success", data: train });
    }

    // 5. Recent Searches
    if (pathname === '/api/searches/recent') {
        return res.status(200).json({
            success: true,
            data: [
                { fromCode: "MAS", fromName: "MGR Chennai Central", toCode: "SBC", toName: "KSR Bengaluru", timeAgo: "Popular Corridor", date: "2026-10-24" },
                { fromCode: "NDLS", fromName: "New Delhi", toCode: "BSB", toName: "Varanasi Junction", timeAgo: "High Demand", date: "2026-10-24" },
                { fromCode: "MMCT", fromName: "Mumbai Central", toCode: "NDLS", toName: "New Delhi", timeAgo: "Overnight Superfast", date: "2026-10-24" },
                { fromCode: "ADI", fromName: "Ahmedabad Junction", toCode: "MMCT", toName: "Mumbai Central", timeAgo: "Express Route", date: "2026-10-24" }
            ]
        });
    }

    // 6. Pricing Calculation
    if (pathname === '/api/pricing/calculate' && req.method === 'POST') {
        const body = req.body || {};
        const train = TRAINS.find(t => t.trainNumber === body.trainNumber) || TRAINS[0];
        const cls = (train.classes && train.classes.find(c => c.id === body.classId)) || (train.classes && train.classes[0]) || { price: 1850, id: "EC" };
        const passengers = body.passengers || [{ passengerType: "ADULT" }];
        
        let totalBase = 0;
        let adultCount = 0;
        let childCount = 0;
        let seniorCount = 0;

        for (const p of passengers) {
            if (p.passengerType === 'CHILD') { totalBase += cls.price * 0.5; childCount++; }
            else if (p.passengerType === 'SENIOR_CITIZEN') { totalBase += cls.price * 0.6; seniorCount++; }
            else { totalBase += cls.price; adultCount++; }
        }

        const isAc = cls.id !== 'SL' && cls.id !== '2S';
        const gst = isAc ? Math.round(totalBase * 0.05 * 100) / 100 : 0;
        const convFee = isAc ? 35.40 : 17.70;
        const total = Math.round((totalBase + gst + convFee) * 100) / 100;

        const breakdown = [];
        if (adultCount > 0) breakdown.push({ label: `Base Fare (${adultCount} Adult${adultCount > 1 ? 's' : ''})`, amount: cls.price * adultCount, formattedAmount: `₹${(cls.price * adultCount).toFixed(2)}` });
        if (childCount > 0) breakdown.push({ label: `Child Fare (50% Concession x ${childCount})`, amount: cls.price * 0.5 * childCount, formattedAmount: `₹${(cls.price * 0.5 * childCount).toFixed(2)}` });
        if (seniorCount > 0) breakdown.push({ label: `Senior Fare (40% Concession x ${seniorCount})`, amount: cls.price * 0.6 * seniorCount, formattedAmount: `₹${(cls.price * 0.6 * seniorCount).toFixed(2)}` });
        if (isAc) breakdown.push({ label: "GST (5% Indian Railways AC Service)", amount: gst, formattedAmount: `₹${gst.toFixed(2)}` });
        breakdown.push({ label: "IRCTC Convenience Fee (incl. PG Charges)", amount: convFee, formattedAmount: `₹${convFee.toFixed(2)}` });

        return res.status(200).json({
            success: true,
            data: { baseFare: totalBase, taxesAndFees: gst + convFee, discounts: 0, totalAmount: total, currency: "₹", breakdown }
        });
    }

    // 7. Create Booking
    if (pathname === '/api/bookings' && req.method === 'POST') {
        const body = req.body || {};
        const train = TRAINS.find(t => t.trainNumber === body.trainNumber) || TRAINS[0];
        const cls = (train.classes && train.classes.find(c => c.id === body.classId)) || (train.classes && train.classes[0]) || { name: "Executive Class (EC)", id: "EC", price: 1850 };
        
        const randFirst3 = Math.floor(200 + Math.random() * 800);
        const randLast7 = Math.floor(1000000 + Math.random() * 9000000);
        const pnr = `${randFirst3}-${randLast7}`;

        let totalBase = 0;
        const passList = (body.passengers || []).map((p, idx) => {
            const age = p.age || 30;
            let fare = cls.price;
            let type = p.passengerType || (age >= 60 ? 'SENIOR_CITIZEN' : (age < 12 ? 'CHILD' : 'ADULT'));
            if (type === 'CHILD') fare *= 0.5;
            else if (type === 'SENIOR_CITIZEN') fare *= 0.6;
            totalBase += fare;

            return {
                id: `P-${Date.now()}-${idx + 1}`,
                firstName: p.firstName || "Passenger",
                lastName: p.lastName || `${idx + 1}`,
                age: age,
                gender: p.gender || "MALE",
                berthPreference: p.berthPreference || "WINDOW",
                mealPreference: p.mealPreference || "VEG",
                passengerType: type,
                seatNumber: p.seatNumber || `${14 + idx}A`,
                car: body.car || "E1",
                status: "CONFIRMED",
                fare: fare
            };
        });

        const isAc = cls.id !== 'SL' && cls.id !== '2S';
        const gst = isAc ? Math.round(totalBase * 0.05 * 100) / 100 : 0;
        const convFee = isAc ? 35.40 : 17.70;
        const total = Math.round((totalBase + gst + convFee) * 100) / 100;

        const newBooking = {
            pnr: pnr,
            trainNumber: train.trainNumber,
            trainName: train.name,
            trainType: train.trainType,
            trainRating: train.rating,
            fromStationCode: train.fromStationCode,
            fromStationName: train.fromStationName,
            toStationCode: train.toStationCode,
            toStationName: train.toStationName,
            departureTime: train.departureTime,
            arrivalTime: train.arrivalTime,
            departurePlatform: train.departurePlatform,
            arrivalPlatform: train.arrivalPlatform,
            journeyDate: body.journeyDate || "2026-10-24",
            travelClass: cls.name,
            travelClassId: cls.id,
            car: body.car || "E1",
            seatNumbers: passList.map(p => p.seatNumber),
            passengers: passList,
            baseFare: totalBase,
            taxesAndFees: gst,
            convenienceFee: convFee,
            totalAmount: total,
            currency: "₹",
            status: "CONFIRMED",
            paymentMode: body.paymentMode || "UPI",
            createdAt: new Date().toISOString(),
            contactEmail: body.contactEmail || "traveller@example.in",
            contactPhone: body.contactPhone || "+91 98765 43210",
            qrCodeData: `IRCTC:PNR=${pnr}:TRN=${train.trainNumber}:DATE=${body.journeyDate}:HASH=${Math.abs(pnr.split('').reduce((a,b)=>{a=((a<<5)-a)+b.charCodeAt(0);return a&a},0))}`,
            refundDetails: null
        };

        memoryBookings.unshift(newBooking);
        return res.status(200).json({ success: true, message: "Booking confirmed", data: newBooking });
    }

    // 8. Get Bookings
    if (pathname === '/api/bookings' && req.method === 'GET') {
        const filter = (url.searchParams.get('filter') || 'ALL').toUpperCase();
        let list = memoryBookings;
        if (filter === 'UPCOMING') list = memoryBookings.filter(b => b.status !== 'CANCELLED');
        else if (filter === 'CANCELLED') list = memoryBookings.filter(b => b.status === 'CANCELLED');
        return res.status(200).json({ success: true, data: list });
    }

    // 9. Cancel Preview
    if (pathname.includes('/cancel-preview')) {
        const pnr = pathname.split('/')[3];
        const b = memoryBookings.find(x => x.pnr === pnr) || { totalAmount: 1977.90, pnr };
        const passCount = (b.passengers && b.passengers.length) || 1;
        const cancFee = 240 * passCount;
        const conv = 35.40;
        const refundAmt = Math.max(0, b.totalAmount - cancFee - conv);

        return res.status(200).json({
            success: true,
            data: {
                pnr: b.pnr,
                originalFare: b.totalAmount,
                cancellationFee: cancFee,
                clerkageFee: 0,
                nonRefundableConvenienceFee: conv,
                finalRefundAmount: refundAmt,
                refundNotice: `You will receive ₹${refundAmt.toFixed(2)} refund`
            }
        });
    }

    // 10. Cancel Booking
    if (pathname.includes('/cancel') && req.method === 'POST') {
        const pnr = pathname.split('/')[3];
        const b = memoryBookings.find(x => x.pnr === pnr);
        if (b) {
            b.status = 'CANCELLED';
            b.cancelledAt = new Date().toISOString();
            const passCount = (b.passengers && b.passengers.length) || 1;
            const cancFee = 240 * passCount;
            const conv = 35.40;
            const refundAmt = Math.max(0, b.totalAmount - cancFee - conv);

            b.refundDetails = {
                refundId: `RFD-2026-${Math.floor(100000 + Math.random() * 900000)}`,
                originalFare: b.totalAmount,
                cancellationCharge: cancFee,
                clerkageCharge: 0,
                convenienceFeeDeducted: conv,
                refundAmount: refundAmt,
                refundStatus: "INITIATED",
                initiatedAt: new Date().toISOString(),
                paymentMode: b.paymentMode || "UPI",
                expectedTimeline: "3 - 5 Banking Working Days",
                refundTransactionRef: `TXN-UPI-${Math.random().toString(36).substring(2, 9).toUpperCase()}`
            };
            return res.status(200).json({ success: true, message: "Ticket cancelled and refund initiated", data: b });
        }
        return res.status(404).json({ success: false, message: "Booking not found" });
    }

    return res.status(404).json({ success: false, message: "Route not found" });
};
