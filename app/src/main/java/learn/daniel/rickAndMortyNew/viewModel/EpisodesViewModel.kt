package learn.daniel.rickAndMortyNew.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.launch
import learn.daniel.rickAndMortyNew.Constants
import learn.daniel.rickAndMortyNew.api.model.Episode
import learn.daniel.rickAndMortyNew.data.EpisodesDataSourceFactory
import learn.daniel.rickAndMortyNew.network.response.GetEpisodesPageResponse
import learn.daniel.rickAndMortyNew.repository.EpisodesRepository

class EpisodesViewModel : ViewModel() {

    val episodesList : MutableLiveData<ArrayList<Episode>> = MutableLiveData()
    private val _episodesList : ArrayList<Episode> = arrayListOf()

    val repository = EpisodesRepository()

    private var isDone = true
    private var page = 1
     fun getEpisodes() {
         viewModelScope.launch {
            val response =  repository.getEpisodes(page)
             isDone = true
             response?.results?.let{
                 _episodesList.addAll(it)
                 episodesList.postValue(_episodesList)
             }
         }
     }

    fun loadMore() {
        if (isDone) {
            isDone = false
            page++
            getEpisodes()
        }

    }
}