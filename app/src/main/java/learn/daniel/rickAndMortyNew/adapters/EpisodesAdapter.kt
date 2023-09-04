package learn.daniel.rickAndMortyNew.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LAYOUT_DIRECTION_INHERIT
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import learn.daniel.rickAndMortyNew.api.model.Episode
import learn.daniel.rickandmortynew.R
import learn.daniel.rickandmortynew.databinding.ModelEpisodeListItemBinding

class EpisodesAdapter : RecyclerView.Adapter<EpisodesAdapter.EpisodeViewHolder>(){
    var items : ArrayList<Episode> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int): EpisodeViewHolder {
        return EpisodeViewHolder(ModelEpisodeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder , position: Int) {
        holder.bind(position, items[position])
    }

    override fun getItemCount(): Int=items.size


    class EpisodeViewHolder(private val binding: ModelEpisodeListItemBinding) : ViewHolder(binding.root){

        fun bind(position: Int, episode: Episode){
           binding.episodeNameTextView.text = episode.name
            binding.episodeIdTextView.text = episode.id.toString()
        }
}
}