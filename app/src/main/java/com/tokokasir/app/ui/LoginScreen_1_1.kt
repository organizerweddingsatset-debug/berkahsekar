package com.tokokasir.app.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun LoginScreen(nav: NavController) {
    var role by remember { mutableStateOf("ADMIN") }
    Column(Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Toko Kasir - Login", style = MaterialTheme.typography.headlineMedium)
        Spacer(Modifier.height(24.dp))
        Text("Pilih Role:")
        Row {
            FilterChip(selected = role=="ADMIN", onClick = {role="ADMIN"}, label={Text("Admin")})
            Spacer(Modifier.width(8.dp))
            FilterChip(selected = role=="KASIR", onClick = {role="KASIR"}, label={Text("Kasir")})
        }
        Spacer(Modifier.height(24.dp))
        Button(onClick = { nav.navigate("dashboard") }, modifier = Modifier.fillMaxWidth()) { Text("Masuk sebagai $role") }
        Text("PIN default: 1234 (bisa diubah di Settings)", style = MaterialTheme.typography.bodySmall)
    }
}
