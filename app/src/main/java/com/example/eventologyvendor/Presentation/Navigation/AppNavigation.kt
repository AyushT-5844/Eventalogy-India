package com.example.eventologyvendor.Presentation.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.eventologyvendor.Presentation.Screens.CategoryScreen
import com.example.eventologyvendor.Presentation.Screens.EditProfileScreen
import com.example.eventologyvendor.Presentation.Screens.Login
import com.example.eventologyvendor.Presentation.Screens.PersonalDetailsScreen
import com.example.eventologyvendor.Presentation.Screens.VendorLeadHubScreen
import com.example.eventologyvendor.Presentation.Screens.VerificationScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(route = Screen.Login.route) {
            Login(
                onNavigateToVendorRegistration = {
                    navController.navigate(Screen.PersonalDetails.route)
                },
                onNavigateToCustomerHome = {
                    // Navigate to customer home screen when available
                }
            )
        }

        composable(route = Screen.PersonalDetails.route) {
            PersonalDetailsScreen(
                onNext = {
                    navController.navigate(Screen.Category.route)
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = Screen.Category.route) {
            CategoryScreen(
                onNext = {
                    navController.navigate(Screen.Verification.route)
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = Screen.Verification.route) {
            VerificationScreen(
                onComplete = {
                    navController.navigate(Screen.VendorLeadHub.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = Screen.VendorLeadHub.route) {
            VendorLeadHubScreen(
                onNavigateToProfile = {
                    navController.navigate(Screen.EditProfile.route)
                },
                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = Screen.EditProfile.route) {
            EditProfileScreen(
                onBack = {
                    navController.popBackStack()
                },
                onNavigateToLeads = {
                    navController.navigate(Screen.VendorLeadHub.route) {
                        popUpTo(Screen.VendorLeadHub.route) { inclusive = true }
                    }
                }
            )
        }
    }
}

