package ru.itis.homework2

//import android.os.Bundle
//import android.view.View
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import ru.itis.homework2.databinding.FragmentProfileBinding
//import ru.itis.homework2.databinding.FragmentSearchBinding
//import com.bumptech.glide.Glide
//import com.bumptech.glide.RequestManager
//
//class SearchFragment : Fragment(R.layout.fragment_search) {
//
//    private var binding: FragmentSearchBinding? = null
//
//    private var adapter: MusicAdapter? = null
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        binding = FragmentSearchBinding.bind(view)
//
//        initAdapter()
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        binding = null
//    }
//
//    private fun initAdapter(){
//        adapter = MusicAdapter(
//            list = MusicRepository.music,
//            glide = Glide.with(this),
//        )
//
//        binding?.run {
//            rvMusic.adapter = adapter
//            rvMusic.layoutManager = LinearLayoutManager(requireContext())
//        }
//    }
//
//
//}

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import ru.itis.homework2.MusicDescriptionFragment

import ru.itis.homework2.databinding.FragmentSearchBinding
import com.google.android.material.snackbar.Snackbar

class SearchFragment : Fragment(R.layout.fragment_search) {
    private var binding : FragmentSearchBinding? = null
    private var adapter : MusicAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)
        initAdapter()
    }

    private fun initAdapter(){
        adapter = MusicAdapter(
            list = MusicRepository.music,
            glide = Glide.with(this),
            onClick = {
                findNavController().navigate(
                    resId = R.id.action_searchFragment_to_musicDescriptionFragment,
                    args = MusicDescriptionFragment.bundle(
                        id = it.id
                    )
                )
            }
        )
        binding?.run{
            rvMusic.adapter = adapter
            rvMusic.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}

