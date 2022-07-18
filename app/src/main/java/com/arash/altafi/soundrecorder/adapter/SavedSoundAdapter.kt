package com.arash.altafi.soundrecorder.adapter

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.recyclerview.widget.RecyclerView
import com.arash.altafi.soundrecorder.R
import com.arash.altafi.soundrecorder.databinding.ItemSavedSoundBinding
import com.arash.altafi.soundrecorder.model.SoundRecord
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.IOException

class SavedSoundAdapter(
    private val itemList: List<SoundRecord> = ArrayList(),
    private val onItemClick: (SoundRecord) -> Unit = {},
    private val onItemLongClick: (SoundRecord) -> Unit = {},
    private val coroutineScope: CoroutineScope = CoroutineScope(Main)
): RecyclerView.Adapter<SavedSoundAdapter.MyViewHolder>() {

    inner class MyViewHolder(
        private val binding: ItemSavedSoundBinding
    ): RecyclerView.ViewHolder(binding.root) {

        private var mediaPlayer: MediaPlayer? = null

        fun bind(soundRecord: SoundRecord) = with(binding) {
            val minutes = (soundRecord.duration / 1000) / 60
            val seconds = (soundRecord.duration / 1000) % 60

            tvDate.text = soundRecord.createdAt
            tvName.text = soundRecord.name
            tvDuration.text = root.context.getString(R.string.time_format_mm_ss, minutes, seconds)
            seekBar.max = soundRecord.duration.toInt()

            btnPlayOrStop.setOnClickListener {
                checkStatusMediaPlayer(soundRecord)
            }

            seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(
                    seekBar: SeekBar?,
                    progress: Int,
                    fromUser: Boolean
                ) {
                    if(fromUser) {
                        tvDuration.text = root.context.getString(
                            R.string.time_format_mm_ss,
                            (progress / 1000) / 60,
                            (progress / 1000) % 60
                        )
                        mediaPlayer?.seekTo(progress)
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {}
                override fun onStopTrackingTouch(seekBar: SeekBar?) {}
            })

            root.setOnClickListener {
                onItemClick(soundRecord)
                stopPlaying()
            }
            root.setOnLongClickListener {
                onItemLongClick(soundRecord)
                true
            }
        }

        private fun changeImageBtn(play: Boolean): Int =
            if(!play) R.drawable.ic_baseline_play_circle_outline_24
            else R.drawable.ic_baseline_stop_24


        private suspend fun runSeekBar() = with(binding) {
            while (mediaPlayer?.isPlaying == true) {
                try {
                    val currentTime = mediaPlayer!!.currentPosition
                    seekBar.progress = currentTime
                    tvDuration.text = root.context.getString(
                        R.string.time_format_mm_ss,
                        (currentTime / 1000) / 60,
                        (currentTime / 1000) % 60
                    )
                    delay(1000)
                } catch (e: Exception) {
                    seekBar.progress = 0
                }
            }
        }

        private fun checkStatusMediaPlayer(soundRecord: SoundRecord) = with(binding) {
            if (mediaPlayer?.isPlaying == true && mediaPlayer != null) {
                btnPlayOrStop.setImageResource(changeImageBtn(false))
                stopPlaying()
                return@with
            }
            startPlaying(soundRecord.filePath)
            btnPlayOrStop.setImageResource(changeImageBtn(true))
            coroutineScope.launch {
                runSeekBar()
                btnPlayOrStop.setImageResource(changeImageBtn(false))
                seekBar.progress = 0
                tvDuration.text = root.context.getString(
                    R.string.time_format_mm_ss,
                        (soundRecord.duration / 1000) / 60,
                        (soundRecord.duration / 1000) % 60
                    )
            }
        }

        private fun stopPlaying() {
            mediaPlayer?.reset()
            mediaPlayer?.release()
            mediaPlayer = null
        }

        private fun startPlaying(filePath: String?) {
            mediaPlayer = MediaPlayer().apply {
                try {
                    setDataSource(filePath)
                    prepare()
                    start()
                } catch (e: IOException) {
                    Timber.e("$e")
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            ItemSavedSoundBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    override fun getItemCount(): Int = itemList.size
}