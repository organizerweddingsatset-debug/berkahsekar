package com.tokokasir.app.ui
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DashboardScreen(nav: NavController) {
    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Dashboard Toko", style = MaterialTheme.typography.headlineSmall)
        Text("Surabaya - ${java.time.LocalDate.now()}")
        Spacer(Modifier.height(16.dp))
        // Stats cards placeholder
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Card(Modifier.weight(1f)) { Column(Modifier.padding(12.dp)) { Text("Modal Awal"); Text("Rp 10.000.000", style = MaterialTheme.typography.titleLarge) } }
            Card(Modifier.weight(1f)) { Column(Modifier.padding(12.dp)) { Text("Laba Hari Ini"); Text("Rp 450.000", color = MaterialTheme.colorScheme.primary) } }
        }
        Spacer(Modifier.height(16.dp))
        LazyVerticalGrid(columns = GridCells.Fixed(2), verticalArrangement = Arrangement.spacedBy(12.dp), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            item { MenuCard("POS / Kasir", Icons.Default.ShoppingCart) { nav.navigate("pos") } }
            item { MenuCard("Barang", Icons.Default.Inventory) { nav.navigate("products") } }
            item { MenuCard("Pelanggan & Hutang", Icons.Default.People) { nav.navigate("customers") } }
            item { MenuCard("Tagihan Hutang", Icons.Default.Warning, true) { nav.navigate("debts") } }
            item { MenuCard("Laporan Laba", Icons.Default.BarChart) { nav.navigate("reports") } }
            item { MenuCard("Blast Promo", Icons.Default.Campaign) { nav.navigate("promo") } }
            item { MenuCard("Pengaturan", Icons.Default.Settings) { nav.navigate("settings") } }
        }
    }
}

@Composable
fun MenuCard(title: String, icon: androidx.compose.ui.graphics.vector.ImageVector, alert: Boolean = false, onClick: ()->Unit) {
    Card(onClick = onClick, colors = if(alert) CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.errorContainer) else CardDefaults.cardColors()) {
        Column(Modifier.padding(16.dp)) {
            Icon(icon, contentDescription = null)
            Spacer(Modifier.height(8.dp))
            Text(title)
        }
    }
}
