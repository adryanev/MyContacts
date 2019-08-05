package com.adryanev.dicoding.mycontacts.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.adryanev.dicoding.mycontacts.data.ContactDatabase
import com.adryanev.dicoding.mycontacts.data.entities.Contact
import com.adryanev.dicoding.mycontacts.utils.Constant
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import kotlinx.coroutines.coroutineScope
import timber.log.Timber

class SeedDatabaseWorker(context: Context,
                         workerParams: WorkerParameters
): CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result = coroutineScope {

        try {
            applicationContext.assets.open(Constant.CONTACTS_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val contactType = object : TypeToken<List<Contact>>() {}.type
                    val contactList: List<Contact> = Gson().fromJson(jsonReader, contactType)

                    val database = ContactDatabase.getInstance(applicationContext)
                    database.contactDao().insertAll(contactList)

                    Result.success()
                }
            }
        } catch (ex: Exception) {
            Timber.e("Error seeding databaseL ${ex.localizedMessage}")
            Result.failure()
        }
    }


}