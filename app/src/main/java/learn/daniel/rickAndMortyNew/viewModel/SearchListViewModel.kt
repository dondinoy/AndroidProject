package learn.daniel.rickAndMortyNew.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import learn.daniel.rickAndMortyNew.api.model.Character
import learn.daniel.rickAndMortyNew.mapper.CharacterMapper
import learn.daniel.rickAndMortyNew.network.response.GetCharactersPageResponse
import learn.daniel.rickAndMortyNew.network.service.RickAndMortyService
import learn.daniel.rickAndMortyNew.repository.CharactersRepository
import java.io.IOException
import java.lang.Exception

class SearchListViewModel :ViewModel() {

    private val _characters = MutableLiveData<List<Character>>()
    val characters: LiveData<List<Character>> = _characters


    private val repository: CharactersRepository = CharactersRepository()
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading
    private val items : ArrayList<Character> = arrayListOf()
    private var page = 1
    private var isDone = true


    fun initCharacters() {
        viewModelScope.launch{
            val response : GetCharactersPageResponse? = repository.getCharactersPage(page)
            response?.results?.forEach {
                items.add(CharacterMapper.buildFrom(it))
            }
            isDone = true
            _characters.postValue(items)
        }
    }

    fun onSearch(newText: String?) {
        val filtered = items.filter { it.name.contains(newText ?: "") }
        viewModelScope.launch(Dispatchers.Main) {
            delay(300)
            _characters.postValue(filtered)
        }
    }

    fun loadMore() {
        if (isDone) {
            isDone = false
            page++
            initCharacters()
        }

    }

}