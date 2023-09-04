package learn.daniel.rickAndMortyNew.mapper

import learn.daniel.rickAndMortyNew.api.model.Episode
import learn.daniel.rickAndMortyNew.network.response.GetEpisodeByIdResponse

object EpisodeMapper {

    fun buildFrom(networkEpisode: GetEpisodeByIdResponse): Episode{
        return Episode(
            id=networkEpisode.id,
            name = networkEpisode.name,
            airDate = networkEpisode.airDate,
            episode = networkEpisode.episode
        )
    }
}