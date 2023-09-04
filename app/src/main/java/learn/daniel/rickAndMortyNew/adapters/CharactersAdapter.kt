package learn.daniel.rickAndMortyNew.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import learn.daniel.rickAndMortyNew.api.model.Character
import learn.daniel.rickandmortynew.databinding.ModelChracterListItemBinding

class CharactersAdapter (var characters: List<Character> = arrayListOf()) : RecyclerView.Adapter<CharactersAdapter.CharacterVH>() {

    override fun onCreateViewHolder(parent: ViewGroup , viewType: Int) =
        CharacterVH(ModelChracterListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = characters.size

    override fun onBindViewHolder(holder: CharacterVH, position: Int) {
        holder.bind(characters.get(position))
    }


    class CharacterVH(private val binding: ModelChracterListItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(character: Character) {
            Picasso.get().load(character.image).into( binding.characterImageView )
            binding.characterNameTextView.text = character.name
        }
    }
}