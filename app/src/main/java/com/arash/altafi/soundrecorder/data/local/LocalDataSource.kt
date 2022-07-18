package com.arash.altafi.soundrecorder.data.local

import com.arash.altafi.soundrecorder.data.local.entity.SoundRecordEntity
import com.arash.altafi.soundrecorder.data.local.room.SoundRecordDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


interface ILocalDataSource {
    fun getAllRecord(): Flow<List<SoundRecordEntity>>
    suspend fun deleteRecord(record: SoundRecordEntity)
    suspend fun updateRecord(record: SoundRecordEntity)
    suspend fun insertRecord(record: SoundRecordEntity)
}

@Singleton
class LocalDataSource @Inject constructor(
    private val soundRecordDao: SoundRecordDao
): ILocalDataSource {
    override fun getAllRecord(): Flow<List<SoundRecordEntity>> =
       soundRecordDao.getAllRecord()

    override suspend fun deleteRecord(record: SoundRecordEntity) =
        soundRecordDao.deleteRecord(record)

    override suspend fun updateRecord(record: SoundRecordEntity) =
        soundRecordDao.updateRecord(record)

    override suspend fun insertRecord(record: SoundRecordEntity) =
        soundRecordDao.insertRecord(record)
}