package ru.itis.homework2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.itis.homework2.databinding.ItemMusicBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager

class MusicAdapter(
    private val list: List<Music>,
    private val glide: RequestManager,
    private val onClick : (Music) -> Unit,
) : RecyclerView.Adapter<MusicHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MusicHolder = MusicHolder(
        binding = ItemMusicBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ),
        glide = glide,
        onClick = onClick,
    )


    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MusicHolder, position: Int) {
        holder.onBind(list[position])


    }

}