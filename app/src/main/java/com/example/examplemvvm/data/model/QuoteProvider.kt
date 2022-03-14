package com.example.examplemvvm.data.model

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuoteProvider @Inject constructor() {
    //Ya no es necesario que esté en un companion object
    var quotes: List<QuoteModel> = emptyList()
}