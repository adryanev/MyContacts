package com.adryanev.dicoding.mycontacts.utils

import android.content.Context
import com.adryanev.dicoding.mycontacts.data.ContactDatabase
import com.adryanev.dicoding.mycontacts.data.ContactRepository
import com.adryanev.dicoding.mycontacts.viewmodels.MainViewModelFactory

object InjectorUtils {


    private fun getContactRepository(context:Context) : ContactRepository{
        return ContactRepository.getInstance(ContactDatabase.getInstance(context).contactDao())
    }

    fun provideMainViewModelFactory(context: Context) : MainViewModelFactory{
        val repository = getContactRepository(context)
        return MainViewModelFactory(repository)
    }

}