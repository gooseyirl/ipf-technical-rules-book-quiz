import SwiftUI
import GoogleMobileAds
import AppTrackingTransparency

@main
struct PowerliftingQuizApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView()
                .onReceive(
                    NotificationCenter.default.publisher(
                        for: UIApplication.didBecomeActiveNotification
                    )
                ) { _ in
                    ATTrackingManager.requestTrackingAuthorization { _ in
                        MobileAds.initialize()
                    }
                }
        }
    }
}
