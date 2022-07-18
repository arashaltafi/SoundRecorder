package com.arash.altafi.soundrecorder.ui.recorder

import androidx.lifecycle.ViewModel
import com.arash.altafi.soundrecorder.data.SoundRecordRepository
import javax.inject.Inject

class RecorderViewModel @Inject constructor(
    private val soundRecordRepository: SoundRecordRepository
): ViewModel()