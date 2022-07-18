package com.arash.altafi.soundrecorder.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arash.altafi.soundrecorder.di.ViewModelKey
import com.arash.altafi.soundrecorder.ui.ViewModelFactory
import com.arash.altafi.soundrecorder.ui.detail.DetailViewModel
import com.arash.altafi.soundrecorder.ui.recorder.RecorderViewModel
import com.arash.altafi.soundrecorder.ui.saved.SavedSoundViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(SavedSoundViewModel::class)
    abstract fun bindSaveSoundViewModel(viewModel: SavedSoundViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RecorderViewModel::class)
    abstract fun bindRecorderViewModel(viewModel: RecorderViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun bindDetailViewModel(viewModel: DetailViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}