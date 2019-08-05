package com.adryanev.dicoding.mycontacts.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.adryanev.dicoding.mycontacts.data.dao.ContactDao
import com.adryanev.dicoding.mycontacts.data.entities.Contact
import com.adryanev.dicoding.mycontacts.utils.Constant.DATABASE_NAME
import com.adryanev.dicoding.mycontacts.worker.SeedDatabaseWorker

@Database(entities = [Contact::class],version = 1, exportSchema = false)
abstract class ContactDatabase: RoomDatabase(){

    abstract fun contactDao() : ContactDao

    companion object{

        @Volatile private var instance : ContactDatabase? = null

        fun getInstance(context: Context): ContactDatabase{
            return  instance ?: synchronized(this){
                instance ?: buildDatabase(context).also{instance = it}
            }
        }

        private fun buildDatabase(context: Context): ContactDatabase {

            return Room.databaseBuilder(context,ContactDatabase::class.java, DATABASE_NAME).
                addCallback(object : RoomDatabase.Callback(){
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                        WorkManager.getInstance(context).enqueue(request)
                    }
                }).build()
        }
    }

}

