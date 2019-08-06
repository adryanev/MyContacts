package com.adryanev.dicoding.mycontacts.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adryanev.dicoding.mycontacts.data.ContactRepository
import com.adryanev.dicoding.mycontacts.data.entities.Contact
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class ContactDetailViewModel (contactRepository: ContactRepository, private val contactId:Int): ViewModel(){

    val repo = contactRepository
    val contact: LiveData<Contact> = contactRepository.getContact(contactId)

    fun deleteContact(id:Int) = viewModelScope.launch {
        repo.deleteContact(id)
    }

    /**
     * Cancel all coroutines when the ViewModel is cleared.
     */
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}