class AppConfig {
  // App Information
  static const String appName = 'Eventology India';
  static const String appVersion = '1.0.0';
  static const String appDescription = 'Mobile-first event planning and booking platform';
  
  // Firebase Configuration
  static const String firebaseProjectId = 'eventology-india';
  
  // API Configuration
  static const String geminiApiBaseUrl = 'https://generativelanguage.googleapis.com/v1beta';
  
  // Feature Flags
  static const bool enableEventDiscovery = true;
  static const bool enableBudgetPlanner = true;
  static const bool enableVendorMarketplace = true;
  static const bool enableManagementDashboard = true;
  static const bool enablePremiumServices = true;
  static const bool enableAIAssistant = true;
  
  // Business Configuration
  static const List<String> supportedEventTypes = [
    'Wedding',
    'Corporate Event',
    'Birthday Party',
    'Anniversary',
    'Conference',
    'Trade Show',
    'Concert',
    'Festival',
    'Workshop',
    'Seminar',
    'Social Gathering',
    'Religious Ceremony',
  ];
  
  static const List<String> vendorCategories = [
    'Venues',
    'Catering',
    'Photography',
    'Videography',
    'Decoration',
    'Entertainment',
    'DJ & Music',
    'Makeup Artists',
    'Mehendi Artists',
    'Choreography',
    'Transportation',
    'Invitations',
    'Gifts & Favors',
    'Event Planning',
  ];
  
  // Pricing Tiers
  static const List<String> pricingTiers = [
    'Budget',
    'Standard',
    'Premium',
    'Luxury',
  ];
}
