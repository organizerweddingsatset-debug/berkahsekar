package com.tokokasir.app.ui
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ProductsScreen(nav: NavController) {
    Column(Modifier.padding(16.dp)) {
        Text("Pengaturan Barang", style = MaterialTheme.typography.headlineSmall)
        Text("Fitur: Tambah/edit/hapus, stok, modal/jual, barcode scan, notif stok menipis")
        Spacer(Modifier.height(12.dp))
        // TODO: Lazy list dari Room ProductDao
        Card { Column(Modifier.padding(12.dp)) { Text("Contoh: Indomie - Stok 20 - Modal 2500 Jual 3500"); Text("Laba per item: 1000 (40%)") } }
        Spacer(Modifier.height(12.dp))
        Button(onClick = {}) { Text("+ Tambah Barang") }
    }
}
@Composable fun CustomersScreen(nav: NavController) { Column(Modifier.padding(16.dp)) { Text("Pelanggan"); Text("Simpan nama, HP untuk blast WA, alamat. Link ke hutang.") } }
@Composable fun DebtsScreen(nav: NavController) { 
    Column(Modifier.padding(16.dp)) { 
        Text("Pengingat Hutang & Lebih Bayar", style = MaterialTheme.typography.headlineSmall)
        Text("FILTER: Hutang Pelanggan (bayar kurang) | Lebih Bayar (kembalian belum diambil)")
        Card(Modifier.padding(top=8.dp)) { Column(Modifier.padding(12.dp)) { Text("Budi - HUTANG Rp 15.000 - Jatuh tempo 2 hari lalu"); Button(onClick={}){Text("Tandai Lunas")} } }
        Card(Modifier.padding(top=8.dp)) { Column(Modifier.padding(12.dp)) { Text("Siti - LEBIH BAYAR Rp 5.000 - Kembalian belum diambil"); Button(onClick={}){Text("Sudah Diambil")} } }
    } 
}
@Composable fun ReportsScreen(nav: NavController) { Column(Modifier.padding(16.dp)) { Text("Laporan Keuntungan"); Text("Modal Awal: Rp 10jt | Laba Hari Ini: Rp 450rb | Mingguan: Rp 2.1jt | Bulanan: Rp 8jt"); Text("Grafik + export CSV") } }
@Composable fun SettingsScreen(nav: NavController) { Column(Modifier.padding(16.dp)) { Text("Pengaturan: Nama Toko, Modal Awal, Printer Bluetooth Address, Notifikasi, PIN Kasir"); OutlinedTextField(value="BERKAH SEKAR", onValueChange={}, label={Text("Nama Toko")}); Spacer(Modifier.height(8.dp)); OutlinedTextField(value="15000000", onValueChange={}, label={Text("Modal Awal")}) } }
@Composable fun PromoBlastScreen(nav: NavController) { Column(Modifier.padding(16.dp)) { Text("Blast Promo WA", style = MaterialTheme.typography.headlineSmall); Text("Pilih pelanggan, tulis promo, blast via WhatsApp official (anti banned)"); Button(onClick={}){Text("Blast ke 50 pelanggan")} } }
