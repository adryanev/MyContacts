package com.adryanev.dicoding.mycontacts.viewmodels

import android.view.View
import android.view.WindowId
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adryanev.dicoding.mycontacts.data.ContactRepository
import com.adryanev.dicoding.mycontacts.data.entities.Contact
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class CreateContactViewModel(repository: ContactRepository) : ViewModel(){

    val repo = repository

    val nama : MutableLiveData<String> = MutableLiveData()
    val nomorHp : MutableLiveData<String> = MutableLiveData()
    val email : MutableLiveData<String> = MutableLiveData()
    val alamat : MutableLiveData<String> = MutableLiveData()

    private var contactMutableLiveData: MutableLiveData<Contact>? = null

    fun getContact(): MutableLiveData<Contact>{
        if(contactMutableLiveData == null){
            contactMutableLiveData = MutableLiveData()
        }

        return contactMutableLiveData as MutableLiveData<Contact>
    }

    fun onClick(view: View){
        val contact = Contact(nama.value.toString(),nomorHp.value.toString(), alamat.value.toString(),email.value.toString())
        contactMutableLiveData?.value = contact

    }


     fun saveContact(contact: Contact) = viewModelScope.launch{
         repo.insertContact(contact)

     }






}