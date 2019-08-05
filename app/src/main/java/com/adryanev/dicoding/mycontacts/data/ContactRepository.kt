package com.adryanev.dicoding.mycontacts.data

import com.adryanev.dicoding.mycontacts.data.dao.ContactDao


class ContactRepository private constructor(private val contactDao: ContactDao){


    fun getContacts() = contactDao.getContacts()

    fun getContact(id: Int) = contactDao.getContactWithId(id)

    companion object{
        @Volatile private var instance: ContactRepository? = null

        fun getInstance(contactDao: ContactDao) =
            instance ?: synchronized(this){
                instance?: ContactRepository(contactDao).also { instance = it }
            }

    }

}