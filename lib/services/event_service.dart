import 'package:flutter/foundation.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import '../models/event.dart';

class EventService extends ChangeNotifier {
  final FirebaseFirestore _firestore = FirebaseFirestore.instance;
  
  // Get all events for a user
  Stream<List<Event>> getUserEvents(String userId) {
    return _firestore
        .collection('events')
        .where('userId', isEqualTo: userId)
        .orderBy('eventDate', descending: false)
        .snapshots()
        .map((snapshot) =>
            snapshot.docs.map((doc) => Event.fromFirestore(doc)).toList());
  }
  
  // Get event by ID
  Future<Event?> getEventById(String eventId) async {
    try {
      final doc = await _firestore.collection('events').doc(eventId).get();
      if (doc.exists) {
        return Event.fromFirestore(doc);
      }
      return null;
    } catch (e) {
      throw Exception('Failed to get event: $e');
    }
  }
  
  // Create new event
  Future<String> createEvent(Event event) async {
    try {
      final docRef = await _firestore
          .collection('events')
          .add(event.toFirestore());
      notifyListeners();
      return docRef.id;
    } catch (e) {
      throw Exception('Failed to create event: $e');
    }
  }
  
  // Update event
  Future<void> updateEvent(String eventId, Map<String, dynamic> updates) async {
    try {
      updates['updatedAt'] = Timestamp.now();
      await _firestore.collection('events').doc(eventId).update(updates);
      notifyListeners();
    } catch (e) {
      throw Exception('Failed to update event: $e');
    }
  }
  
  // Delete event
  Future<void> deleteEvent(String eventId) async {
    try {
      await _firestore.collection('events').doc(eventId).delete();
      notifyListeners();
    } catch (e) {
      throw Exception('Failed to delete event: $e');
    }
  }
  
  // Search events by type
  Stream<List<Event>> searchEventsByType(String eventType) {
    return _firestore
        .collection('events')
        .where('eventType', isEqualTo: eventType)
        .snapshots()
        .map((snapshot) =>
            snapshot.docs.map((doc) => Event.fromFirestore(doc)).toList());
  }
  
  // Get upcoming events
  Stream<List<Event>> getUpcomingEvents(String userId) {
    return _firestore
        .collection('events')
        .where('userId', isEqualTo: userId)
        .where('eventDate', isGreaterThan: Timestamp.now())
        .orderBy('eventDate')
        .snapshots()
        .map((snapshot) =>
            snapshot.docs.map((doc) => Event.fromFirestore(doc)).toList());
  }
  
  // Get events by status
  Stream<List<Event>> getEventsByStatus(String userId, EventStatus status) {
    return _firestore
        .collection('events')
        .where('userId', isEqualTo: userId)
        .where('status', isEqualTo: status.name)
        .snapshots()
        .map((snapshot) =>
            snapshot.docs.map((doc) => Event.fromFirestore(doc)).toList());
  }
}
