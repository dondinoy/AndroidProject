package learn.daniel.rickAndMortyNew.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import learn.daniel.rickAndMortyNew.adapters.EpisodesAdapter
import learn.daniel.rickAndMortyNew.viewModel.EpisodesViewModel
import learn.daniel.rickandmortynew.R
import learn.daniel.rickandmortynew.databinding.FragmentEpisodeListBinding


class EpisodeListFragment : Fragment(R.layout.fragment_episode_list) {

    private val binding: FragmentEpisodeListBinding by lazy{
        _binding!!
    }
    private var _binding: FragmentEpisodeListBinding? = null
    private var episodesAdapter : EpisodesAdapter ?= null
    private val viewModel: EpisodesViewModel by lazy {
        ViewModelProvider(this).get(EpisodesViewModel::class.java)
    }
    override fun onViewCreated(view:View, savedInstanceState: Bundle?) {
        super.onViewCreated(view,savedInstanceState)
        _binding = FragmentEpisodeListBinding.bind(view)
        //

        episodesAdapter = EpisodesAdapter()
        binding.list.apply {
            layoutManager=LinearLayoutManager(view.context)
            this.adapter = episodesAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val lastPosition = (recyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                    episodesAdapter?.itemCount?.let { count ->
                        if (lastPosition >= 0 && lastPosition + 10 > count && count > 0) {
                            viewModel.loadMore()
                        }
                    }

                }
            })
        }

        this.lifecycleScope.launch {
            viewModel.getEpisodes()
        }

        viewModel.episodesList.observe(viewLifecycleOwner){
            episodesAdapter?.items = it
        }



    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}