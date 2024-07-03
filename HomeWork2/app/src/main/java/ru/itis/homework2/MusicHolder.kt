package ru.itis.homework2

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.itis.homework2.databinding.ItemMusicBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager

class MusicHolder(
    private val binding: ItemMusicBinding,
    private val glide: RequestManager,
    private val onClick : (Music) -> Unit,
): ViewHolder(binding.root) {

    fun onBind(music: Music) {
        binding.run {
            tvMusic.text = music.name
            tvDescription.text = music.description

            glide
                .load(music.url)
                .into(ivImage)
            root.setOnClickListener {
                onClick.invoke(music)
            }
        }
    }
}