package com.adryanev.dicoding.mycontacts.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    var id: Int,
    var nama: String,
    var nomorHp: String,
    var alamat: String,
    var email: String){

    @Ignore constructor(nama: String,
                        nomorHp: String,
                        alamat: String,
                        email: String) : this(0,nama,nomorHp,alamat,email)


    override fun toString() = nama
}