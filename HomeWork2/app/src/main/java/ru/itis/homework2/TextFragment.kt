package ru.itis.homework2

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import ru.itis.homework2.databinding.FragmentTextBinding
class TextFragment : Fragment(R.layout.fragment_text) {
    var binding : FragmentTextBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTextBinding.bind(view)
        val text = arguments?.getString(ARG_NAME) ?: "ERROR"
        binding?.run{
            tvTitle.text = "Your text - $text"
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
    companion object{
        private const val ARG_NAME = "ARG_NAME"
        fun bundle(name:String) : Bundle = Bundle().apply {
            putString(ARG_NAME, name)
        }
    }

}