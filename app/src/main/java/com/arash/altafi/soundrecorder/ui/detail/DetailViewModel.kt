package com.arash.altafi.soundrecorder.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arash.altafi.soundrecorder.data.SoundRecordRepository
import com.arash.altafi.soundrecorder.model.SoundRecord
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val repository: SoundRecordRepository
): ViewModel() {

    fun updateSoundRecord(soundRecord: SoundRecord) {
        viewModelScope.launch {
            repository.updateRecord(soundRecord)
        }
    }
}