package com.adryanev.dicoding.mycontacts.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adryanev.dicoding.mycontacts.data.ContactRepository

class ContactDetailViewModelFactory(private val contactRepository: ContactRepository, private val contactId:Int) : ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ContactDetailViewModel(contactRepository,contactId) as T
    }
}