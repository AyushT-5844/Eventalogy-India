# Gemini AI Integration Guide

This guide explains how to integrate and use Google's Gemini AI in Eventology India.

## Getting Your API Key

1. Go to [Google AI Studio](https://makersuite.google.com/app/apikey)
2. Sign in with your Google account
3. Click "Create API Key"
4. Copy your API key (keep it secure!)

## Configuration

1. Open `lib/config/api_config.dart`
2. Replace the placeholder with your actual API key:

```dart
static const String geminiApiKey = 'YOUR_ACTUAL_API_KEY_HERE';
```

⚠️ **Security Warning**: Never commit API keys to version control!

## Features Powered by Gemini AI

### 1. Event Planning Suggestions
Generate personalized event planning advice

### 2. Vendor Recommendations
Get AI-powered vendor recommendations

### 3. Budget Planning
Generate detailed budget breakdowns

### 4. Event Checklist
Create timeline-based planning checklists

### 5. AI Chat Assistant
Interactive conversational assistance

## Resources

- [Gemini API Documentation](https://ai.google.dev/docs)
- [Google AI Studio](https://makersuite.google.com)
- [Flutter Package: google_generative_ai](https://pub.dev/packages/google_generative_ai)
