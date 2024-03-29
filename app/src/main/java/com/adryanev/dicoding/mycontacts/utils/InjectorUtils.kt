package com.adryanev.dicoding.mycontacts.utils

import android.content.Context
import com.adryanev.dicoding.mycontacts.data.ContactDatabase
import com.adryanev.dicoding.mycontacts.data.ContactRepository
import com.adryanev.dicoding.mycontacts.viewmodels.ContactDetailViewModelFactory
import com.adryanev.dicoding.mycontacts.viewmodels.CreateContactViewModelFactory
import com.adryanev.dicoding.mycontacts.viewmodels.EditContactViewModelFactory
import com.adryanev.dicoding.mycontacts.viewmodels.MainViewModelFactory

object InjectorUtils {


    private fun getContactRepository(context:Context) : ContactRepository{
        return ContactRepository.getInstance(ContactDatabase.getInstance(context).contactDao())
    }

    fun provideMainViewModelFactory(context: Context) : MainViewModelFactory{
        val repository = getContactRepository(context)
        return MainViewModelFactory(repository)
    }

    fun provideDetailContactViewModelFactory(context: Context, contactId: Int): ContactDetailViewModelFactory{
        return ContactDetailViewModelFactory(getContactRepository(context),contactId)
    }

    fun provideCreateContactViewModelFactory(context: Context) : CreateContactViewModelFactory{
        val repository = getContactRepository(context)
        return CreateContactViewModelFactory(repository)
    }

    fun provideEditContactViewModelFactory(context: Context, contactId: Int) : EditContactViewModelFactory{
        val repository = getContactRepository(context)
        return EditContactViewModelFactory(repository,contactId)
    }


}