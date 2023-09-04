package learn.daniel.rickAndMortyNew.repository

import learn.daniel.rickAndMortyNew.mapper.CharacterMapper
import learn.daniel.rickAndMortyNew.api.model.Character
import learn.daniel.rickAndMortyNew.network.NetworkLayer
import learn.daniel.rickAndMortyNew.network.response.GetCharacterByIdResponse
import learn.daniel.rickAndMortyNew.network.response.GetEpisodeByIdResponse

class SharedRepository {
    suspend fun getCharacterById(characterId: Int): Character? {
        val request= NetworkLayer.apiClient.getCharacterById(characterId)

        if (request.failed || !request.isSuccessful){
            return null
        }

        val networkEpisode= getEpisodesFromCharacterResponce(request.body)

        return CharacterMapper.buildFrom(response = request.body, episodes=networkEpisode)
    }

    private suspend fun getEpisodesFromCharacterResponce(
        characterResponce: GetCharacterByIdResponse
    ): List<GetEpisodeByIdResponse> {
        val episodeRange = characterResponce.episode.map {
            it.substring(startIndex = it.lastIndexOf("/") + 1)
        }.toString()

        val request = NetworkLayer.apiClient.getEpisodeRange(episodeRange)

        if (request.failed || !request.isSuccessful){
            return emptyList()
        }
     return request.body
    }
}
