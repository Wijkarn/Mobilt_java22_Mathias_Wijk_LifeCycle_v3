package com.example.lifecyclev3

class User {
    var personnummer = ""
    var password = ""
    var email = ""
    var address = ""
    var phone = ""
    var name = ""

    constructor()

    constructor(
        personnummer: String,
        password: String
    )

    constructor(
        personnummer: String,
        password: String,
        email: String,
        address: String,
        phone: String,
        name: String
    ){
        this.personnummer = personnummer
        this.password = password
        this.email = email
        this.address = address
        this.phone = phone
        this.name = name
    }
}