# Android Application Development Standard

This document serves as a template and standard for creating new Android applications following modern best practices and Google Play requirements.

## Project Setup Requirements

### Target SDK Requirements
Always check the latest requirements at: https://developer.android.com/google/play/requirements/target-sdk

**Current Requirements (as of August 2025):**
- **Target SDK:** API 35 (Android 15) or higher
- **Min SDK:** API 24 (Android 7.0) recommended for broad compatibility
- **Compile SDK:** API 35 or latest available

### Gradle Version Requirements
Always check the latest versions at: https://developer.android.com/build/releases/gradle-plugin

**Current Requirements (as of November 2025):**
- **Android Gradle Plugin (AGP):** 8.13.0
- **Gradle:** 8.13 (minimum required for AGP 8.13)
- **JDK:** 17 (minimum and default for AGP 8.13)
- **SDK Build Tools:** 35.0.0 (minimum and default)

**Important:** Never use dynamic dependencies in version numbers (e.g., `'8.13.+'`). Always specify exact versions to avoid unexpected updates and compatibility issues.

### Development Environment
- **Android Studio:** Latest stable version (Otter 2025.2.1 or later)
- **JDK:** Version 17 (required)
- **Gradle:** 8.13 or later
- **Kotlin:** Latest stable version (2.1.0 or later)

### Build Configuration

#### Root build.gradle
```gradle
plugins {
    id 'com.android.application' version '8.13.0' apply false
    id 'org.jetbrains.kotlin.android' version '2.1.0' apply false
}
```

#### gradle-wrapper.properties
```properties
distributionBase=GRADLE_USER_HOME
distributionPath=wrapper/dists
distributionUrl=https\://services.gradle.org/distributions/gradle-8.13-bin.zip
networkTimeout=10000
validateDistributionUrl=true
zipStoreBase=GRADLE_USER_HOME
zipStorePath=wrapper/dists
```

#### app/build.gradle
```gradle
android {
    namespace 'com.yourapp'
    compileSdk 35

    defaultConfig {
        applicationId "com.yourapp"
        minSdk 24
        targetSdk 35
        versionCode 1
        versionName "1.0"
    }

    buildTypes {
        release {
            minifyEnabled true
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }

    compileOptions {
        sourceCompatibility JavaVersion.VERSION_17
        targetCompatibility JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = '17'
    }

    buildFeatures {
        viewBinding true
    }
}
```

## Project Structure

### Standard Directory Layout
```
project-root/
├── .claude/                    # Claude Code configuration
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/yourapp/
│   │   │   │   └── MainActivity.kt
│   │   │   ├── res/
│   │   │   │   ├── layout/
│   │   │   │   ├── values/
│   │   │   │   └── xml/
│   │   │   └── AndroidManifest.xml
│   │   ├── test/              # Unit tests
│   │   └── androidTest/       # Instrumented tests
│   ├── build.gradle
│   └── proguard-rules.pro
├── screenshots/
│   ├── raw/                   # Original screenshots
│   ├── phone/                 # Processed phone screenshots
│   ├── tablet-7/              # 7-inch tablet screenshots
│   └── tablet-10/             # 10-inch tablet screenshots
├── build.gradle
├── settings.gradle
├── gradle.properties
├── .gitignore
├── README.md
├── PRIVACY_POLICY.md
├── PLAY_STORE.md
├── Claude.md                  # This template
├── take-screenshots.bat
└── process-screenshots.bat
```

## Required Documentation Files

### README.md
Include:
- Project overview and purpose
- Version information (version name, code, SDK levels)
- Build instructions (debug and release)
- Testing instructions
- Screenshot automation instructions
- Development guidelines (architecture, code style)
- Version history

### PRIVACY_POLICY.md
Must include:
- Last updated date
- What information is collected
- How information is used
- Data sharing practices
- Third-party services used
- Children's privacy statement
- User rights
- Contact information
- Notes for developers on when to update

**Update When:**
- Adding analytics or tracking
- Implementing user accounts
- Adding permissions (camera, location, etc.)
- Integrating third-party services
- Collecting any user data
- Adding social features
- Implementing in-app purchases

### PLAY_STORE.md
Must include:
- Short description (80 characters max)
- Long description (4000 characters max)
- App category
- Content rating
- Keywords/tags for SEO
- "What's New" release notes (500 characters max)
- Character limit reminders
- Screenshot requirements reference

**Update When:**
- New features are added
- Major UI changes occur
- New version is released
- Contact information changes

## Git Configuration

