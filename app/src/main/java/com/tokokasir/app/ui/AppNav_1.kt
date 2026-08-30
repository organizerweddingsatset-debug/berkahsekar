package com.tokokasir.app.ui
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*

@Composable
fun AppNav() {
    val nav = rememberNavController()
    NavHost(nav, startDestination = "login") {
        composable("login") { LoginScreen(nav) }
        composable("dashboard") { DashboardScreen(nav) }
        composable("pos") { PosScreen(nav) }
        composable("products") { ProductsScreen(nav) }
        composable("customers") { CustomersScreen(nav) }
        composable("debts") { DebtsScreen(nav) }
        composable("reports") { ReportsScreen(nav) }
        composable("settings") { SettingsScreen(nav) }
        composable("promo") { PromoBlastScreen(nav) }
    }
}
