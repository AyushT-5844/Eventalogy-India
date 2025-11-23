import 'package:cloud_firestore/cloud_firestore.dart';

class Event {
  final String id;
  final String userId;
  final String title;
  final String description;
  final String eventType;
  final DateTime eventDate;
  final String venue;
  final int guestCount;
  final double budget;
  final String pricingTier;
  final EventStatus status;
  final List<String> vendorIds;
  final Map<String, dynamic> preferences;
  final DateTime createdAt;
  final DateTime updatedAt;
  
  Event({
    required this.id,
    required this.userId,
    required this.title,
    required this.description,
    required this.eventType,
    required this.eventDate,
    required this.venue,
    required this.guestCount,
    required this.budget,
    required this.pricingTier,
    required this.status,
    required this.vendorIds,
    required this.preferences,
    required this.createdAt,
    required this.updatedAt,
  });
  
  factory Event.fromFirestore(DocumentSnapshot doc) {
    final data = doc.data() as Map<String, dynamic>;
    return Event(
      id: doc.id,
      userId: data['userId'] ?? '',
      title: data['title'] ?? '',
      description: data['description'] ?? '',
      eventType: data['eventType'] ?? '',
      eventDate: (data['eventDate'] as Timestamp).toDate(),
      venue: data['venue'] ?? '',
      guestCount: data['guestCount'] ?? 0,
      budget: (data['budget'] ?? 0.0).toDouble(),
      pricingTier: data['pricingTier'] ?? 'Standard',
      status: EventStatus.values.firstWhere(
        (e) => e.name == data['status'],
        orElse: () => EventStatus.planning,
      ),
      vendorIds: List<String>.from(data['vendorIds'] ?? []),
      preferences: Map<String, dynamic>.from(data['preferences'] ?? {}),
      createdAt: (data['createdAt'] as Timestamp).toDate(),
      updatedAt: (data['updatedAt'] as Timestamp).toDate(),
    );
  }
  
  Map<String, dynamic> toFirestore() {
    return {
      'userId': userId,
      'title': title,
      'description': description,
      'eventType': eventType,
      'eventDate': Timestamp.fromDate(eventDate),
      'venue': venue,
      'guestCount': guestCount,
      'budget': budget,
      'pricingTier': pricingTier,
      'status': status.name,
      'vendorIds': vendorIds,
      'preferences': preferences,
      'createdAt': Timestamp.fromDate(createdAt),
      'updatedAt': Timestamp.fromDate(updatedAt),
    };
  }
}

enum EventStatus {
  planning,
  booked,
  confirmed,
  inProgress,
  completed,
  cancelled,
}
