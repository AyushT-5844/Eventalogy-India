/// API Configuration
/// 
/// IMPORTANT: This file contains placeholder values.
/// For production, use environment variables or secure storage.
/// Never commit actual API keys to version control.
class ApiConfig {
  // Gemini API Configuration
  // Get your API key from: https://makersuite.google.com/app/apikey
  static const String geminiApiKey = 'YOUR_GEMINI_API_KEY_HERE';
  
  // Firebase Configuration
  // These will be generated when you set up Firebase
  static const String firebaseApiKey = 'YOUR_FIREBASE_API_KEY';
  static const String firebaseProjectId = 'YOUR_FIREBASE_PROJECT_ID';
  static const String firebaseMessagingSenderId = 'YOUR_MESSAGING_SENDER_ID';
  static const String firebaseAppId = 'YOUR_FIREBASE_APP_ID';
  
  // Additional API Endpoints (if needed)
  static const String baseApiUrl = 'https://api.eventology-india.com';
  
  // Feature Toggles
  static const bool useGeminiForRecommendations = true;
  static const bool enableRealTimeUpdates = true;
  static const bool enablePushNotifications = true;
}
