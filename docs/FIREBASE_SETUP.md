# Firebase Setup Guide

This guide will help you set up Firebase for Eventology India.

## Step 1: Create Firebase Project

1. Go to [Firebase Console](https://console.firebase.google.com)
2. Click "Add Project"
3. Enter project name: `eventology-india`
4. Enable Google Analytics (optional)
5. Click "Create Project"

## Step 2: Add Firebase to Your App

### For Android:
1. Click the Android icon in Firebase Console
2. Register app with package name: `com.eventology.india`
3. Download `google-services.json`
4. Place it in `android/app/` directory
5. Follow the SDK setup instructions

### For iOS:
1. Click the iOS icon in Firebase Console
2. Register app with bundle ID: `com.eventology.india`
3. Download `GoogleService-Info.plist`
4. Place it in `ios/Runner/` directory
5. Follow the SDK setup instructions

## Step 3: Enable Authentication

1. Go to "Authentication" in Firebase Console
2. Click "Get Started"
3. Enable "Email/Password" sign-in method
4. (Optional) Enable other providers:
   - Google Sign-In
   - Phone Authentication
   - Apple Sign-In

## Step 4: Create Firestore Database

1. Go to "Firestore Database" in Firebase Console
2. Click "Create Database"
3. Choose "Start in production mode"
4. Select location closest to your users (e.g., `asia-south1` for India)

## Step 5: Deploy Security Rules

```bash
# Deploy Firestore rules
firebase deploy --only firestore:rules

# Deploy Storage rules
firebase deploy --only storage:rules

# Deploy Firestore indexes
firebase deploy --only firestore:indexes
```

## Step 6: Set up Firebase Storage

1. Go to "Storage" in Firebase Console
2. Click "Get Started"
3. Start in production mode
4. Choose the same location as Firestore

## Resources

- [Firebase Documentation](https://firebase.google.com/docs)
- [FlutterFire Documentation](https://firebase.flutter.dev)
- [Firebase Console](https://console.firebase.google.com)
