package learn.daniel.rickAndMortyNew.network.service

import androidx.appcompat.widget.SearchView
import learn.daniel.rickAndMortyNew.network.response.GetCharacterByIdResponse
import learn.daniel.rickAndMortyNew.network.response.GetCharactersPageResponse
import learn.daniel.rickAndMortyNew.network.response.GetEpisodeByIdResponse
import learn.daniel.rickAndMortyNew.network.response.GetEpisodesPageResponse
import learn.daniel.rickAndMortyNew.network.response.SimpleResponse
import retrofit2.Response

class ApiClient(
    private val rickAndMortyService: RickAndMortyService
) {
    suspend fun getCharacterById(characterId: Int): SimpleResponse<GetCharacterByIdResponse> {
        return  safeApiCall { rickAndMortyService.getCharacterById(characterId) }
    }

    suspend fun getCharactersPage(pageIndex: Int): SimpleResponse<GetCharactersPageResponse> {
        return safeApiCall { rickAndMortyService.getCharactersPage(pageIndex) }
    }



    suspend fun getEpisodes(episodeId: Int): SimpleResponse<GetEpisodeByIdResponse> {
        return  safeApiCall { rickAndMortyService.getEpisodeById(episodeId) }
    }
    suspend fun getEpisodeByPage(pageIndex: Int): SimpleResponse<GetEpisodesPageResponse> {
        return  safeApiCall { rickAndMortyService.getEpisodeByPage(pageIndex) }
    }

    suspend fun getEpisodeRange(episodeRange: String): SimpleResponse<List<GetEpisodeByIdResponse>> {
        return safeApiCall { rickAndMortyService.getEpisodeRange(episodeRange) }
    }


    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        }catch (e: Exception){
            SimpleResponse.failure(e)
        }
    }
}
