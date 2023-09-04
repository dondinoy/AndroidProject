package learn.daniel.rickAndMortyNew.repository

import android.util.Log
import learn.daniel.rickAndMortyNew.network.NetworkLayer
import learn.daniel.rickAndMortyNew.network.response.GetEpisodesPageResponse

class EpisodesRepository {

    suspend fun getEpisodes(pageIndex: Int): GetEpisodesPageResponse? {
        val response = NetworkLayer.apiClient.getEpisodeByPage(pageIndex)

        if (response.failed || !response.isSuccessful) {
            Log.d("Daniel", "exceprion = ${response.exception}")
            return null
        }
        return response.body
    }
}