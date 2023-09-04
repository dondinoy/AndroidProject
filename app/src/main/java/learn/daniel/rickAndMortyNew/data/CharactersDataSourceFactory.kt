package learn.daniel.rickAndMortyNew.data

import androidx.paging.DataSource
import kotlinx.coroutines.CoroutineScope
import learn.daniel.rickAndMortyNew.repository.CharactersRepository
import learn.daniel.rickAndMortyNew.network.response.GetCharacterByIdResponse

class CharactersDataSourceFactory(
    private val coroutinScope: CoroutineScope ,
    private val repository: CharactersRepository
):DataSource.Factory<Int, GetCharacterByIdResponse>() {
    override fun create(): DataSource<Int , GetCharacterByIdResponse> {
        return CharactersDataSource(coroutinScope, repository)
    }
}