# Quick Start Guide

Get started with Eventology India in just a few minutes!

## Prerequisites

- Flutter SDK 3.0+
- Git
- A code editor (VS Code recommended)
- Firebase account (free tier is fine)
- Google Cloud account for Gemini API

## 5-Minute Setup

### 1. Clone and Install (1 min)
```bash
git clone https://github.com/AyushT-5844/Eventology-India.git
cd Eventology-India
flutter pub get
```

### 2. Firebase Setup (2 min)
1. Create project at [Firebase Console](https://console.firebase.google.com)
2. Add your app (Android/iOS)
3. Download config files:
   - `google-services.json` → `android/app/`
   - `GoogleService-Info.plist` → `ios/Runner/`
4. Enable Authentication (Email/Password)
5. Create Firestore database

### 3. Get Gemini API Key (1 min)
1. Visit [Google AI Studio](https://makersuite.google.com/app/apikey)
2. Click "Create API Key"
3. Copy your key

### 4. Run the App (1 min)
```bash
# Run with API key
flutter run --dart-define=GEMINI_API_KEY=your_key_here

# Or for quick testing (AI features won't work)
flutter run
```

## First Steps After Launch

1. **Explore the UI**
   - Browse the 4 main tabs
   - Check out event categories
   - View vendor marketplace

2. **Test Authentication**
   - Create an account
   - Sign in/out
   - Test password validation

3. **Set Up Sample Data** (Optional)
   - Add sample events in Firestore
   - Add sample vendors
   - Test search and filters

## Key Features to Try

✨ **Event Discovery** - Browse event types and categories  
🏪 **Vendor Marketplace** - Explore vendor categories  
💰 **Budget Planner** - Plan your event budget (with AI)  
📊 **Dashboard** - Manage your events and bookings  

## Next Steps

- Read the [Full Documentation](../README.md)
- Set up [Firebase Rules](FIREBASE_SETUP.md)
- Configure [Gemini AI](GEMINI_AI_GUIDE.md)
- Check [Architecture](ARCHITECTURE.md)
- Review [API Documentation](API_DOCUMENTATION.md)

## Common Issues

**Issue**: App won't build  
**Fix**: Run `flutter clean && flutter pub get`

**Issue**: Firebase not connecting  
**Fix**: Verify config files are in correct locations

**Issue**: Gemini AI not working  
**Fix**: Check your API key is valid and set correctly

## Getting Help

- Check the [README](../README.md)
- Review [Documentation](.)
- Open an [Issue](https://github.com/AyushT-5844/Eventology-India/issues)

Happy Event Planning! 🎉
