# 🎟️ GO TICKET — Indian Railways Reservation & Management System

> **Book Trains. Track Journeys. Travel Smarter.**  
> A high-performance Java backend & responsive modern web application for searching Indian Railways services, comparing train routes, live seat availability, booking 10-digit PNR tickets, downloading Electronic Reservation Slips (ERS PDF), and managing cancellations & refunds.

---

## 🌟 Key Features

1. **67+ Real Indian Railway Stations**: Complete station database (e.g., `MAS`, `SBC`, `NDLS`, `BSB`, `MMCT`, `ADI`, `HWH`, `PUNE`, `SC`).
2. **Guaranteed 4+ Trains per Search**: Real express services (Vande Bharat Express, Rajdhani Express, Shatabdi Express, Superfast Mail).
3. **Dynamic Passenger Roster**: Supports 1 to 6+ manual passenger entries per booking with Full Name, Age, Gender, Berth Preference, Meal Preference, and Coach/Seat allocation.
4. **Authentic IRCTC Pricing & Calculations**:
   - Class-based base fares (EC, 1A, 2A, 3A, CC, SL, 2S).
   - 5% GST on AC travel (0% on Sleeper/2S).
   - Senior Citizen (40%) & Child (50%) concessions.
   - Convenience fee: ₹35.40 for AC, ₹17.70 for non-AC.
5. **High-Definition E-Ticket PDF Download**:
   - Generates official `Ticket_<PNR>.pdf` using `html2canvas` (scale 3) + `jsPDF`.
   - Real dynamically generated QR code via `qrcode.js`.
   - Inlined official green GO TICKET logo (zero CORS/clipping bugs).
6. **Cancellation & Itemized Refund Lifecycle**:
   - Real-time pre-cancellation refund calculation.
   - Unique Refund ID (`RFD-2026-XXXXXX`) and UTR reference.
   - Separate Upcoming vs. Cancelled trips tracking.
   - Downloadable Refund Receipt PDF.

---

## 🚀 Running Locally

### Prerequisites
- **Java 17+ / Java 21 JDK**

### Start in One Click (Windows)
```powershell
# Double-click or run:
.\run.ps1
# or
.\run.bat
```

### Manual Compile & Run
```bash
# Compile Java classes
mkdir -p backend/bin
javac -encoding UTF-8 -d backend/bin $(find backend/src/main/java -name "*.java")

# Start server on Port 8080
java -cp backend/bin com.velocity.VelocityServer 8080
```
Open **[http://localhost:8080](http://localhost:8080)** in your browser.

---

## 📦 Pushing to GitHub

If you have Git installed:

```bash
# 1. Initialize git
git init

# 2. Add files
git add .

# 3. Commit
git commit -m "feat: complete GO TICKET railway reservation system"

# 4. Set main branch & remote
git branch -M main
git remote add origin https://github.com/<YOUR_GITHUB_USERNAME>/<YOUR_REPO_NAME>.git

# 5. Push
git push -u origin main
```

*(Alternatively, drag and drop the folder contents into GitHub Web or GitHub Desktop).*

---

## ☁️ 1-Click Cloud Deployment

### Option 1: Deploy on Render.com (Recommended Free Hosting)
1. Go to [https://render.com](https://render.com) and click **New + Web Service**.
2. Connect your GitHub repository.
3. Select **Docker** environment.
4. Click **Deploy Web Service**.
5. Your application will be live at `https://<your-app-name>.onrender.com`.

### Option 2: Deploy on Railway.app
1. Go to [https://railway.app](https://railway.app) and click **New Project → Deploy from GitHub Repo**.
2. Select this repository.
3. Railway automatically detects the `Dockerfile` and deploys it.

### Option 3: Run with Docker locally
```bash
docker build -t goticket .
docker run -p 8080:8080 goticket
```
