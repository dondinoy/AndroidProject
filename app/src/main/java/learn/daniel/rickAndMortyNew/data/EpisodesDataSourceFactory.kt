package learn.daniel.rickAndMortyNew.data

import androidx.paging.DataSource
import kotlinx.coroutines.CoroutineScope
import learn.daniel.rickAndMortyNew.network.response.GetEpisodesPageResponse
import learn.daniel.rickAndMortyNew.repository.EpisodesRepository

class EpisodesDataSourceFactory(
    private val coroutinScope: CoroutineScope ,
    private val repository: EpisodesRepository
): DataSource.Factory<Int, GetEpisodesPageResponse>() {
    override fun create(): DataSource<Int , GetEpisodesPageResponse> {
        return EpisodesDataSource(coroutinScope, repository)
    }
}