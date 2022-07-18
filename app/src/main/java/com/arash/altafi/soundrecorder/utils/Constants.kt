package com.arash.altafi.soundrecorder.utils

import android.app.Application
import com.arash.altafi.soundrecorder.R
import com.arash.altafi.soundrecorder.model.SoundRecord
import java.io.File

sealed class RecordServiceEvent {
    object PLAY: RecordServiceEvent()
    object STOP: RecordServiceEvent()
}

fun createRecordFile(application: Application): File {
    var count = 0
    var resultFile: File
    do {
        count++
        val mediaDir = application.externalMediaDirs.firstOrNull()?.let {
            File(it, application.resources.getString(R.string.file_record)).apply { mkdirs() }
        }

        val outputDirectory = if (
            mediaDir != null && mediaDir.exists()
        ) mediaDir else application.filesDir

        resultFile = File(outputDirectory, "rec-${count}.mp4")
    } while (resultFile.exists() && !resultFile.isDirectory)

    return resultFile
}

fun deleteFile(filePath: String) {
    val file = File(filePath)
    file.delete()
}

fun updateFile(
    newName: String,
    oldSoundRecord: SoundRecord,
    application: Application,
    setChange: (SoundRecord) -> Unit = {}
) {

    val mediaDir = application.externalMediaDirs.first().let {
        File(it, application.resources.getString(R.string.file_record))
    }

    val oldFile = File(oldSoundRecord.filePath ?: "")
    val newFile = File(mediaDir, "$newName.mp4")
    oldFile.renameTo(newFile)
    val newSoundRecord = SoundRecord(
        oldSoundRecord.id,
        newName,
        oldSoundRecord.duration,
        newFile.absolutePath,
        oldSoundRecord.createdAt
    )
    setChange(newSoundRecord)
}