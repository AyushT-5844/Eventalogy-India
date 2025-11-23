import 'package:flutter/foundation.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import '../models/vendor.dart';

class VendorService extends ChangeNotifier {
  final FirebaseFirestore _firestore = FirebaseFirestore.instance;
  
  // Get all vendors
  Stream<List<Vendor>> getAllVendors() {
    return _firestore
        .collection('vendors')
        .orderBy('rating', descending: true)
        .snapshots()
        .map((snapshot) =>
            snapshot.docs.map((doc) => Vendor.fromFirestore(doc)).toList());
  }
  
  // Get vendor by ID
  Future<Vendor?> getVendorById(String vendorId) async {
    try {
      final doc = await _firestore.collection('vendors').doc(vendorId).get();
      if (doc.exists) {
        return Vendor.fromFirestore(doc);
      }
      return null;
    } catch (e) {
      throw Exception('Failed to get vendor: $e');
    }
  }
  
  // Get vendors by category
  Stream<List<Vendor>> getVendorsByCategory(String category) {
    return _firestore
        .collection('vendors')
        .where('category', isEqualTo: category)
        .orderBy('rating', descending: true)
        .snapshots()
        .map((snapshot) =>
            snapshot.docs.map((doc) => Vendor.fromFirestore(doc)).toList());
  }
  
  // Get verified vendors
  Stream<List<Vendor>> getVerifiedVendors() {
    return _firestore
        .collection('vendors')
        .where('isVerified', isEqualTo: true)
        .orderBy('rating', descending: true)
        .snapshots()
        .map((snapshot) =>
            snapshot.docs.map((doc) => Vendor.fromFirestore(doc)).toList());
  }
  
  // Get premium vendors
  Stream<List<Vendor>> getPremiumVendors() {
    return _firestore
        .collection('vendors')
        .where('isPremium', isEqualTo: true)
        .orderBy('rating', descending: true)
        .snapshots()
        .map((snapshot) =>
            snapshot.docs.map((doc) => Vendor.fromFirestore(doc)).toList());
  }
  
  // Search vendors by name or city
  Future<List<Vendor>> searchVendors(String query) async {
    try {
      final nameResults = await _firestore
          .collection('vendors')
          .where('name', isGreaterThanOrEqualTo: query)
          .where('name', isLessThanOrEqualTo: '$query\uf8ff')
          .get();
      
      final cityResults = await _firestore
          .collection('vendors')
          .where('city', isEqualTo: query)
          .get();
      
      final vendors = <Vendor>{};
      vendors.addAll(
        nameResults.docs.map((doc) => Vendor.fromFirestore(doc)),
      );
      vendors.addAll(
        cityResults.docs.map((doc) => Vendor.fromFirestore(doc)),
      );
      
      return vendors.toList();
    } catch (e) {
      throw Exception('Failed to search vendors: $e');
    }
  }
  
  // Filter vendors by price range
  Stream<List<Vendor>> filterVendorsByPrice(double minPrice, double maxPrice) {
    return _firestore
        .collection('vendors')
        .where('minPrice', isGreaterThanOrEqualTo: minPrice)
        .where('maxPrice', isLessThanOrEqualTo: maxPrice)
        .snapshots()
        .map((snapshot) =>
            snapshot.docs.map((doc) => Vendor.fromFirestore(doc)).toList());
  }
  
  // Get vendors by city
  Stream<List<Vendor>> getVendorsByCity(String city) {
    return _firestore
        .collection('vendors')
        .where('city', isEqualTo: city)
        .orderBy('rating', descending: true)
        .snapshots()
        .map((snapshot) =>
            snapshot.docs.map((doc) => Vendor.fromFirestore(doc)).toList());
  }
}
