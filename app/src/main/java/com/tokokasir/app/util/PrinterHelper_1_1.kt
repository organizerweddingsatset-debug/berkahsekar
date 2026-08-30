package com.tokokasir.app.util
import android.bluetooth.BluetoothManager
import android.content.Context
import java.io.OutputStream
import java.util.UUID

object PrinterHelper {
    // ESC/POS commands untuk printer thermal 58mm
    private val ESC = 0x1B.toByte()
    private val GS = 0x1D.toByte()

    fun buildReceipt(
        tokoName: String,
        items: List<Triple<String, Int, Double>>, // name, qty, price
        total: Double,
        bayar: Double,
        kembalian: Double,
        customerName: String,
        isHutang: Boolean
    ): ByteArray {
        val sb = StringBuilder()
        val boldOn = byteArrayOf(ESC, 0x45, 1)
        val boldOff = byteArrayOf(ESC, 0x45, 0)
        val center = byteArrayOf(ESC, 0x61, 1)
        val left = byteArrayOf(ESC, 0x61, 0)

        // Build text dulu, nanti convert ke ESC/POS bytes
        val lines = mutableListOf<String>()
        lines.add("      $tokoName")
        lines.add("  Jl. Contoh No.123 Surabaya")
        lines.add("--------------------------------")
        lines.add("Pelanggan: $customerName")
        lines.add("Tgl: ${java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(java.util.Date())}")
        lines.add("--------------------------------")
        items.forEach { (name, qty, price) ->
            lines.add("${name.take(20)}")
            lines.add("  $qty x ${price.toInt()} = ${(qty*price).toInt()}")
        }
        lines.add("--------------------------------")
        lines.add("TOTAL: Rp ${total.toInt()}")
        lines.add("BAYAR: Rp ${bayar.toInt()}")
        lines.add("KEMBALI: Rp ${kembalian.toInt()}")
        if(isHutang) lines.add("** SISA HUTANG: Rp ${(-kembalian).toInt()} **")
        if(kembalian > 0 && !isHutang) lines.add("** LEBIH BAYAR: Rp ${kembalian.toInt()} - Simpan! **")
        lines.add("--------------------------------")
        lines.add("  Terima kasih!")
        lines.add("  Barang yang sudah dibeli")
        lines.add("  tidak dapat ditukar")
        lines.add("\n\n\n")

        return lines.joinToString("\n").toByteArray(Charsets.US_ASCII) + byteArrayOf(0x0A, 0x0A, 0x0A, GS, 0x56, 0x00) // cut
    }

    // Untuk koneksi Bluetooth SPP standar (kebanyakan printer thermal)
    fun printViaBluetooth(context: Context, data: ByteArray, deviceAddress: String, onResult: (Boolean, String) -> Unit) {
        try {
            val btManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
            val adapter = btManager.adapter
            val device = adapter.getRemoteDevice(deviceAddress)
            val uuid = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")
            val socket = device.createRfcommSocketToServiceRecord(uuid)
            socket.connect()
            val out: OutputStream = socket.outputStream
            out.write(data)
            out.flush()
            out.close()
            socket.close()
            onResult(true, "Struk berhasil diprint")
        } catch (e: Exception) {
            onResult(false, "Gagal print: ${e.message}")
        }
    }
}
