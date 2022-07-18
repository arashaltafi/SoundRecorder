package com.arash.altafi.soundrecorder.data

import com.arash.altafi.soundrecorder.data.local.LocalDataSource
import com.arash.altafi.soundrecorder.model.SoundRecord
import com.arash.altafi.soundrecorder.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

interface ISoundRecordRepository {
    fun getAllRecord(): Flow<List<SoundRecord>>
    suspend fun deleteRecord(record: SoundRecord)
    suspend fun updateRecord(record: SoundRecord)
    suspend fun insertRecord(record: SoundRecord)
}

@Singleton
class SoundRecordRepository @Inject constructor(
    private val localDataSource: LocalDataSource
): ISoundRecordRepository {
    override fun getAllRecord(): Flow<List<SoundRecord>> =
        localDataSource.getAllRecord().map { list ->
            list.map { DataMapper.entityToModel(it) }
        }

    override suspend fun deleteRecord(record: SoundRecord) {
        localDataSource.deleteRecord(DataMapper.modelToEntity(record))
    }

    override suspend fun updateRecord(record: SoundRecord) {
        localDataSource.updateRecord(DataMapper.modelToEntity(record))
    }

    override suspend fun insertRecord(record: SoundRecord) {
        localDataSource.insertRecord(DataMapper.modelToEntity(record))
    }
}
