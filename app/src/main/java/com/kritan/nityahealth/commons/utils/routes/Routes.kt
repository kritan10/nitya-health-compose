package com.kritan.nityahealth.commons.utils.routes

sealed class Routes(val route: String) {
    object AppIntro : Routes("app_intro")
    object AppIntroBoarding : Routes("onboarding")

    object Dashboard : Routes("dashboard")
    object Profile : Routes("profile")
}

sealed class AuthRoutes(route: String) : Routes(route) {
    object SignIn : AuthRoutes("auth_sign_in")
    object SignInEmail : AuthRoutes("auth_sign_in_email")
    object SignUp : AuthRoutes("auth_sign_up")
    object SignUpOTP : AuthRoutes("auth_sign_up_otp")
    object SignUpLocation : AuthRoutes("auth_sign_up_location")
}