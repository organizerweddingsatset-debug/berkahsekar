package com.tokokasir.app.data.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val sku: String = "",
    val barcode: String = "",
    val modal: Double, // harga beli
    val jual: Double, // harga jual
    val stok: Int,
    val kategori: String = "Umum",
    val minStok: Int = 5
)

@Entity
data class Customer(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val phone: String, // untuk blast WA
    val alamat: String = ""
)

@Entity
data class Debt(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val customerId: Int,
    val customerName: String,
    val type: String, // HUTANG atau LEBIH_BAYAR (kembalian yang belum diambil)
    val amount: Double,
    val note: String = "",
    val dueDate: Long = System.currentTimeMillis(),
    val isLunas: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)

@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val customerId: Int? = null,
    val customerName: String = "Umum",
    val totalModal: Double,
    val totalJual: Double,
    val profit: Double,
    val bayar: Double,
    val kembalian: Double,
    val hutangCreated: Double = 0.0, // jika bayar kurang
    val itemsJson: String, // JSON list items
    val createdAt: Long = System.currentTimeMillis()
)

@Entity
data class ModalAwal(
    @PrimaryKey val id: Int = 1,
    val amount: Double,
    val dateSet: Long = System.currentTimeMillis()
)
