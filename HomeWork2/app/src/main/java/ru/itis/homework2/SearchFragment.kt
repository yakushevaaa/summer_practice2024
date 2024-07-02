package ru.itis.homework2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import ru.itis.homework2.databinding.FragmentProfileBinding
import ru.itis.homework2.databinding.FragmentSearchBinding

class SearchFragment : Fragment(R.layout.fragment_search) {

    private var binding: FragmentSearchBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}