### .gitignore
Use the standard Android .gitignore that excludes:
- Build artifacts (*.apk, *.aab, build/, .gradle/)
- IDE files (.idea/, *.iml)
- Local configuration (local.properties)
- Keystores (*.jks, *.keystore)
- Sensitive files (google-services.json if contains secrets)

### Commit Strategy
- Commit each logical change separately
- Use clear, descriptive commit messages
- Format: `[Type] Brief description`
  - Types: Feature, Fix, Update, Refactor, Docs, Test, Build
  - Example: `[Feature] Add user authentication screen`
- Include Co-Authored-By for Claude assistance
- Never commit sensitive information (keys, passwords, tokens)

### Commit Checklist
Before each commit:
1. Run builds successfully
2. Fix any lint warnings
3. Update documentation if needed
4. Update version numbers if releasing
5. Update PLAY_STORE.md release notes if needed

## Screenshot Management

### Automation Scripts

#### take-screenshots.bat
- Connects to device/emulator via ADB
- Interactive screenshot capture
- Saves to screenshots/raw/
- Timestamps each session

#### process-screenshots.bat
- Organizes screenshots by device type
- Validates Google Play requirements
- Prepares for upload

### Screenshot Requirements
- **Format:** JPEG or 24-bit PNG (no alpha)
- **Min dimension:** 320px
- **Max dimension:** 3840px
- **Aspect ratio:** 16:9 to 9:16
- **Quantity:** 2-8 screenshots per device type
- **Device types:** Phone (required), 7" tablet, 10" tablet

### Screenshot Best Practices
1. Show key features in order of importance
2. Use real, production-quality content
3. Ensure text is readable
4. Remove personal/test data
5. Localize for different markets if needed
6. Name files descriptively (01_main_screen.png)

## Architecture Guidelines

### Design Patterns
- **MVVM** (Model-View-ViewModel) recommended
- **Repository pattern** for data management
- **Dependency injection** for loose coupling

### Modern Android Practices
- **ViewBinding** for type-safe view access
- **Kotlin Coroutines** for asynchronous operations
- **Material Design 3** components
- **AndroidX** libraries
- **Jetpack** components where appropriate

### Code Organization
```
com.yourapp/
├── data/
│   ├── model/
│   ├── repository/
│   └── source/
├── ui/
│   ├── main/
│   ├── feature1/
│   └── feature2/
├── util/
└── MainActivity.kt
```

## Security Requirements

### OWASP Top 10 Considerations
- **Input Validation:** Validate all user input
- **Authentication:** Use secure authentication mechanisms
- **Data Storage:** Encrypt sensitive data
- **Network Security:** Use HTTPS, certificate pinning
- **Permissions:** Request minimum necessary permissions
- **Code Obfuscation:** Enable ProGuard/R8 for release

### Sensitive Data
- Never hardcode API keys, passwords, or tokens
- Use Android Keystore for cryptographic keys
- Store credentials in secure storage
- Implement certificate pinning for APIs
- Use SafetyNet or Play Integrity API

## Testing Strategy

### Unit Tests
- Test business logic and ViewModels
- Use JUnit 4 or JUnit 5
- Aim for >70% code coverage on critical paths

### Instrumented Tests
- Test UI interactions and user flows
- Use Espresso framework
- Test on multiple device configurations

### Manual Testing Checklist
- Install fresh (not upgrade)
- Test upgrade from previous version
- Test on different Android versions
- Test on different screen sizes
- Test with poor network conditions
- Test with no network
- Test rotation and lifecycle events
- Test permissions granted/denied

## Release Checklist

### Pre-Release
- [ ] Update versionCode and versionName
- [ ] Update PLAY_STORE.md "What's New" section
- [ ] Review and update PRIVACY_POLICY.md if needed
- [ ] Run full test suite
- [ ] Build release APK/AAB
- [ ] Test release build on device
- [ ] Verify ProGuard/R8 doesn't break functionality
- [ ] Take updated screenshots if UI changed
- [ ] Process screenshots for Google Play

### Google Play Console
- [ ] Upload AAB (Android App Bundle)
- [ ] Update store listing if needed
- [ ] Upload screenshots
- [ ] Set content rating
- [ ] Configure in-app products (if applicable)
- [ ] Set pricing and distribution
- [ ] Submit for review

### Post-Release
- [ ] Monitor crash reports
- [ ] Monitor user reviews
- [ ] Track key metrics
- [ ] Plan next iteration

## Version Management

