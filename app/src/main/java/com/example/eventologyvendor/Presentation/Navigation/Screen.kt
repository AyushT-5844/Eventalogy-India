package com.example.eventologyvendor.Presentation.Navigation

sealed class Screen(val route: String) {
    object Login : Screen("login")
    object PersonalDetails : Screen("personal_details")
    object Category : Screen("category")
    object Verification : Screen("verification")
    object VendorLeadHub : Screen("vendor_lead_hub")
    object EditProfile : Screen("edit_profile")
}
