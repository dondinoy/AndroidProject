package learn.daniel.rickAndMortyNew.viewModel

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import learn.daniel.rickAndMortyNew.api.model.Character
import learn.daniel.rickAndMortyNew.repository.SharedRepository

class SharedViewModel:ViewModel() {
    private val repository= SharedRepository()

    private val _characterByIdLiveData=MutableLiveData<Character?>()
    val characterByIdLiveData:LiveData<Character?> = _characterByIdLiveData

    @SuppressLint("SuspiciousIndentation")
    fun refreshCharacter(characterId:Int){
        viewModelScope.launch {
            val response=repository.getCharacterById(characterId)
                _characterByIdLiveData.postValue(response)
        }
    }
}