### Semantic Versioning
Use format: MAJOR.MINOR.PATCH

- **MAJOR:** Breaking changes, major features
- **MINOR:** New features, backward compatible
- **PATCH:** Bug fixes, minor improvements

### Version Code
- Increment by 1 for each release
- Never reuse version codes
- Must always increase

### Version Name Examples
- 1.0.0 - Initial release
- 1.1.0 - Minor feature update
- 1.1.1 - Bug fix release
- 2.0.0 - Major update with breaking changes

## Common Dependencies

### Essential Libraries
```gradle
dependencies {
    // AndroidX Core
    implementation 'androidx.core:core-ktx:1.15.0'
    implementation 'androidx.appcompat:appcompat:1.7.0'
    implementation 'androidx.activity:activity-ktx:1.9.3'

    // Material Design
    implementation 'com.google.android.material:material:1.12.0'

    // ConstraintLayout
    implementation 'androidx.constraintlayout:constraintlayout:2.2.0'

    // Testing
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.2.1'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.6.1'
}
```

**IMPORTANT:** If your app uses any of these features, add the corresponding dependencies:

```gradle
// If using Fragments (required for Navigation Component)
implementation 'androidx.fragment:fragment-ktx:1.8.5'

// If using RecyclerView for lists
implementation 'androidx.recyclerview:recyclerview:1.3.2'
```

### Optional but Recommended
```gradle
// Lifecycle components
implementation 'androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.7'
implementation 'androidx.lifecycle:lifecycle-livedata-ktx:2.8.7'

// Navigation
implementation 'androidx.navigation:navigation-fragment-ktx:2.8.5'
implementation 'androidx.navigation:navigation-ui-ktx:2.8.5'

// Coroutines
implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.1'

// Networking (if needed)
implementation 'com.squareup.retrofit2:retrofit:2.11.0'
implementation 'com.squareup.okhttp3:okhttp:4.12.0'
```

## Permissions

### Request Only What's Needed
Only request permissions essential for core functionality.

### Common Permissions
```xml
<!-- Internet access -->
<uses-permission android:name="android.permission.INTERNET" />

<!-- Network state -->
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
```

### Runtime Permissions
For dangerous permissions (location, camera, etc.):
1. Check if permission is granted
2. Request permission at appropriate time
3. Handle permission denied gracefully
4. Provide clear rationale to user

## Localization

### String Resources
- All user-facing text in strings.xml
- Never hardcode strings in layouts or code
- Create values-{locale}/ directories for translations
- Use plurals resources where applicable

### Supporting RTL
```xml
android:supportsRtl="true"
```

## Performance Optimization

### Best Practices
- Use ViewBinding instead of findViewById
- Optimize images (WebP format recommended)
- Implement lazy loading for large lists
- Use RecyclerView for scrollable lists
- Profile app with Android Profiler
- Enable R8 optimization for release builds
- Minimize method count
- Avoid memory leaks (weak references, lifecycle awareness)

## Accessibility

### Requirements
- Add content descriptions to images
- Ensure sufficient color contrast
- Support TalkBack screen reader
- Make touch targets at least 48dp
- Support text scaling

## App Icon Setup

### Icon Directory Structure

App icons must be placed in multiple mipmap directories for different screen densities:

```
app/src/main/res/
├── mipmap-mdpi/
│   ├── ic_launcher.png (48x48dp)
│   └── ic_launcher_round.png
├── mipmap-hdpi/
│   ├── ic_launcher.png (72x72dp)
│   └── ic_launcher_round.png
├── mipmap-xhdpi/
│   ├── ic_launcher.png (96x96dp)
│   └── ic_launcher_round.png
├── mipmap-xxhdpi/
│   ├── ic_launcher.png (144x144dp)
│   └── ic_launcher_round.png
├── mipmap-xxxhdpi/
│   ├── ic_launcher.png (192x192dp)
│   └── ic_launcher_round.png
└── mipmap-anydpi-v26/
    ├── ic_launcher.xml
    └── ic_launcher_round.xml
```

### Adaptive Icon Configuration (Android 8.0+)

Create `mipmap-anydpi-v26/ic_launcher.xml`:

```xml
<?xml version="1.0" encoding="utf-8"?>
<adaptive-icon xmlns:android="http://schemas.android.com/apk/res/android">
    <background android:drawable="@color/ic_launcher_background"/>
    <foreground android:drawable="@mipmap/ic_launcher"/>
</adaptive-icon>
```

Add icon background color to `values/colors.xml`:

