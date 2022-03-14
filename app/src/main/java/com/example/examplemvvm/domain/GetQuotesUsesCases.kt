package com.example.examplemvvm.domain

import com.example.examplemvvm.data.Repository
import com.example.examplemvvm.data.database.entities.ToDataBase
import com.example.examplemvvm.domain.model.Quote
import javax.inject.Inject


class GetQuotesUsesCases @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke(): List<Quote> {

        val quotes: List<Quote> = repository.getAllQuoteRFromApi()

       return if (quotes.isNotEmpty()) {
            repository.clearQuotes()
            repository.inserQuotes(quotes.map { it.ToDataBase() })
           quotes
        } else {
            repository.getAllQuoteRDataBase()
        }

    }



}