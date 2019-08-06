package com.adryanev.dicoding.mycontacts.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.adryanev.dicoding.mycontacts.data.ContactRepository

class CreateContactViewModelFactory (private val contactRepository: ContactRepository) : ViewModelProvider.NewInstanceFactory() {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CreateContactViewModel(contactRepository) as T
    }
}