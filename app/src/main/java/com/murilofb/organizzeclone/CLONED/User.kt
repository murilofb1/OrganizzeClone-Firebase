package com.murilofb.organizzeclone.models


class UserMD(fullName: String, email: String, psswd: String) {
    var fullName = fullName
    var email = email
    var psswd = psswd
    var totalIncome: Double = 0.0
    var totalExpenses: Double = 0.0

    fun getFirstname(): String {
        val splittedName = fullName.split(" ")
        return splittedName[0]
    }
}