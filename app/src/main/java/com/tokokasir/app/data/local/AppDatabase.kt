package com.tokokasir.app.data.local
import com.tokokasir.app.data.model.*
object InMemoryDb {
    val products = mutableListOf(Product(1, "Indomie Goreng", "", "", 2500.0, 3500.0, 20), Product(2, "Aqua 600ml", "", "", 2000.0, 3000.0, 30))
    var modalAwal = ModalAwal(amount = 15000000.0)
}
class AppDatabase
