package com.arash.altafi.soundrecorder.utils

import com.arash.altafi.soundrecorder.data.local.entity.SoundRecordEntity
import com.arash.altafi.soundrecorder.model.SoundRecord

object DataMapper {

    fun entityToModel(soundRecordEntity: SoundRecordEntity) =
        SoundRecord(
            soundRecordEntity.id,
            soundRecordEntity.name,
            soundRecordEntity.duration,
            soundRecordEntity.filePath,
            soundRecordEntity.createdAt
    )

    fun modelToEntity(soundRecord: SoundRecord) =
        SoundRecordEntity(
            id = soundRecord.id,
            name = soundRecord.name ?: "",
            duration = soundRecord.duration,
            filePath = soundRecord.filePath ?: "",
            createdAt = soundRecord.createdAt
        )
}