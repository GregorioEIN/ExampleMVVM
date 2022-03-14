package com.example.examplemvvm.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.examplemvvm.data.database.entities.QuoteEntity

//Data access de room
@Dao
interface QuoteDao {

    @Query("SELECT * FROM quote_table ")
    suspend fun getAllQuote():List<QuoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllQuote(quotes:List<QuoteEntity>)

    @Query("DELETE FROM quote_table")
    suspend fun clearQuotesDB()

}