# Deployment Guide

This guide covers deploying Eventology India to production.

## Pre-Deployment Checklist

- [ ] All tests pass
- [ ] Code reviewed and approved
- [ ] Firebase configured for production
- [ ] API keys secured with environment variables
- [ ] Security rules deployed and tested
- [ ] Performance optimizations applied
- [ ] Analytics configured
- [ ] Crash reporting set up

## Environment Setup

### Development
```bash
flutter run --dart-define=GEMINI_API_KEY=dev_key
```

### Staging
```bash
flutter run --dart-define=GEMINI_API_KEY=staging_key --dart-define=ENV=staging
```

### Production
```bash
flutter build apk --release --dart-define=GEMINI_API_KEY=prod_key --dart-define=ENV=production
```

## Android Deployment

### 1. Configure Signing
Create `android/key.properties`:
```properties
storePassword=your_store_password
keyPassword=your_key_password
keyAlias=your_key_alias
storeFile=path/to/keystore.jks
```

### 2. Build Release APK
```bash
flutter build apk --release
```

### 3. Build App Bundle (Recommended)
```bash
flutter build appbundle --release
```

### 4. Upload to Play Console
1. Go to [Google Play Console](https://play.google.com/console)
2. Create app listing
3. Upload AAB file
4. Complete store listing
5. Submit for review

## iOS Deployment

### 1. Configure Xcode
1. Open `ios/Runner.xcworkspace`
2. Set bundle identifier
3. Configure signing & capabilities
4. Set deployment target (iOS 12+)

### 2. Build Release
```bash
flutter build ios --release
```

### 3. Archive in Xcode
1. Product → Archive
2. Upload to App Store Connect
3. Complete app listing
4. Submit for review

## Firebase Deployment

### Deploy Rules and Indexes
```bash
firebase use production
firebase deploy --only firestore:rules
firebase deploy --only storage:rules
firebase deploy --only firestore:indexes
```

### Enable Required Services
- Authentication
- Firestore
- Storage
- Analytics
- Crashlytics

## Performance Optimization

### 1. Enable Code Obfuscation
```bash
flutter build apk --obfuscate --split-debug-info=build/app/outputs/symbols
```

### 2. Optimize Assets
- Compress images
- Use WebP format
- Remove unused assets

### 3. Enable Caching
- Implement image caching
- Cache API responses
- Use offline persistence

## Monitoring

### Firebase Analytics
- Track user events
- Monitor conversion funnels
- Analyze user demographics

### Crashlytics
- Monitor crash-free users
- Track error rates
- Set up alerts

### Performance Monitoring
- Monitor app start time
- Track network requests
- Monitor screen rendering

## Security

### API Key Management
- Use environment variables
- Never commit secrets
- Rotate keys regularly
- Use different keys per environment

### Security Rules
- Test thoroughly
- Use least privilege principle
- Enable audit logging
- Regular security reviews

## Rollback Plan

### Android
1. Keep previous APK/AAB
2. Use staged rollout
3. Monitor crash rates
4. Roll back if needed

### iOS
1. TestFlight for beta testing
2. Phased release
3. Monitor metrics
4. Release previous version if needed

## Post-Deployment

- [ ] Monitor analytics
- [ ] Check crash reports
- [ ] Review user feedback
- [ ] Monitor performance
- [ ] Update documentation
- [ ] Announce release

## Continuous Deployment

### GitHub Actions Example
```yaml
name: Deploy to Production
on:
  push:
    tags:
      - 'v*'
jobs:
  deploy:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - uses: subosito/flutter-action@v2
      - run: flutter pub get
      - run: flutter test
      - run: flutter build apk --release
      # Upload to Play Store
```

## Version Naming

Follow semantic versioning:
- MAJOR.MINOR.PATCH (e.g., 1.0.0)
- Increment MAJOR for breaking changes
- Increment MINOR for new features
- Increment PATCH for bug fixes

## Support

After deployment:
- Monitor user feedback
- Respond to reviews
- Fix critical bugs immediately
- Plan next release

---

**Remember**: Test thoroughly in staging before production deployment!
