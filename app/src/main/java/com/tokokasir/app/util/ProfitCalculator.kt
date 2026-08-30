package com.tokokasir.app.util

data class ProfitReport(
    val modalAwal: Double,
    val totalPenjualan: Double,
    val totalModalTerjual: Double,
    val labaKotor: Double,
    val labaBersih: Double,
    val jumlahTransaksi: Int
)

object ProfitCalculator {
    fun calculate(modalAwal: Double, transactions: List<com.tokokasir.app.data.model.Transaction>): ProfitReport {
        val totalJual = transactions.sumOf { it.totalJual }
        val totalModal = transactions.sumOf { it.totalModal }
        val laba = transactions.sumOf { it.profit }
        return ProfitReport(
            modalAwal = modalAwal,
            totalPenjualan = totalJual,
            totalModalTerjual = totalModal,
            labaKotor = laba,
            labaBersih = laba, // bisa dikurangi biaya operasional nanti
            jumlahTransaksi = transactions.size
        )
    }
}
