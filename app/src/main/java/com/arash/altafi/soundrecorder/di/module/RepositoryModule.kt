package com.arash.altafi.soundrecorder.di.module

import com.arash.altafi.soundrecorder.data.ISoundRecordRepository
import com.arash.altafi.soundrecorder.data.SoundRecordRepository
import dagger.Binds
import dagger.Module

@Module(includes = [DatabaseModule::class])
abstract class RepositoryModule {
    @Binds
    abstract fun provideRepository(
        soundRecordRepository: SoundRecordRepository
    ): ISoundRecordRepository
}