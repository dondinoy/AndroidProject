package learn.daniel.rickAndMortyNew.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.airbnb.epoxy.EpoxyRecyclerView
import learn.daniel.rickAndMortyNew.Constants
import learn.daniel.rickAndMortyNew.epoxy.controller.CharacterListPagingEpoxyController
import learn.daniel.rickAndMortyNew.viewModel.CharactersViewModel
import learn.daniel.rickandmortynew.R

class CharacterListFragment : Fragment() {

    private val epoxyController = CharacterListPagingEpoxyController(::onCharacterSelected)

    private val viewModel: CharactersViewModel by lazy {
        ViewModelProvider(this).get(CharactersViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater ,
        container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_list , container , false)
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        viewModel.charactersPagedListLivaData.observe(viewLifecycleOwner) { pagedList ->
            epoxyController.submitList(pagedList)
        }
        view.findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView).setController(epoxyController)
    }


    private fun onCharacterSelected(characterId: Int) {
//       val directions=CharacterListFragmentDirections.actionCharacterListFragmentToCharacterDetailsFragment(
//           characterId = characterId
//       )
        findNavController().navigate(R.id.characterDetailsFragment, Bundle().apply { putInt(Constants.INTENT_EXTRA_CHARACTER_ID, characterId) })
    }
}



