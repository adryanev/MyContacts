package com.adryanev.dicoding.mycontacts.viewmodels

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.adryanev.dicoding.mycontacts.data.ContactRepository
import com.adryanev.dicoding.mycontacts.data.entities.Contact
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber

class EditContactViewModel(repository: ContactRepository, private val id: Int) : ViewModel(){

    val repo = repository
    val nama : MutableLiveData<String> = MutableLiveData()
    val nomorHp : MutableLiveData<String> = MutableLiveData()
    val email : MutableLiveData<String> = MutableLiveData()
    val alamat : MutableLiveData<String> = MutableLiveData()

    private var contactMutableLiveData: MutableLiveData<Contact>? = null
    init{
        val data = repository.getContact(id)
        nama.value = data.value?.nama
        nomorHp.value = data.value?.nomorHp
        email.value = data.value?.nomorHp
        alamat.value = data.value?.alamat

        Timber.d(alamat.value)
    }
    fun getContact(): MutableLiveData<Contact>{
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
         repo.updateContact(contact)

     }






}