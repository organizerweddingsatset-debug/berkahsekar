package com.tokokasir.app.ui
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tokokasir.app.util.PrinterHelper

data class CartItem(val name: String, var qty: Int, val modal: Double, val jual: Double)

@Composable
fun PosScreen(nav: NavController) {
    var cart by remember { mutableStateOf(listOf(CartItem("Indomie Goreng",2,2500.0,3500.0), CartItem("Aqua 600ml",1,2000.0,3000.0))) }
    var bayar by remember { mutableStateOf("10000") }
    val total = cart.sumOf { it.qty * it.jual }
    val totalModal = cart.sumOf { it.qty * it.modal }
    val bayarDouble = bayar.toDoubleOrNull() ?: 0.0
    val kembalian = bayarDouble - total

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Kasir POS", style = MaterialTheme.typography.headlineSmall)
        LazyColumn(Modifier.weight(1f)) {
            items(cart.size) { idx ->
                val item = cart[idx]
                ListItem(headlineContent = { Text(item.name) }, supportingContent = { Text("${item.qty} x Rp ${item.jual.toInt()}") })
            }
        }
        Divider()
        Text("Total Modal: Rp ${totalModal.toInt()} | Total Jual: Rp ${total.toInt()} | Laba: Rp ${(total-totalModal).toInt()}")
        OutlinedTextField(value = bayar, onValueChange = {bayar = it}, label = {Text("Bayar")}, modifier = Modifier.fillMaxWidth())
        Text(if(kembalian < 0) "Kurang bayar (Hutang): Rp ${(-kembalian).toInt()}" else if(kembalian>0) "Kembalian (Lebih): Rp ${kembalian.toInt()} - Wajib ingat!" else "Pas", color = if(kembalian<0) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary)
        Spacer(Modifier.height(8.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            Button(onClick = {
                // Simpan transaksi + cek hutang/lebihan
                // Jika kembalian <0 -> buat Debt type HUTANG
                // Jika kembalian >0 -> buat Debt type LEBIH_BAYAR untuk pengingat
            }, modifier = Modifier.weight(1f)) { Text("Bayar & Simpan") }
            OutlinedButton(onClick = {
                val data = PrinterHelper.buildReceipt(
                    "TOKO BAROKAH SBY",
                    cart.map { Triple(it.name, it.qty, it.jual) },
                    total, bayarDouble, kembalian, "Umum", kembalian<0
                )
                // PrinterHelper.printViaBluetooth(context, data, "XX:XX:XX:XX:XX:XX") { ok,msg -> }
            }) { Text("Print Struk") }
        }
    }
}
