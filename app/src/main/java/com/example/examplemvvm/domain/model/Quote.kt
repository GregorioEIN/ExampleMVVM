package com.example.examplemvvm.domain.model

import com.example.examplemvvm.data.database.entities.QuoteEntity
import com.example.examplemvvm.data.model.QuoteModel

data class Quote(val quote:String, val author:String)

//funcion de extenciion
fun QuoteModel.toDomaind() = Quote(quote, author)
fun QuoteEntity.toDomaind() = Quote(quote, author)