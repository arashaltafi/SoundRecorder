package com.arash.altafi.soundrecorder.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.arash.altafi.soundrecorder.data.local.entity.SoundRecordEntity

@Database(
    entities = [SoundRecordEntity::class],
    exportSchema = false,
    version = 1
)
abstract class SoundRecorderDatabase: RoomDatabase() {
    abstract fun soundRecordDao(): SoundRecordDao

}