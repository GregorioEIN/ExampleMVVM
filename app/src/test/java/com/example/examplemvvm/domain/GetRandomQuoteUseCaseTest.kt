package com.example.examplemvvm.domain

import com.example.examplemvvm.data.Repository
import com.example.examplemvvm.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetRandomQuoteUseCaseTest{
    @RelaxedMockK
    private lateinit var quoteRespository: Repository

    lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getRandomQuoteUseCase = GetRandomQuoteUseCase(quoteRespository)
    }

    @Test
    fun `when database is emty then return null` ()= runBlocking {

        //given
        coEvery { quoteRespository.getAllQuoteRDataBase() } returns emptyList()
        //when
        val response = getRandomQuoteUseCase()
        //then
        assert(response == null)
    }

    @Test
    fun `when database is not emty then return a quote` () = runBlocking {
        val quote = listOf(Quote("Hola genio del futuro", "DelGenio"))
        coEvery { quoteRespository.getAllQuoteRDataBase() } returns quote
        val response = getRandomQuoteUseCase()
        assert(response == quote.first())
    }
}