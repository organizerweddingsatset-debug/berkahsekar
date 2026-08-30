package com.tokokasir.app.data.local
import androidx.room.*
import com.tokokasir.app.data.model.*

@Dao
interface ProductDao {
    @Query("SELECT * FROM Product ORDER BY name ASC") suspend fun getAll(): List<Product>
    @Query("SELECT * FROM Product WHERE stok <= minStok") suspend fun getLowStock(): List<Product>
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insert(p: Product)
    @Delete suspend fun delete(p: Product)
    @Query("SELECT * FROM Product WHERE barcode = :code LIMIT 1") suspend fun findByBarcode(code: String): Product?
}

@Dao
interface CustomerDao {
    @Query("SELECT * FROM Customer") suspend fun getAll(): List<Customer>
    @Insert suspend fun insert(c: Customer)
    @Delete suspend fun delete(c: Customer)
}

@Dao
interface DebtDao {
    @Query("SELECT * FROM Debt WHERE isLunas = 0 ORDER BY dueDate ASC") suspend fun getActive(): List<Debt>
    @Query("SELECT * FROM Debt") suspend fun getAll(): List<Debt>
    @Insert suspend fun insert(d: Debt)
    @Query("UPDATE Debt SET isLunas = 1 WHERE id = :id") suspend fun setLunas(id: Int)
}

@Dao
interface TransactionDao {
    @Query("SELECT * FROM Transaction ORDER BY createdAt DESC") suspend fun getAll(): List<Transaction>
    @Query("SELECT * FROM Transaction WHERE createdAt BETWEEN :start AND :end") suspend fun getBetween(start: Long, end: Long): List<Transaction>
    @Insert suspend fun insert(t: Transaction)
    @Query("SELECT SUM(profit) FROM `Transaction` WHERE createdAt BETWEEN :start AND :end") suspend fun sumProfit(start: Long, end: Long): Double?
    @Query("SELECT SUM(totalModal) FROM `Transaction` WHERE createdAt BETWEEN :start AND :end") suspend fun sumModal(start: Long, end: Long): Double?
}

@Dao
interface ModalDao {
    @Query("SELECT * FROM ModalAwal WHERE id = 1") suspend fun get(): ModalAwal?
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun set(m: ModalAwal)
}

@Database(entities = [Product::class, Customer::class, Debt::class, Transaction::class, ModalAwal::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun customerDao(): CustomerDao
    abstract fun debtDao(): DebtDao
    abstract fun transactionDao(): TransactionDao
    abstract fun modalDao(): ModalDao
}
