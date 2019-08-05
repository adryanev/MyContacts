package com.adryanev.dicoding.mycontacts.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    val id: Int,
    val nama: String,
    val jenisKelamin: String,
    val foto: String,
    val nomorHp: String,
    val alamat: String,
    val email: String){

    override fun toString() = nama
}