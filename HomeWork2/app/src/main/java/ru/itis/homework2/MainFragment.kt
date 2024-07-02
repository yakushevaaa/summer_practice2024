package ru.itis.homework2

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import ru.itis.homework2.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private var binding: FragmentMainBinding? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMainBinding.bind(view)

        binding?.run {
            button.setOnClickListener{
                val name = etEmail.text.toString()
                if(name.length == 0){
                    Snackbar.make(root, "Для отправки текста требуется заполнить поле", Snackbar.LENGTH_SHORT).show()
                }
                else {
                    findNavController().navigate(
                        resId = R.id.action_mainFragment_to_textFragment,
                        args = TextFragment.bundle(
                            name = etEmail.text.toString()
                        )
                    )
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}