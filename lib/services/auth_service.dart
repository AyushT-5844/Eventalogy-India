import 'package:flutter/foundation.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import '../models/user_profile.dart';

class AuthService extends ChangeNotifier {
  final FirebaseAuth _auth = FirebaseAuth.instance;
  final FirebaseFirestore _firestore = FirebaseFirestore.instance;
  
  User? get currentUser => _auth.currentUser;
  bool get isAuthenticated => currentUser != null;
  
  Stream<User?> get authStateChanges => _auth.authStateChanges();
  
  // Sign in with email and password
  Future<UserCredential> signInWithEmailAndPassword(
    String email,
    String password,
  ) async {
    try {
      final credential = await _auth.signInWithEmailAndPassword(
        email: email,
        password: password,
      );
      
      // Update last login
      await _updateLastLogin(credential.user!.uid);
      
      notifyListeners();
      return credential;
    } catch (e) {
      throw Exception('Sign in failed: $e');
    }
  }
  
  // Register with email and password
  Future<UserCredential> registerWithEmailAndPassword(
    String email,
    String password,
    String displayName,
  ) async {
    try {
      final credential = await _auth.createUserWithEmailAndPassword(
        email: email,
        password: password,
      );
      
      // Create user profile
      await _createUserProfile(
        credential.user!.uid,
        email,
        displayName,
      );
      
      notifyListeners();
      return credential;
    } catch (e) {
      throw Exception('Registration failed: $e');
    }
  }
  
  // Sign out
  Future<void> signOut() async {
    await _auth.signOut();
    notifyListeners();
  }
  
  // Reset password
  Future<void> sendPasswordResetEmail(String email) async {
    await _auth.sendPasswordResetEmail(email: email);
  }
  
  // Get user profile
  Future<UserProfile?> getUserProfile(String userId) async {
    try {
      final doc = await _firestore.collection('users').doc(userId).get();
      if (doc.exists) {
        return UserProfile.fromFirestore(doc);
      }
      return null;
    } catch (e) {
      throw Exception('Failed to get user profile: $e');
    }
  }
  
  // Create user profile
  Future<void> _createUserProfile(
    String userId,
    String email,
    String displayName,
  ) async {
    final profile = UserProfile(
      id: userId,
      email: email,
      displayName: displayName,
      role: UserRole.client,
      isVerified: false,
      savedVendors: [],
      favoriteEvents: [],
      preferences: {},
      createdAt: DateTime.now(),
      lastLoginAt: DateTime.now(),
    );
    
    await _firestore
        .collection('users')
        .doc(userId)
        .set(profile.toFirestore());
  }
  
  // Update last login
  Future<void> _updateLastLogin(String userId) async {
    await _firestore.collection('users').doc(userId).update({
      'lastLoginAt': Timestamp.now(),
    });
  }
}
