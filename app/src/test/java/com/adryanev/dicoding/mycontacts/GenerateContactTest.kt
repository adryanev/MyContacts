package com.adryanev.dicoding.mycontacts

import com.adryanev.dicoding.mycontacts.data.ContactRepository
import junit.framework.Assert.*
import org.junit.Test

class GenerateContactTest {


    @Test
    fun checkIfRepoNotNull(){

        val repo = ContactRepository();
        assertNotNull(repo)
    }


}