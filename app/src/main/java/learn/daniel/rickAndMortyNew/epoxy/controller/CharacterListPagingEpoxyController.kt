package learn.daniel.rickAndMortyNew.epoxy.controller

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.squareup.picasso.Picasso
import learn.daniel.rickandmortynew.R
import learn.daniel.rickandmortynew.databinding.ModelChracterListItemBinding
import learn.daniel.rickAndMortyNew.epoxy.ViewBindingKotlinModel
import learn.daniel.rickAndMortyNew.network.response.GetCharacterByIdResponse


class CharacterListPagingEpoxyController(
    val onCharacterSelected: (Int) -> Unit
): PagedListEpoxyController<GetCharacterByIdResponse>() {

    override fun buildItemModel(
        currentPosition: Int ,
        item: GetCharacterByIdResponse?
    ): EpoxyModel<*>{
        return CharacterGridItemEpoxyModel(
            characterId = item!!.id,
            imageUrl = item.image ,
            name = item.name,
            onCharacterSelected = onCharacterSelected
        ).id(item.id)
    }


    data class CharacterGridItemEpoxyModel(
        val characterId:Int,
        val imageUrl:String,
        val name: String,
        val onCharacterSelected:(Int)->Unit
    ):ViewBindingKotlinModel<ModelChracterListItemBinding>(R.layout.model_chracter_list_item) {

        override fun ModelChracterListItemBinding.bind() {
            Picasso.get().load(imageUrl).into(characterImageView)
            characterNameTextView.text =name

            root.setOnClickListener {
                onCharacterSelected(characterId)
            }
        }

    }

}


