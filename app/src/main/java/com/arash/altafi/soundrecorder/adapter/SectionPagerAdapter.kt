package com.arash.altafi.soundrecorder.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.arash.altafi.soundrecorder.ui.recorder.RecorderFragment
import com.arash.altafi.soundrecorder.ui.saved.SavedSoundFragment

class SectionPagerAdapter(
    activity: AppCompatActivity
): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position) {
            0 -> fragment = RecorderFragment()
            1 -> fragment = SavedSoundFragment()
            else -> {}
        }
        return fragment as Fragment
    }
}