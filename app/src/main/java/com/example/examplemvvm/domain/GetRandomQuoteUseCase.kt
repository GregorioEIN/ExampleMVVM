package com.example.examplemvvm.domain

import com.example.examplemvvm.data.Repository
import com.example.examplemvvm.domain.model.Quote
import javax.inject.Inject


class GetRandomQuoteUseCase @Inject constructor(private val repository: Repository) {

    suspend operator fun invoke():Quote?{
        val quotes = repository.getAllQuoteRDataBase()
        if (!quotes.isNullOrEmpty()){
            val ramdonNumber = (0..quotes.size-1).random()
            return quotes[ramdonNumber]
        }
        return null
    }
}