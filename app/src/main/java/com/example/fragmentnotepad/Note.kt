package com.example.fragmentnotepad

import java.io.Serializable

class Note(val count: Int, val textNote: String, val dateCreate: String, val checkBoxCondition: Boolean) : Serializable {
}