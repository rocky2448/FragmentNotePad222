package com.example.fragmentnotepad

import java.io.Serializable

class Note(val count: Int, var textNote: String, val dateCreate: String, val checkBoxCondition: Boolean) : Serializable {
}