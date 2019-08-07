package com.adryanev.dicoding.mycontacts.viewmodels

import android.view.View
import androidx.annotation.MainThread
import androidx.lifecycle.*
import com.adryanev.dicoding.mycontacts.data.ContactRepository
import com.adryanev.dicoding.mycontacts.data.entities.Contact
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

class EditContactViewModel( private val repository: ContactRepository, private val id: Int) : ViewModel(){

    val loadTask = null

    val contact = repository.getContact(id)
    val data = MediatorLiveData<Contact>()

    val nama : MutableLiveData<String> = MutableLiveData()
    val nomorHp : MutableLiveData<String> = MutableLiveData()
    val email : MutableLiveData<String> = MutableLiveData()
    val alamat : MutableLiveData<String> = MutableLiveData()

    init{
        data.addSource(contact){
            nama.value = it.nama
            nomorHp.value = it.nomorHp
            email.value = it.email
            alamat.value = it.alamat
        }
    }


    private var contactMutableLiveData: MutableLiveData<Contact>? = null
    fun saveContact(): MutableLiveData<Contact>{
        if(contactMutableLiveData == null){
            contactMutableLiveData = MutableLiveData()
        }

        return contactMutableLiveData as MutableLiveData<Contact>
    }

    fun onClick(view: View){
        val contact = Contact(0, nama.value.toString(),nomorHp.value.toString(), alamat.value.toString(),email.value.toString())
        contactMutableLiveData?.value = contact

    }


     fun updateContact(contact: Contact) = viewModelScope.launch{
         repository.updateContact(contact)

     }






}