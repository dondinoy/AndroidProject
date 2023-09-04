package learn.daniel.rickAndMortyNew.data

import androidx.paging.PageKeyedDataSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import learn.daniel.rickAndMortyNew.network.response.GetEpisodesPageResponse
import learn.daniel.rickAndMortyNew.repository.EpisodesRepository

class EpisodesDataSource (
    private val coroutineScope: CoroutineScope ,
    private val repository: EpisodesRepository
): PageKeyedDataSource<Int , GetEpisodesPageResponse>() {
    override fun loadInitial(
        params: LoadInitialParams<Int> ,
        callback: LoadInitialCallback<Int , GetEpisodesPageResponse>
    ) {
        coroutineScope.launch {
            val page = repository.getEpisodes(1)

            if (page == null) {
                callback.onResult(emptyList() , null , null)
                return@launch
            }
           // callback.onResult(page.results , null , getPageIndexFromNext(page.info.next))
        }
    }

    override fun loadAfter(
        params: LoadParams<Int> ,
        callback: LoadCallback<Int , GetEpisodesPageResponse>
    ) {
        coroutineScope.launch {
            val page = repository.getEpisodes(params.key)

            if (page == null) {
                callback.onResult(emptyList() , null)
                return@launch
            }
            //callback.onResult(page.results , getPageIndexFromNext(page.info.next))
        }
    }

    override fun loadBefore(
        params: LoadParams<Int> ,
        callback: LoadCallback<Int , GetEpisodesPageResponse>
    ) {
        TODO("Not yet implemented")
    }

    private fun getPageIndexFromNext(next: String?): Int? {
        return next?.split("?page=")?.get(1)?.toInt()
    }
}

