package com.arash.altafi.soundrecorder.ui.saved

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.arash.altafi.soundrecorder.data.SoundRecordRepository
import com.arash.altafi.soundrecorder.model.SoundRecord
import kotlinx.coroutines.launch
import javax.inject.Inject

class SavedSoundViewModel @Inject constructor(
    private val soundRecordRepository: SoundRecordRepository
): ViewModel() {

    fun getAllRecord(): LiveData<List<SoundRecord>> =
        soundRecordRepository.getAllRecord().asLiveData()

    fun deleteItem(item: SoundRecord) {
        viewModelScope.launch {
            soundRecordRepository.deleteRecord(item)
        }
    }

}