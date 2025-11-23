import 'package:cloud_firestore/cloud_firestore.dart';

class UserProfile {
  final String id;
  final String email;
  final String displayName;
  final String? phoneNumber;
  final String? photoUrl;
  final UserRole role;
  final bool isVerified;
  final List<String> savedVendors;
  final List<String> favoriteEvents;
  final Map<String, dynamic> preferences;
  final DateTime createdAt;
  final DateTime lastLoginAt;
  
  UserProfile({
    required this.id,
    required this.email,
    required this.displayName,
    this.phoneNumber,
    this.photoUrl,
    required this.role,
    required this.isVerified,
    required this.savedVendors,
    required this.favoriteEvents,
    required this.preferences,
    required this.createdAt,
    required this.lastLoginAt,
  });
  
  factory UserProfile.fromFirestore(DocumentSnapshot doc) {
    final data = doc.data() as Map<String, dynamic>;
    return UserProfile(
      id: doc.id,
      email: data['email'] ?? '',
      displayName: data['displayName'] ?? '',
      phoneNumber: data['phoneNumber'],
      photoUrl: data['photoUrl'],
      role: UserRole.values.firstWhere(
        (e) => e.name == data['role'],
        orElse: () => UserRole.client,
      ),
      isVerified: data['isVerified'] ?? false,
      savedVendors: List<String>.from(data['savedVendors'] ?? []),
      favoriteEvents: List<String>.from(data['favoriteEvents'] ?? []),
      preferences: Map<String, dynamic>.from(data['preferences'] ?? {}),
      createdAt: (data['createdAt'] as Timestamp).toDate(),
      lastLoginAt: (data['lastLoginAt'] as Timestamp).toDate(),
    );
  }
  
  Map<String, dynamic> toFirestore() {
    return {
      'email': email,
      'displayName': displayName,
      'phoneNumber': phoneNumber,
      'photoUrl': photoUrl,
      'role': role.name,
      'isVerified': isVerified,
      'savedVendors': savedVendors,
      'favoriteEvents': favoriteEvents,
      'preferences': preferences,
      'createdAt': Timestamp.fromDate(createdAt),
      'lastLoginAt': Timestamp.fromDate(lastLoginAt),
    };
  }
}

enum UserRole {
  client,
  vendor,
  admin,
}
