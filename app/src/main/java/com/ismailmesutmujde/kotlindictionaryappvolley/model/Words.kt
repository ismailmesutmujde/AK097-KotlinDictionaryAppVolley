package com.ismailmesutmujde.kotlindictionaryappvolley.model

import java.io.Serializable

data class Words (var word_id : Int, var english : String, var turkish : String) : Serializable {
}