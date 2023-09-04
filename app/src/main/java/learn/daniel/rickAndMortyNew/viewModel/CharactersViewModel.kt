package learn.daniel.rickAndMortyNew.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.launch
import learn.daniel.rickAndMortyNew.Constants
import learn.daniel.rickAndMortyNew.data.CharactersDataSourceFactory
import learn.daniel.rickAndMortyNew.network.response.GetCharacterByIdResponse
import learn.daniel.rickAndMortyNew.repository.CharactersRepository

class CharactersViewModel : ViewModel() {

    private val repository = CharactersRepository()
    private val pageIListConfig: PagedList.Config = PagedList.Config.Builder()
        .setPageSize(Constants.PAGE_SIZE)
        .setPrefetchDistance(Constants.PREFETCH_DISTANCE)
        .build()

    private val dataSourceFactory = CharactersDataSourceFactory(viewModelScope,repository)
    val charactersPagedListLivaData: LiveData<PagedList<GetCharacterByIdResponse>> =
        LivePagedListBuilder(dataSourceFactory,pageIListConfig).build()
}