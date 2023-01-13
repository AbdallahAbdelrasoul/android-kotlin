package com.udacity.asteroidradar.screens.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.domain.Asteroid

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by lazy {
        val viewModelFactory = MainViewModelFactory(requireContext())
        ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        // set the Recycler view Adapter
        binding.asteroidRecycler.adapter = MainListAdapter(
            MainListAdapter.Companion.AsteroidListener {
                navigateToDetailScreen(it)
            }
        )

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun navigateToDetailScreen(asteroid: Asteroid) {
        findNavController().navigate(
            MainFragmentDirections.actionShowDetail(asteroid)
        )
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.main_overflow_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        viewModel.asteroidsToShow.value = item.title.toString()

        return super.onOptionsItemSelected(item)
    }
}
