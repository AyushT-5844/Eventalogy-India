# Eventology India 🎉

A mobile-first event planning and booking platform connecting clients with verified vendors across India.

## 🌟 Overview

Eventology India is a comprehensive EventTech solution that combines event planning, vendor marketplace, and AI-powered assistance to make event management seamless and efficient. Built with Flutter and powered by Firebase and Google's Gemini AI.

## ✨ Features

### Core Features
- **Event Discovery** - Browse and discover various events with smart filtering
- **Budget Planning** - AI-powered budget allocation and planning tools
- **Vendor Marketplace** - Connect with verified vendors across 14+ categories
- **Management Dashboard** - Track events, bookings, and vendor relationships
- **Premium Services** - Access to premium vendors and exclusive features

### AI Integration
- **Gemini AI Assistant** - Intelligent event planning suggestions
- **Smart Recommendations** - AI-driven vendor recommendations
- **Budget Optimization** - Automated budget breakdown and optimization
- **Event Checklist Generation** - Dynamic timeline-based planning checklists

### Technical Features
- Real-time updates with Firebase Firestore
- Secure authentication with Firebase Auth
- Cloud storage for images and documents
- Responsive UI with Material Design 3
- Offline support and data caching

## 🏗️ Tech Stack

- **Frontend**: Flutter 3.0+
- **Mobile Development**: FlutterFlow (for rapid prototyping)
- **Backend & Database**: Firebase
  - Firestore (NoSQL database)
  - Firebase Authentication
  - Firebase Storage
  - Firebase Analytics
- **AI Integration**: Google Gemini API
- **State Management**: Provider & Riverpod
- **API Routing**: Firebase AI Stack

## 📱 Supported Event Types

- Weddings
- Corporate Events
- Birthday Parties
- Anniversaries
- Conferences
- Trade Shows
- Concerts & Festivals
- Workshops & Seminars
- Social Gatherings
- Religious Ceremonies
- And more...

## 🏪 Vendor Categories

- Venues
- Catering Services
- Photography & Videography
- Decoration & Design
- Entertainment & DJ Services
- Makeup & Mehendi Artists
- Choreography & Dance
- Transportation Services
- Invitations & Printing
- Gifts & Favors
- Professional Event Planning

## 🚀 Getting Started

### Prerequisites

- Flutter SDK (3.0.0 or higher)
- Dart SDK (3.0.0 or higher)
- Firebase account
- Google Cloud account (for Gemini API)
- Android Studio / Xcode for mobile development
- A code editor (VS Code recommended)

### Installation

1. **Clone the repository**
   ```bash
   git clone https://github.com/AyushT-5844/Eventology-India.git
   cd Eventology-India
   ```

2. **Install Flutter dependencies**
   ```bash
   flutter pub get
   ```

3. **Set up Firebase**
   - Create a new Firebase project at [Firebase Console](https://console.firebase.google.com)
   - Enable Authentication, Firestore, and Storage
   - Download `google-services.json` (Android) and `GoogleService-Info.plist` (iOS)
   - Place them in the appropriate directories

4. **Configure API Keys**
   - Get your Gemini API key from [Google AI Studio](https://makersuite.google.com/app/apikey)
   - Update `lib/config/api_config.dart` with your keys
   - **Never commit API keys to version control!**

5. **Deploy Firebase Rules**
   ```bash
   firebase deploy --only firestore:rules
   firebase deploy --only storage:rules
   firebase deploy --only firestore:indexes
   ```

6. **Run the app**
   ```bash
   flutter run
   ```

## 📁 Project Structure

```
lib/
├── config/           # App configuration and API keys
├── models/           # Data models (Event, Vendor, Booking, UserProfile)
├── services/         # Business logic and API services
│   ├── auth_service.dart
│   ├── event_service.dart
│   ├── vendor_service.dart
│   └── gemini_service.dart
├── screens/          # UI screens
│   ├── auth/
│   ├── home_screen.dart
│   └── splash_screen.dart
├── widgets/          # Reusable UI components
├── utils/            # Utility functions and helpers
└── main.dart         # App entry point

firebase/
├── firestore.rules   # Firestore security rules
├── firestore.indexes.json  # Database indexes
└── storage.rules     # Storage security rules

assets/
├── images/           # Image assets
├── icons/            # Custom icons
└── config/           # Configuration files
```

## 🔐 Security

- Firebase Authentication for secure user management
- Firestore security rules for data access control
- Storage rules for file upload validation
- Environment variables for sensitive data
- No hardcoded API keys in source code

## 🎨 UI/UX Design

- Material Design 3 with custom Indian cultural theme
- Responsive layouts for all screen sizes
- Dark mode support
- Smooth animations and transitions
- Accessible design following WCAG guidelines

## 🧪 Testing

```bash
# Run unit tests
flutter test

# Run integration tests
flutter test integration_test

# Generate coverage report
flutter test --coverage
```

## 📦 Building for Production

### Android
```bash
flutter build apk --release
flutter build appbundle --release
```

### iOS
```bash
flutter build ios --release
```

## 🤝 Contributing

We welcome contributions! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🙏 Acknowledgments

- Flutter team for the amazing framework
- Firebase for backend infrastructure
- Google for Gemini AI capabilities
- FlutterFlow for rapid prototyping tools
- Open source community for various packages

## 📞 Support

For support, email support@eventology-india.com or join our community forum.

## 🗺️ Roadmap

- [ ] Multi-language support (Hindi, Tamil, Bengali, etc.)
- [ ] Payment gateway integration
- [ ] Video consultation with vendors
- [ ] AR venue preview
- [ ] Social media integration
- [ ] Vendor rating and review system
- [ ] Push notifications
- [ ] Calendar integration
- [ ] Guest list management
- [ ] Automated reminders and notifications

## 📊 Architecture

### Data Flow
```
User Interface
    ↓
Services Layer (Business Logic)
    ↓
Firebase Backend (Firestore, Auth, Storage)
    ↓
Gemini AI (for intelligent features)
```

### State Management
- Provider for authentication and simple state
- Riverpod for complex state management
- StreamBuilder for real-time updates

## 🔧 Configuration

### Firebase Setup
1. Enable Email/Password authentication
2. Create Firestore database
3. Set up Storage buckets
4. Deploy security rules
5. Create composite indexes

### Gemini AI Setup
1. Get API key from Google AI Studio
2. Enable Gemini Pro model
3. Configure API routing
4. Set up rate limiting

---

**Built with ❤️ for the Indian Event Industry**
