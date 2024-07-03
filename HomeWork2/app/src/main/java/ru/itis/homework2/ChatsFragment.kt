package ru.itis.homework2

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import ru.itis.homework2.databinding.FragmentChatsBinding

class ChatsFragment : Fragment(R.layout.fragment_chats) {
    private var binding: FragmentChatsBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChatsBinding.bind(view)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}



