/// API Configuration
/// 
/// IMPORTANT: For production, use environment variables or secure storage.
/// 
/// Setup with environment variables:
/// flutter run --dart-define=GEMINI_API_KEY=your_key_here
/// 
/// Access in code:
/// const apiKey = String.fromEnvironment('GEMINI_API_KEY', defaultValue: '');
/// 
/// Never commit actual API keys to version control!
class ApiConfig {
  // Gemini API Configuration
  // Get your API key from: https://makersuite.google.com/app/apikey
  static const String geminiApiKey = String.fromEnvironment(
    'GEMINI_API_KEY',
    defaultValue: 'YOUR_GEMINI_API_KEY_HERE',
  );
  
  // Firebase Configuration
  // These will be generated when you set up Firebase
  static const String firebaseApiKey = String.fromEnvironment(
    'FIREBASE_API_KEY',
    defaultValue: 'YOUR_FIREBASE_API_KEY',
  );
  static const String firebaseProjectId = String.fromEnvironment(
    'FIREBASE_PROJECT_ID',
    defaultValue: 'YOUR_FIREBASE_PROJECT_ID',
  );
  static const String firebaseMessagingSenderId = String.fromEnvironment(
    'FIREBASE_MESSAGING_SENDER_ID',
    defaultValue: 'YOUR_MESSAGING_SENDER_ID',
  );
  static const String firebaseAppId = String.fromEnvironment(
    'FIREBASE_APP_ID',
    defaultValue: 'YOUR_FIREBASE_APP_ID',
  );
  
  // Additional API Endpoints (if needed)
  static const String baseApiUrl = 'https://api.eventology-india.com';
  
  // Feature Toggles
  static const bool useGeminiForRecommendations = true;
  static const bool enableRealTimeUpdates = true;
  static const bool enablePushNotifications = true;
}
