package learn.daniel.rickAndMortyNew.repository

import learn.daniel.rickAndMortyNew.network.NetworkLayer
import learn.daniel.rickAndMortyNew.network.response.GetCharactersPageResponse

class CharactersRepository {
    suspend fun getCharactersPage(pageIndex:Int): GetCharactersPageResponse? {
        val response = NetworkLayer.apiClient.getCharactersPage(pageIndex)

        if (response.failed || !response.isSuccessful){
            return null
        }
        return response.body
    }
}