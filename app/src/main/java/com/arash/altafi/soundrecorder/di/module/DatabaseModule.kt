package com.arash.altafi.soundrecorder.di.module

import android.content.Context
import androidx.room.Room
import com.arash.altafi.soundrecorder.data.local.room.SoundRecordDao
import com.arash.altafi.soundrecorder.data.local.room.SoundRecorderDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): SoundRecorderDatabase =
        Room.databaseBuilder(
            context,
            SoundRecorderDatabase::class.java,
            "SoundRecord.db"
        ).build()

    @Provides
    fun provideSoundRecordDao(database: SoundRecorderDatabase): SoundRecordDao =
        database.soundRecordDao()
}