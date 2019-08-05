package com.adryanev.dicoding.mycontacts.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.adryanev.dicoding.mycontacts.data.entities.Contact

@Dao
interface ContactDao {

    @Query("SELECT * FROM contacts ORDER BY nama")
    fun getContacts(): LiveData<List<Contact>>

    @Query("SELECT * FROM contacts WHERE id = :id")
    fun getContactWithId(id: Int): LiveData<Contact>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(contacts: List<Contact>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contact: Contact)
}