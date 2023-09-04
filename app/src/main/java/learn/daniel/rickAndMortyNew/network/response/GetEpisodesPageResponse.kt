package learn.daniel.rickAndMortyNew.network.response

import com.google.gson.annotations.SerializedName
import learn.daniel.rickAndMortyNew.api.model.Episode

data class GetEpisodesPageResponse (
    @SerializedName("info") var info: Info = Info() ,
    @SerializedName("results") var results:List<Episode> = arrayListOf()
) {
    data class Info(
        val count: Int = 0 ,
        val next: String? = null,
        val pages: Int = 0,
        val prev: String? = null
    )
}


