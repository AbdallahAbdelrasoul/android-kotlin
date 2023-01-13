package com.udacity.shoestore.screens.shoedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.ActivityMainViewModel
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding


class ShoeDetailFragment : Fragment() {

    private lateinit var binding: FragmentShoeDetailBinding
    private val viewModel: ActivityMainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentShoeDetailBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.errMsg.observe(viewLifecycleOwner, Observer { errMsg ->
            with(binding) {
                if (!errMsg.equals("")) {
                    if (errMsg.contains("shoe name"))
                        nameTextInputLayout.error = errMsg
                    if (errMsg.contains("company name"))
                        companyTextInputLayout.error = errMsg
                    if (errMsg.contains("shoe size"))
                        sizeTextInputLayout.error = errMsg
                    if (errMsg.contains("description"))
                        descTextInputLayout.error = errMsg
                }
            }
        })

        viewModel.textChanged.observe(viewLifecycleOwner, Observer { changedItem ->
            with(binding){
                if (!changedItem.equals("")) {
                    if (changedItem.contains("shoe name"))
                        nameTextInputLayout.error = null
                    if (changedItem.contains("company name"))
                        companyTextInputLayout.error = null
                    if (changedItem.contains("shoe size"))
                        sizeTextInputLayout.error = null
                    if (changedItem.contains("description"))
                        descTextInputLayout.error = null
                }
            }
        })

        viewModel.toShoeListScreenEvent.observe(viewLifecycleOwner, Observer { go ->
            if (go) {
                gotoShoeListScreen()
                viewModel.onToShoeListScreenComplete()
            }
        })
        return binding.root
    }

    private fun gotoShoeListScreen() {
        findNavController().navigate(ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment())
    }

}