import 'package:cloud_firestore/cloud_firestore.dart';

class Vendor {
  final String id;
  final String name;
  final String category;
  final String description;
  final String businessName;
  final String contactEmail;
  final String contactPhone;
  final String address;
  final String city;
  final String state;
  final List<String> services;
  final double rating;
  final int reviewCount;
  final bool isVerified;
  final bool isPremium;
  final double minPrice;
  final double maxPrice;
  final List<String> portfolioImages;
  final Map<String, dynamic> availability;
  final List<String> certifications;
  final DateTime createdAt;
  
  Vendor({
    required this.id,
    required this.name,
    required this.category,
    required this.description,
    required this.businessName,
    required this.contactEmail,
    required this.contactPhone,
    required this.address,
    required this.city,
    required this.state,
    required this.services,
    required this.rating,
    required this.reviewCount,
    required this.isVerified,
    required this.isPremium,
    required this.minPrice,
    required this.maxPrice,
    required this.portfolioImages,
    required this.availability,
    required this.certifications,
    required this.createdAt,
  });
  
  factory Vendor.fromFirestore(DocumentSnapshot doc) {
    final data = doc.data() as Map<String, dynamic>;
    return Vendor(
      id: doc.id,
      name: data['name'] ?? '',
      category: data['category'] ?? '',
      description: data['description'] ?? '',
      businessName: data['businessName'] ?? '',
      contactEmail: data['contactEmail'] ?? '',
      contactPhone: data['contactPhone'] ?? '',
      address: data['address'] ?? '',
      city: data['city'] ?? '',
      state: data['state'] ?? '',
      services: List<String>.from(data['services'] ?? []),
      rating: (data['rating'] ?? 0.0).toDouble(),
      reviewCount: data['reviewCount'] ?? 0,
      isVerified: data['isVerified'] ?? false,
      isPremium: data['isPremium'] ?? false,
      minPrice: (data['minPrice'] ?? 0.0).toDouble(),
      maxPrice: (data['maxPrice'] ?? 0.0).toDouble(),
      portfolioImages: List<String>.from(data['portfolioImages'] ?? []),
      availability: Map<String, dynamic>.from(data['availability'] ?? {}),
      certifications: List<String>.from(data['certifications'] ?? []),
      createdAt: (data['createdAt'] as Timestamp).toDate(),
    );
  }
  
  Map<String, dynamic> toFirestore() {
    return {
      'name': name,
      'category': category,
      'description': description,
      'businessName': businessName,
      'contactEmail': contactEmail,
      'contactPhone': contactPhone,
      'address': address,
      'city': city,
      'state': state,
      'services': services,
      'rating': rating,
      'reviewCount': reviewCount,
      'isVerified': isVerified,
      'isPremium': isPremium,
      'minPrice': minPrice,
      'maxPrice': maxPrice,
      'portfolioImages': portfolioImages,
      'availability': availability,
      'certifications': certifications,
      'createdAt': Timestamp.fromDate(createdAt),
    };
  }
}
