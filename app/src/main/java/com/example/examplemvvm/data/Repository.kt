package com.example.examplemvvm.data

import com.example.examplemvvm.data.database.dao.QuoteDao
import com.example.examplemvvm.data.database.entities.QuoteEntity
import com.example.examplemvvm.data.model.QuoteModel
import com.example.examplemvvm.data.network.QuoteService
import com.example.examplemvvm.domain.model.Quote
import com.example.examplemvvm.domain.model.toDomaind
import javax.inject.Inject


class Repository @Inject constructor(
    private val api: QuoteService,
    private val quoteDao: QuoteDao
) {

    suspend fun getAllQuoteRFromApi(): List<Quote> {
        val response : List<QuoteModel> = api.getQuotes()
        return response.map { it.toDomaind() } //Estoy mapeando los datos que me llevagan de otra capa a la capa donde me encuentro
    }

    suspend fun getAllQuoteRDataBase(): List<Quote>{
        val response : List<QuoteEntity> = quoteDao.getAllQuote()
        return response.map { it.toDomaind() }
    }

    suspend fun inserQuotes(quotes:List<QuoteEntity>){
        quoteDao.insertAllQuote(quotes)
    }

    suspend fun clearQuotes(){
        quoteDao.clearQuotesDB()
    }
}