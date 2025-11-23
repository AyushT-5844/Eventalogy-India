# API Documentation

## Overview

Eventology India uses Firebase as the backend and Gemini AI for intelligent features. This document describes the API structure and data models.

## Firebase Firestore Collections

### Users Collection (`/users`)

**Schema:**
```typescript
{
  id: string;              // Document ID (Firebase Auth UID)
  email: string;           // User email
  displayName: string;     // Full name
  phoneNumber?: string;    // Optional phone
  photoUrl?: string;       // Profile picture URL
  role: 'client' | 'vendor' | 'admin';
  isVerified: boolean;     // Account verification status
  savedVendors: string[];  // Array of vendor IDs
  favoriteEvents: string[]; // Array of event IDs
  preferences: object;     // User preferences
  createdAt: timestamp;
  lastLoginAt: timestamp;
}
```

**Operations:**
- GET `/users/{userId}` - Get user profile
- POST `/users` - Create user (auto-created on signup)
- PUT `/users/{userId}` - Update user profile
- DELETE `/users/{userId}` - Delete user account

### Events Collection (`/events`)

**Schema:**
```typescript
{
  id: string;              // Document ID
  userId: string;          // Owner user ID
  title: string;           // Event title
  description: string;     // Event description
  eventType: string;       // Type from AppConfig.supportedEventTypes
  eventDate: timestamp;    // Event date and time
  venue: string;           // Venue name/address
  guestCount: number;      // Expected guests
  budget: number;          // Total budget in INR
  pricingTier: string;     // 'Budget' | 'Standard' | 'Premium' | 'Luxury'
  status: string;          // 'planning' | 'booked' | 'confirmed' | 'inProgress' | 'completed' | 'cancelled'
  vendorIds: string[];     // Associated vendor IDs
  preferences: object;     // Custom preferences
  createdAt: timestamp;
  updatedAt: timestamp;
}
```

**Queries:**
- Get user events: `where('userId', '==', userId)`
- Get upcoming events: `where('eventDate', '>', now)`
- Get by status: `where('status', '==', status)`
- Get by type: `where('eventType', '==', eventType)`

### Vendors Collection (`/vendors`)

**Schema:**
```typescript
{
  id: string;              // Document ID
  name: string;            // Vendor name
  category: string;        // Category from AppConfig.vendorCategories
  description: string;     // Business description
  businessName: string;    // Legal business name
  contactEmail: string;    // Contact email
  contactPhone: string;    // Contact phone
  address: string;         // Physical address
  city: string;            // City
  state: string;           // State
  services: string[];      // List of services offered
  rating: number;          // Average rating (0-5)
  reviewCount: number;     // Total reviews
  isVerified: boolean;     // Verified vendor status
  isPremium: boolean;      // Premium membership
  minPrice: number;        // Minimum price in INR
  maxPrice: number;        // Maximum price in INR
  portfolioImages: string[]; // Image URLs
  availability: object;    // Availability calendar
  certifications: string[]; // Certifications
  createdAt: timestamp;
}
```

**Queries:**
- Get all: `orderBy('rating', 'desc')`
- Get by category: `where('category', '==', category)`
- Get verified: `where('isVerified', '==', true)`
- Get premium: `where('isPremium', '==', true)`
- Get by city: `where('city', '==', city)`
- Price filter: `where('minPrice', '>=', min).where('maxPrice', '<=', max)`

### Bookings Collection (`/bookings`)

**Schema:**
```typescript
{
  id: string;              // Document ID
  eventId: string;         // Associated event ID
  vendorId: string;        // Vendor ID
  userId: string;          // User ID
  bookingDate: timestamp;  // Booking date
  amount: number;          // Total amount
  paidAmount: number;      // Amount paid
  status: string;          // 'pending' | 'confirmed' | 'paid' | 'completed' | 'cancelled' | 'refunded'
  paymentMethod: string;   // Payment method used
  serviceDetails: object;  // Service-specific details
  notes?: string;          // Optional notes
  createdAt: timestamp;
  updatedAt: timestamp;
}
```

**Queries:**
- Get user bookings: `where('userId', '==', userId)`
- Get vendor bookings: `where('vendorId', '==', vendorId)`
- Get event bookings: `where('eventId', '==', eventId)`
- Get by status: `where('status', '==', status)`

## Gemini AI API

### Base URL
```
https://generativelanguage.googleapis.com/v1beta
```

### Authentication
All requests require API key in header:
```
Authorization: Bearer YOUR_GEMINI_API_KEY
```

### Endpoints

#### 1. Generate Event Suggestions
**Method:** POST  
**Endpoint:** `/models/gemini-pro:generateContent`

**Request:**
```json
{
  "contents": [{
    "parts": [{
      "text": "As an event planning expert, provide suggestions for..."
    }]
  }]
}
```

**Response:**
```json
{
  "candidates": [{
    "content": {
      "parts": [{
        "text": "Event planning suggestions..."
      }]
    }
  }]
}
```

#### 2. Generate Budget Plan
**Method:** POST  
**Endpoint:** `/models/gemini-pro:generateContent`

Similar structure as above, with budget-specific prompts.

#### 3. Chat with AI Assistant
**Method:** POST  
**Endpoint:** `/models/gemini-pro:generateContent`

For interactive conversations with context.

## Firebase Storage Paths

### Structure:
```
/users/{userId}/profile/{filename}          - Profile pictures
/events/{eventId}/images/{filename}         - Event images
/vendors/{vendorId}/portfolio/{filename}    - Vendor portfolios
/vendors/{vendorId}/documents/{filename}    - Certifications
```

### Upload Guidelines:
- Max size: 10MB for images, 5MB for documents
- Allowed formats: jpg, png, pdf
- Use Firebase Storage SDK for uploads

## Error Codes

### Firebase Errors
- `permission-denied`: User lacks permissions
- `not-found`: Document doesn't exist
- `already-exists`: Document already exists
- `unauthenticated`: User not logged in

### Gemini AI Errors
- `401`: Invalid API key
- `429`: Rate limit exceeded
- `500`: Server error

## Rate Limits

### Firebase
- Read: 50,000/day (free tier)
- Write: 20,000/day (free tier)
- Storage: 1GB (free tier)

### Gemini API
- Free tier: 60 requests/minute
- Daily: 1,000 requests/day

## Best Practices

1. **Always paginate** large collections
2. **Use indexes** for compound queries
3. **Cache frequently** accessed data
4. **Implement retry logic** for API calls
5. **Validate data** before writes
6. **Handle errors** gracefully
7. **Secure API keys** properly

## Code Examples

### Creating an Event
```dart
final event = Event(
  id: '',
  userId: currentUser.uid,
  title: 'Wedding Ceremony',
  description: 'Beautiful wedding',
  eventType: 'Wedding',
  eventDate: DateTime(2025, 6, 15),
  venue: 'Grand Hotel',
  guestCount: 200,
  budget: 500000,
  pricingTier: 'Premium',
  status: EventStatus.planning,
  vendorIds: [],
  preferences: {},
  createdAt: DateTime.now(),
  updatedAt: DateTime.now(),
);

final eventId = await eventService.createEvent(event);
```

### Querying Vendors
```dart
final vendors = vendorService.getVendorsByCategory('Catering');
vendors.listen((vendorList) {
  // Handle vendor list
});
```

### Using Gemini AI
```dart
final suggestions = await geminiService.generateEventSuggestions(
  eventType: 'Wedding',
  guestCount: 200,
  budget: 500000,
);
```

## Support

For API issues:
- Check Firebase Console for quota limits
- Review Firestore security rules
- Verify API keys are valid
- Check network connectivity
