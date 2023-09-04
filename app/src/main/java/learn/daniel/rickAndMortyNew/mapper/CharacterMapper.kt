package learn.daniel.rickAndMortyNew.mapper

import learn.daniel.rickAndMortyNew.api.model.Character
import learn.daniel.rickAndMortyNew.network.response.GetCharacterByIdResponse
import learn.daniel.rickAndMortyNew.network.response.GetEpisodeByIdResponse

object CharacterMapper {
    fun buildFrom(
        response:GetCharacterByIdResponse ,
        episodes:List<GetEpisodeByIdResponse>? = null
    ): Character {
        return Character(
            episodeList = episodes?.map { EpisodeMapper.buildFrom(it) } ?: arrayListOf(),
            gender = response.gender,
            id = response.id,
            image = response.image,
            location = Character.Location(
                name = response.location.name,
                url = response.location.url
            ),
            name = response.name,
            origin = Character.Origin(
                name = response.origin.name,
                url = response.origin.url
            ),
            species = response.species,
            status = response.status
        )
    }
}