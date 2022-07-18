package com.arash.altafi.soundrecorder.ui.saved

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.arash.altafi.soundrecorder.App
import com.arash.altafi.soundrecorder.R
import com.arash.altafi.soundrecorder.adapter.SavedSoundAdapter
import com.arash.altafi.soundrecorder.databinding.FragmentSavedSoundBinding
import com.arash.altafi.soundrecorder.model.SoundRecord
import com.arash.altafi.soundrecorder.ui.ViewModelFactory
import com.arash.altafi.soundrecorder.ui.detail.DetailRecordActivity
import com.arash.altafi.soundrecorder.ui.detail.DetailRecordActivity.Companion.DETAIL_EXTRA_BUNDLE
import com.arash.altafi.soundrecorder.utils.deleteFile
import javax.inject.Inject

class SavedSoundFragment : Fragment() {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: SavedSoundViewModel by viewModels { factory }

    private var _binding: FragmentSavedSoundBinding? = null
    private val binding get() = _binding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as App).mainComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSavedSoundBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(activity != null) {
            observer()
        }
    }

    private fun observer() {
        viewModel.getAllRecord().observe(viewLifecycleOwner,::setListSavedSound)
    }

    private fun setListSavedSound(item: List<SoundRecord>) {
        binding?.rvSoundList?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter =  SavedSoundAdapter(
                itemList = item,
                onItemClick = { soundRecord ->
                    val intent = Intent(requireContext(), DetailRecordActivity::class.java).apply {
                        putExtra(DETAIL_EXTRA_BUNDLE, soundRecord)
                    }
                    startActivity(intent)
                },
                onItemLongClick = { soundRecord ->
                   deleteMessage(soundRecord)
                },
            )
        }
    }

    private fun deleteMessage(soundRecord: SoundRecord) {
        binding?.root?.let { root ->
            Snackbar.make(
                requireContext(),
                root,
                getString(R.string.txt_delete_msg),
                Snackbar.LENGTH_SHORT,
            ).setAction("Yes") {
                viewModel.deleteItem(soundRecord)
                deleteFile(soundRecord.filePath ?: "")
                Toast.makeText(requireContext(), getString(R.string.txt_record_deleted), Toast.LENGTH_SHORT).show()
            }.show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}