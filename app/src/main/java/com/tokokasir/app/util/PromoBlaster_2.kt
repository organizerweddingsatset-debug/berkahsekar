package com.tokokasir.app.util
import android.content.Context
import android.content.Intent
import android.net.Uri

object PromoBlaster {
    fun blastWhatsApp(context: Context, phones: List<String>, message: String) {
        // Blast satu per satu via Intent WA - anti banned, pakai official WA
        phones.forEach { phone ->
            val cleanPhone = phone.replace(Regex("[^0-9]"), "")
            val formatted = if(cleanPhone.startsWith("0")) "62" + cleanPhone.substring(1) else cleanPhone
            try {
                val uri = Uri.parse("https://wa.me/$formatted?text=${Uri.encode(message)}")
                val intent = Intent(Intent.ACTION_VIEW, uri)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                context.startActivity(intent)
                Thread.sleep(1500) // jeda biar tidak spam
            } catch (e: Exception) {}
        }
    }

    fun buildPromoTemplate(namaToko: String, promo: String, diskon: String): String {
        return "Halo Kak! 🎉\n\nAda promo spesial dari *$namaToko*:\n$promo\nDiskon: $diskon\n\nDatang langsung ke toko ya, stok terbatas!\nTerima kasih 🙏"
    }
}
