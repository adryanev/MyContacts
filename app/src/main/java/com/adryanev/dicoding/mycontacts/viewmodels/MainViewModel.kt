package com.adryanev.dicoding.mycontacts.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

import com.adryanev.dicoding.mycontacts.data.ContactRepository
import com.adryanev.dicoding.mycontacts.data.entities.Contact

class MainViewModel internal constructor(contactRepository: ContactRepository): ViewModel() {

    val contacts : LiveData<List<Contact>> = contactRepository.getContacts()
}