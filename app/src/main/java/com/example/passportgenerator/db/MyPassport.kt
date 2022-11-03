package com.example.passportgenerator.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MyPassport:java.io.Serializable {

    @PrimaryKey
    var id:Int?=null

    var name:String?=null
    var surname:String?=null
    var imagePath:String?=null
    var BOD:String?=null
    var passportNumber:String?=null

    constructor()
    constructor(
        name: String?,
        surname: String?,
        imagePath: String?,
        BOD: String?,
        passportNumber: String?
    ) {
        this.name = name
        this.surname = surname
        this.imagePath = imagePath
        this.BOD = BOD
        this.passportNumber = passportNumber
    }
}