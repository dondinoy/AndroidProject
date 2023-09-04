package learn.daniel.rickAndMortyNew.epoxy.controller

import com.airbnb.epoxy.CarouselModel_
import com.airbnb.epoxy.EpoxyController
import com.squareup.picasso.Picasso
import learn.daniel.rickAndMortyNew.api.model.Character
import learn.daniel.rickAndMortyNew.api.model.Episode

import learn.daniel.rickAndMortyNew.epoxy.LoadingEpoxyModel
import learn.daniel.rickAndMortyNew.epoxy.ViewBindingKotlinModel
import learn.daniel.rickandmortynew.R
import learn.daniel.rickandmortynew.databinding.ModelCharacterDetailsDataPointBinding
import learn.daniel.rickandmortynew.databinding.ModelCharacterDetailsHeaderBinding
import learn.daniel.rickandmortynew.databinding.ModelCharacterDetailsImageBinding
import learn.daniel.rickandmortynew.databinding.ModelEpisodeCarouselItemEpoxyControllerBinding

class CharecterDetailsEpoxyController:EpoxyController (){

    var isLoadind:Boolean=true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }
    var character: Character? = null
        set(value){
            field=value
            if (field!=null){
                isLoadind=false
               requestModelBuild()
            }
        }
    override fun buildModels() {
        if(isLoadind){
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }
        if (character==null){
            // TODO: error 
            return
        }
        HeaderEpoxyModel(
            name = character!!.name,
            gender = character!!.gender,
            status = character!!.status
        ).id("header").addTo(this)

        ImageEpoxyModel(
            imageUrl = character!!.image,
        ).id("image").addTo(this)

        if(character!!.episodeList.isNotEmpty()){
            val items=character!!.episodeList.map {
                EpisodeCaruselItem(it).id(it.id)
            }
            CarouselModel_()
                .id("episode_carousel")
                .models(items)
                .numViewsToShowOnScreen(1.15f)
                .addTo(this)
        }

        DataPointEpoxyModel(
            title = "Location",
            description = character!!.location.name
        ).id("dataPoint").addTo(this)

        DataPointEpoxyModel(
            title = "Origen",
            description = character!!.origin.name
        ).id("dataPoint").addTo(this)

        DataPointEpoxyModel(
            title = "Species",
            description = character!!.species
        ).id("dataPoint").addTo(this)

    }
    data class HeaderEpoxyModel(
        val name:String,
        val gender:String,
        val status:String
    ): ViewBindingKotlinModel<ModelCharacterDetailsHeaderBinding>(R.layout.model_character_details_header){

        override fun ModelCharacterDetailsHeaderBinding.bind(){
                nameTextView.text=name
                aliveTextView.text=status

                if (status.equals("Alive",ignoreCase = true)){
                    aliveImageView.setImageResource(R.drawable.baseline_circle_alive)
                }else if (status.equals("Dead",ignoreCase = true)){
                    aliveImageView.setImageResource(R.drawable.baseline_circle_dead)
                }else{
                    aliveImageView.setImageResource(R.drawable.baseline_circle_unknown)
                }

                if (gender.equals("male",ignoreCase = true)) {
                    genderImageView.setImageResource(R.drawable.baseline_male_24)
                }else{
                    genderImageView.setImageResource(R.drawable.baseline_female_24)
                }

            }
        }
    data class ImageEpoxyModel(
        val imageUrl: String ,
    ): ViewBindingKotlinModel<ModelCharacterDetailsImageBinding>(R.layout.model_character_details_image){
        override fun ModelCharacterDetailsImageBinding.bind(){
            Picasso.get().load(imageUrl).into(headerImage)
        }
    }

    data class DataPointEpoxyModel(
        val title:String,
        val description:String,
    ): ViewBindingKotlinModel<ModelCharacterDetailsDataPointBinding>(R.layout.model_character_details_data_point){
        override fun ModelCharacterDetailsDataPointBinding.bind(){
            lableTextView.text=title
            textView.text=description
        }
    }
    data class EpisodeCaruselItem(
        val episode:Episode
    ):ViewBindingKotlinModel<ModelEpisodeCarouselItemEpoxyControllerBinding>(R.layout.model_episode_carousel_item_epoxy_controller){

        override fun ModelEpisodeCarouselItemEpoxyControllerBinding.bind(){
            episodeTextView.text=episode.episode
            episodeDetailsTextView.text="${episode.name}\n${episode.airDate}"
        }
        }
    }


