import 'package:cloud_firestore/cloud_firestore.dart';

class Booking {
  final String id;
  final String eventId;
  final String vendorId;
  final String userId;
  final DateTime bookingDate;
  final double amount;
  final double paidAmount;
  final BookingStatus status;
  final String paymentMethod;
  final Map<String, dynamic> serviceDetails;
  final String? notes;
  final DateTime createdAt;
  final DateTime updatedAt;
  
  Booking({
    required this.id,
    required this.eventId,
    required this.vendorId,
    required this.userId,
    required this.bookingDate,
    required this.amount,
    required this.paidAmount,
    required this.status,
    required this.paymentMethod,
    required this.serviceDetails,
    this.notes,
    required this.createdAt,
    required this.updatedAt,
  });
  
  factory Booking.fromFirestore(DocumentSnapshot doc) {
    final data = doc.data() as Map<String, dynamic>;
    return Booking(
      id: doc.id,
      eventId: data['eventId'] ?? '',
      vendorId: data['vendorId'] ?? '',
      userId: data['userId'] ?? '',
      bookingDate: (data['bookingDate'] as Timestamp).toDate(),
      amount: (data['amount'] ?? 0.0).toDouble(),
      paidAmount: (data['paidAmount'] ?? 0.0).toDouble(),
      status: BookingStatus.values.firstWhere(
        (e) => e.name == data['status'],
        orElse: () => BookingStatus.pending,
      ),
      paymentMethod: data['paymentMethod'] ?? '',
      serviceDetails: Map<String, dynamic>.from(data['serviceDetails'] ?? {}),
      notes: data['notes'],
      createdAt: (data['createdAt'] as Timestamp).toDate(),
      updatedAt: (data['updatedAt'] as Timestamp).toDate(),
    );
  }
  
  Map<String, dynamic> toFirestore() {
    return {
      'eventId': eventId,
      'vendorId': vendorId,
      'userId': userId,
      'bookingDate': Timestamp.fromDate(bookingDate),
      'amount': amount,
      'paidAmount': paidAmount,
      'status': status.name,
      'paymentMethod': paymentMethod,
      'serviceDetails': serviceDetails,
      'notes': notes,
      'createdAt': Timestamp.fromDate(createdAt),
      'updatedAt': Timestamp.fromDate(updatedAt),
    };
  }
}

enum BookingStatus {
  pending,
  confirmed,
  paid,
  completed,
  cancelled,
  refunded,
}
