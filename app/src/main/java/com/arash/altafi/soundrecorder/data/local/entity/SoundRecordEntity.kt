package com.arash.altafi.soundrecorder.data.local.entity


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SoundRecordEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val duration: Long,
    val filePath: String,
    val createdAt: String,
)