```xml
<color name="ic_launcher_background">#FFFFFF</color>
```

### AndroidManifest Configuration

```xml
<application
    android:icon="@mipmap/ic_launcher"
    android:roundIcon="@mipmap/ic_launcher_round"
    ...>
```

### Icon Best Practices

1. **Use PNG format** with transparency for foreground
2. **Design for adaptive icons** - keep important elements in safe zone (center 66%)
3. **Test on different launchers** - various Android devices use different shapes
4. **Provide all densities** - ideally create specific sizes for each density, not just copy the same file
5. **Use simple, recognizable design** - icons are displayed at small sizes

### Play Store Icon Requirements

For Google Play Store submission, you'll also need:

- **App Icon**: 512x512 PNG with transparency (32-bit)
- **Feature Graphic**: 1024x500 JPEG or PNG (no transparency)

## Creating a New App from This Template

### Step-by-Step Process

1. **Initial Setup**
   ```bash
   # Create project directory
   mkdir myapp
   cd myapp

   # Initialize git
   git init
   ```

2. **Create Project Structure**
   - Copy this Claude.md as reference
   - Create build.gradle files with correct versions
   - Create settings.gradle with app name
   - Create gradle.properties
   - Create .gitignore

3. **Create Android App Module**
   - Create app/build.gradle with namespace and versions
   - Create AndroidManifest.xml
   - Create MainActivity.kt
   - Create layout files
   - Create resource files (strings, colors, themes)

4. **Create Documentation**
   - Create README.md with project specifics
   - Create PRIVACY_POLICY.md (update as needed)
   - Create PLAY_STORE.md with descriptions

5. **Create Automation Scripts**
   - Copy take-screenshots.bat
   - Copy process-screenshots.bat
   - Create screenshots/ directory structure

6. **Initial Commit**
   ```bash
   git add .
   git commit -m "[Init] Initial project setup with Android API 35"
   ```

7. **Verify Build**
   ```bash
   gradlew build
   ```

8. **Start Development**
   - Implement features
   - Commit regularly
   - Update documentation as you go

## Maintenance

### Regular Updates
- **Monthly:** Check for library updates
- **Quarterly:** Review and update target SDK if needed
- **As needed:** Update privacy policy and store listing

### Monitoring
- Set up crash reporting (Firebase Crashlytics)
- Monitor Play Console metrics
- Respond to user reviews
- Track key performance indicators

## Resources

### Official Documentation
- Android Developers: https://developer.android.com/
- Material Design: https://m3.material.io/
- Kotlin: https://kotlinlang.org/docs/home.html
- Google Play Console: https://play.google.com/console

### Tools
- Android Studio: https://developer.android.com/studio
- Android SDK Platform Tools: https://developer.android.com/studio/releases/platform-tools
- ImageMagick: https://imagemagick.org/

### Target SDK Requirements
- https://developer.android.com/google/play/requirements/target-sdk

## Template Version

**Version:** 1.0
**Last Updated:** November 22, 2025
**Target SDK:** API 35 (Android 15)
**Min SDK:** API 24 (Android 7.0)

---

## Notes for Claude

When creating a new Android app using this template:

1. Always fetch the latest target SDK requirements first
2. Create all documentation files (README, PRIVACY_POLICY, PLAY_STORE, this Claude.md)
3. Set up git and commit each major section separately
4. Create both automation scripts for screenshots
5. Use the exact project structure outlined above
6. Follow the security guidelines strictly
7. Commit with descriptive messages and Co-Authored-By attribution

### Common Pitfalls to Avoid

**Missing Dependencies:**
- Always include `androidx.activity:activity-ktx` in essential dependencies
- If using Navigation Component, you MUST include `androidx.fragment:fragment-ktx`
- If using RecyclerView anywhere in the app, you MUST include `androidx.recyclerview:recyclerview`
- These dependencies are not automatically included and will cause crashes if missing

**App Icon Setup:**
- Always set up icons in all mipmap densities (mdpi, hdpi, xhdpi, xxhdpi, xxxhdpi)
- Always create adaptive icon XML for Android 8.0+ support
- Always add `ic_launcher_background` color to colors.xml
- Test icons on actual devices or emulators to verify they display correctly

**Build Configuration:**
- Always use exact version numbers (e.g., '8.13.0'), never dynamic versions (e.g., '8.13.+')
- AGP version must match the minimum required Gradle version
- JDK 17 is required for AGP 8.13+

This template ensures consistency across all Android projects and adherence to Google Play requirements.
