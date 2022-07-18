package com.arash.altafi.soundrecorder.data.local.room

import androidx.room.*
import com.arash.altafi.soundrecorder.data.local.entity.SoundRecordEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SoundRecordDao {

    @Query("SELECT * FROM soundrecordentity")
    fun getAllRecord(): Flow<List<SoundRecordEntity>>

    @Delete
    suspend fun deleteRecord(record: SoundRecordEntity)

    @Update
    suspend fun updateRecord(record: SoundRecordEntity)

    @Insert
    suspend fun insertRecord(record: SoundRecordEntity)

}