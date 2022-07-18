package com.arash.altafi.soundrecorder.di

import android.content.Context
import com.arash.altafi.soundrecorder.data.ISoundRecordRepository
import com.arash.altafi.soundrecorder.di.module.RepositoryModule
import com.arash.altafi.soundrecorder.di.module.ViewModelModule
import com.arash.altafi.soundrecorder.service.RecordingService
import com.arash.altafi.soundrecorder.ui.detail.DetailRecordActivity
import com.arash.altafi.soundrecorder.ui.recorder.RecorderFragment
import com.arash.altafi.soundrecorder.ui.saved.SavedSoundFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RepositoryModule::class,
        ViewModelModule::class,
    ]
)
interface MainComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): MainComponent
    }

    fun provideRepository(): ISoundRecordRepository

    fun inject(recorderFragment: RecorderFragment)
    fun inject(savedSoundFragment: SavedSoundFragment)
    fun inject(recordingService: RecordingService)
    fun inject(detailRecordActivity: DetailRecordActivity)
}