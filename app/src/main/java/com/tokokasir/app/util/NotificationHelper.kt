package com.tokokasir.app.util
import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit

class DebtReminderWorker(ctx: Context, params: WorkerParameters): Worker(ctx, params) {
    override fun doWork(): Result {
        // Cek DB untuk hutang jatuh tempo & stok menipis, lalu show notification
        // Untuk simple, pakai NotificationManager di sini
        return Result.success()
    }
}

object NotificationHelper {
    fun scheduleDailyCheck(context: Context) {
        val req = PeriodicWorkRequestBuilder<DebtReminderWorker>(1, TimeUnit.DAYS)
            .setInitialDelay(1, TimeUnit.HOURS)
            .build()
        WorkManager.getInstance(context).enqueueUniquePeriodicWork("debt_check", ExistingPeriodicWorkPolicy.KEEP, req)
    }
}
