package learn.daniel.rickAndMortyNew.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.lifecycle.ViewModelProvider
import com.airbnb.epoxy.EpoxyRecyclerView
import learn.daniel.rickAndMortyNew.Constants
import learn.daniel.rickAndMortyNew.epoxy.controller.CharecterDetailsEpoxyController
import learn.daniel.rickAndMortyNew.viewModel.SharedViewModel
import learn.daniel.rickandmortynew.R


class CharacterDetailsFragment : Fragment() {
    private val viewModel: SharedViewModel by lazy {
        ViewModelProvider(this).get(SharedViewModel::class.java)
    }
    private val epoxyController= CharecterDetailsEpoxyController()
//    private val safeArgs:CharacterDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_character_details , container , false)
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)
        viewModel.characterByIdLiveData.observe(viewLifecycleOwner) { character ->

            epoxyController.character = character

            if (character == null) {
                Toast.makeText(
                    requireActivity() ,
                    "Unsuccessful Network Call!" ,
                    Toast.LENGTH_SHORT
                ).show()
                return@observe
            }
        }
                val id = arguments?.getInt(Constants.INTENT_EXTRA_CHARACTER_ID ,1)?:0
                viewModel.refreshCharacter(characterId=id)

                val epoxyRecyclerView = view.findViewById<EpoxyRecyclerView>(R.id.epoxyRecyclerView)
                epoxyRecyclerView.setControllerAndBuildModels(epoxyController)
   }
}




