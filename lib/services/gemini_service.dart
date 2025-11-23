import 'package:google_generative_ai/google_generative_ai.dart';
import '../config/api_config.dart';

class GeminiService {
  late final GenerativeModel _model;
  
  GeminiService() {
    _model = GenerativeModel(
      model: 'gemini-pro',
      apiKey: ApiConfig.geminiApiKey,
    );
  }
  
  // Generate event planning suggestions
  Future<String> generateEventSuggestions({
    required String eventType,
    required int guestCount,
    required double budget,
    String? preferences,
  }) async {
    final prompt = '''
As an event planning expert in India, provide detailed suggestions for:
- Event Type: $eventType
- Guest Count: $guestCount
- Budget: ₹$budget
${preferences != null ? '- Preferences: $preferences' : ''}

Please suggest:
1. Venue recommendations
2. Catering options
3. Decoration themes
4. Entertainment ideas
5. Budget breakdown
6. Timeline for planning

Format the response in a clear, structured manner.
''';
    
    try {
      final content = [Content.text(prompt)];
      final response = await _model.generateContent(content);
      return response.text ?? 'Unable to generate suggestions';
    } catch (e) {
      throw Exception('Failed to generate suggestions: $e');
    }
  }
  
  // Generate vendor recommendations
  Future<String> recommendVendors({
    required String eventType,
    required String city,
    required String category,
    required double budget,
  }) async {
    final prompt = '''
As a vendor recommendation expert in India, suggest the best types of vendors for:
- Event Type: $eventType
- Location: $city
- Category: $category
- Budget Range: ₹$budget

Provide recommendations on:
1. What to look for in vendors
2. Key questions to ask
3. Price expectations
4. Must-have services
5. Red flags to avoid

Be specific to the Indian event market.
''';
    
    try {
      final content = [Content.text(prompt)];
      final response = await _model.generateContent(content);
      return response.text ?? 'Unable to generate recommendations';
    } catch (e) {
      throw Exception('Failed to generate recommendations: $e');
    }
  }
  
  // Generate budget breakdown
  Future<Map<String, dynamic>> generateBudgetPlan({
    required String eventType,
    required int guestCount,
    required double totalBudget,
  }) async {
    final prompt = '''
Create a detailed budget breakdown for:
- Event Type: $eventType
- Guest Count: $guestCount
- Total Budget: ₹$totalBudget

Provide percentage allocation and estimated amounts for:
1. Venue
2. Catering
3. Decoration
4. Photography/Videography
5. Entertainment
6. Invitations
7. Miscellaneous

Return as JSON format with categories and amounts.
''';
    
    try {
      final content = [Content.text(prompt)];
      final response = await _model.generateContent(content);
      
      // Parse the response and return budget breakdown
      // In production, you'd parse the JSON response properly
      return {
        'analysis': response.text ?? 'Unable to generate budget plan',
        'totalBudget': totalBudget,
        'eventType': eventType,
        'guestCount': guestCount,
      };
    } catch (e) {
      throw Exception('Failed to generate budget plan: $e');
    }
  }
  
  // Chat with AI assistant
  Future<String> chatWithAssistant(String userMessage) async {
    final prompt = '''
You are an expert event planning assistant for Eventology India.
Help the user with their event planning query.

User Query: $userMessage

Provide helpful, specific, and actionable advice related to event planning in India.
''';
    
    try {
      final content = [Content.text(prompt)];
      final response = await _model.generateContent(content);
      return response.text ?? 'Unable to process your query';
    } catch (e) {
      throw Exception('Failed to process chat: $e');
    }
  }
  
  // Generate event checklist
  Future<List<String>> generateEventChecklist({
    required String eventType,
    required DateTime eventDate,
  }) async {
    final daysUntilEvent = eventDate.difference(DateTime.now()).inDays;
    
    final prompt = '''
Generate a comprehensive event planning checklist for:
- Event Type: $eventType
- Days until event: $daysUntilEvent

Create a timeline-based checklist with tasks categorized by timeframes:
- 6 months before
- 3 months before
- 1 month before
- 2 weeks before
- 1 week before
- 1 day before
- Day of event

Return as a numbered list.
''';
    
    try {
      final content = [Content.text(prompt)];
      final response = await _model.generateContent(content);
      
      // Parse response into list of tasks
      final text = response.text ?? '';
      return text.split('\n').where((line) => line.trim().isNotEmpty).toList();
    } catch (e) {
      throw Exception('Failed to generate checklist: $e');
    }
  }
}
