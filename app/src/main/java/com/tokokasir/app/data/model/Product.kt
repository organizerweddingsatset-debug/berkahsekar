package com.tokokasir.app.data.model
data class Product(val id: Int = 0, val name: String, val sku: String = "", val barcode: String = "", val modal: Double, val jual: Double, val stok: Int, val kategori: String = "Umum", val minStok: Int = 5)
data class Customer(val id: Int = 0, val name: String, val phone: String, val alamat: String = "")
data class Debt(val id: Int = 0, val customerId: Int, val customerName: String, val type: String, val amount: Double, val note: String = "", val dueDate: Long = System.currentTimeMillis(), val isLunas: Boolean = false, val createdAt: Long = System.currentTimeMillis())
data class Transaction(val id: Int = 0, val customerId: Int? = null, val customerName: String = "Umum", val totalModal: Double, val totalJual: Double, val profit: Double, val bayar: Double, val kembalian: Double, val hutangCreated: Double = 0.0, val itemsJson: String = "", val createdAt: Long = System.currentTimeMillis())
data class ModalAwal(val id: Int = 1, val amount: Double = 15000000.0, val dateSet: Long = System.currentTimeMillis())
