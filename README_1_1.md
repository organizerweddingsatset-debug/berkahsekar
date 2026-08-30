# TOKO KASIR - Kotlin Native Full POS

## Fitur sesuai request:
- Login Admin / Kasir
- Modal Awal & Laporan Laba Rutin (harian/mingguan/bulanan)
- Pengaturan Barang (CRUD, stok, barcode, notif stok menipis)
- Pengingat Hutang Pelanggan (bayar kurang) & Lebih Bayar (kembalian belum diambil) + notifikasi WorkManager
- Kuitansi thermal printer 58mm Bluetooth ESC/POS (PrinterHelper.kt)
- Blast Promo WA via Intent official (PromoBlaster.kt) - aman dari banned
- Room Database lokal, offline first

## Cara Build jadi APK via GitHub:
1. Buat repo baru github.com/new
2. Upload semua file ini
3. Tab Actions -> workflow akan build otomatis
4. Download APK dari Artifacts

## Setting Printer:
- Edit SettingsScreen -> masukkan MAC address printer bluetooth (contoh 66:22:33:44:55)
- PrinterHelper sudah pakai UUID SPP standar, cocok untuk printer EPSON, RPP02N, dll

## Next Improvement:
- Tambah login PIN Room
- Tambah export laporan CSV
- Tambah backup DB ke Google Drive

Dibuat untuk toko di Surabaya, support offline.
