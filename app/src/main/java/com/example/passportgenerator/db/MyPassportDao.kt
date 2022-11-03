package com.example.passportgenerator.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MyPassportDao {

    @Insert
    fun addPassport(myPassport: MyPassport)

    @Query("select * from mypassport")
    fun getAllPassports():List<MyPassport>
}