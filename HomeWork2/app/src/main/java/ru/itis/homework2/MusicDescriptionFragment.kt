package ru.itis.homework2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import ru.itis.homework2.databinding.FragmentMusicDescriptionBinding

class MusicDescriptionFragment : Fragment(R.layout.fragment_music_description) {
    private var binding : FragmentMusicDescriptionBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
                binding = FragmentMusicDescriptionBinding.bind(view)
        val id = arguments?.getInt(ARG_NAME) ?: 0
        val music : Music? = MusicRepository.music.find {
            it.id == id
        }
        binding?.run {

            initViews(view, music)
            ivBack.setOnClickListener{
                findNavController().navigate(R.id.action_musicDescriptionFragment_to_searchFragment)
            }
        }
    }

    private fun FragmentMusicDescriptionBinding.initViews(
        view: View,
        music: Music?
    ) {
        Glide.with(view)
            .load(music?.url)
            .into(ivMusicImg)

        tvMusic.text = music?.name
        tvShortDescription.text = "Some information: ${music?.description}"
        tvLongDescription.text = "Long Description: ${music?.longDescription}"
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    companion object{
        private const val ARG_NAME = "ARG_NAME"
        fun bundle(id:Int) : Bundle = Bundle().apply {
            putInt(ARG_NAME, id)
        }
    }
}
