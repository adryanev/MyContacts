package com.adryanev.dicoding.mycontacts.data

import com.adryanev.dicoding.mycontacts.data.dao.ContactDao
import com.adryanev.dicoding.mycontacts.data.entities.Contact


class ContactRepository private constructor(private val contactDao: ContactDao){


    fun getContacts() = contactDao.getContacts()

    fun getContact(id: Int) = contactDao.getContact(id)

    suspend fun insertContact(contact: Contact)  = contactDao.insert(contact)

    suspend fun deleteContact(id: Int) = contactDao.delete(id)
    suspend fun updateContact(contact: Contact) = contactDao.update(contact)

    companion object{
        @Volatile private var instance: ContactRepository? = null

        fun getInstance(contactDao: ContactDao) =
            instance ?: synchronized(this){
                instance?: ContactRepository(contactDao).also { instance = it }
            }

    }

}