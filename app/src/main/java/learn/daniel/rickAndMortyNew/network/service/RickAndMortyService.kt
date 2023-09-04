package learn.daniel.rickAndMortyNew.network.service
import learn.daniel.rickAndMortyNew.network.response.GetCharacterByIdResponse
import learn.daniel.rickAndMortyNew.network.response.GetCharactersPageResponse
import learn.daniel.rickAndMortyNew.network.response.GetEpisodeByIdResponse
import learn.daniel.rickAndMortyNew.network.response.GetEpisodesPageResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RickAndMortyService {

    @GET("character/{character-id}")
    suspend fun getCharacterById(
        @Path("character-id") characterId:Int
    ): Response<GetCharacterByIdResponse>

    @GET("character")
    suspend fun getCharactersPage(
        @Query("page") pageIndex:Int
    ): Response<GetCharactersPageResponse>

    @GET("episode/{episode-id}")
    suspend fun getEpisodeById(
        @Path("episode-id") episodeId:Int
    ): Response<GetEpisodeByIdResponse>

    @GET("episode/{episode-range}")
    suspend fun getEpisodeRange(
        @Path("episode-range") episodeRange:String
    ): Response<List<GetEpisodeByIdResponse>>


    @GET("episode")
    suspend fun getEpisodeByPage(
        @Query("page") pageIndex:Int
    ): Response<GetEpisodesPageResponse>


    @GET("search/character")
    suspend fun searchCharacter(@Query("q") query: String): GetCharacterByIdResponse

    companion object {
        fun create(): RickAndMortyService =
            Retrofit.Builder()
                .baseUrl("https://rickandmortyapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(RickAndMortyService::class.java)
    }

}

