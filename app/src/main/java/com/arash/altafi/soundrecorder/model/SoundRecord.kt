package com.arash.altafi.soundrecorder.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SoundRecord(
    var id: Int = 0,
    var name: String? = "",
    var duration: Long = 0,
    var filePath: String? = "",
    var createdAt: String = ""
) : Parcelable
