import SwiftUI
import GoogleMobileAds

@main
struct PowerliftingQuizApp: App {
    var body: some Scene {
        WindowGroup {
            ContentView()
                .onAppear {
                    MobileAds.initialize()
                }
        }
    }
}
