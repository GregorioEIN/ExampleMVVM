package com.example.examplemvvm.domain

import com.example.examplemvvm.data.Repository
import com.example.examplemvvm.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetQuotesUsesCasesTest {


    //@MockK
    @RelaxedMockK
    private lateinit var quoteRespository: Repository

    lateinit var quoteRespositoryUseCase: GetQuotesUsesCases

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        quoteRespositoryUseCase = GetQuotesUsesCases(quoteRespository)
    }

    @Test
    fun `when the api doesnt return anything the get vaules form the batabase` () = runBlocking {

        //Given // coEvery cuando está en una corutina pero cuando no, solo every
        coEvery { quoteRespository.getAllQuoteRFromApi() } returns emptyList()

        //When
        quoteRespositoryUseCase()

        //then
        coVerify(exactly = 1) { quoteRespository.getAllQuoteRDataBase() }
    }

    @Test
    fun `when the api return something then get values from api` () = runBlocking {

        val myList = listOf(Quote("Hola me llamo Nadie", "Nadie"))
        //given
        coEvery { quoteRespository.getAllQuoteRFromApi() } returns myList

        //when
        val response = quoteRespositoryUseCase()

        //then
        coVerify (exactly = 1) { quoteRespository.clearQuotes() }
        coVerify (exactly = 1) { quoteRespository.inserQuotes(any()) }
        coVerify (exactly = 0) { quoteRespository.getAllQuoteRDataBase() }

        assert(myList == response)
    }
}