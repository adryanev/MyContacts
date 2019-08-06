package com.adryanev.dicoding.mycontacts.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adryanev.dicoding.mycontacts.data.ContactRepository
import com.adryanev.dicoding.mycontacts.data.entities.Contact
import kotlinx.coroutines.cancel

class ContactDetailViewModel (contactRepository: ContactRepository, private val contactId:Int): ViewModel(){

    val contact: LiveData<Contact> = contactRepository.getContact(contactId)

    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}