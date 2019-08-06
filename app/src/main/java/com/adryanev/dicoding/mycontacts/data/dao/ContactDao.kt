package com.adryanev.dicoding.mycontacts.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.adryanev.dicoding.mycontacts.data.entities.Contact

@Dao
interface ContactDao {

    @Query("SELECT * FROM contacts WHERE nama LIKE 'K%' ORDER BY nama ")
    fun getContacts(): LiveData<List<Contact>>

    @Query("SELECT * FROM contacts WHERE id = :id")
    fun getContact(id: Int): LiveData<Contact>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(contacts: List<Contact>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(contact: Contact) : Long

    @Update
    suspend fun update(contact: Contact)

    @Query("DELETE FROM contacts WHERE id = :id")
    suspend fun delete(id: Int)
}