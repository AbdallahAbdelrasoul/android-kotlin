package com.udacity.shoestore.screens.shoelist

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.ActivityMainViewModel
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeItemBinding


class ShoeListFragment : Fragment() {

    private lateinit var binding: FragmentShoeListBinding
    private lateinit var itemShoeBinding: ShoeItemBinding
    private val viewModel: ActivityMainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentShoeListBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.shoeList.observe(viewLifecycleOwner, Observer { newShoeList ->

            Toast.makeText(requireContext(), newShoeList.size.toString() + " Shoes", Toast.LENGTH_SHORT).show()

            for (shoe in newShoeList) {
                // Inflate the shoe item layout for this fragment's LinearLayout
                itemShoeBinding = ShoeItemBinding.inflate(inflater,binding.myLinearLayout,false)

                with(itemShoeBinding){
                    shoeName.text = shoe.name
                    shoeSize.text = shoe.size.toString()
                    shoeCompany.text = shoe.company
                    shoeDesc.text = shoe.description
                }

                binding.myLinearLayout.addView(itemShoeBinding.root)
            }

        })

        viewModel.toShoeDetailScreenEvent.observe(viewLifecycleOwner, Observer { toDetailScreen ->
            if(toDetailScreen){
                gotoDetailScreen()
                viewModel.onToShoeDetailComplete()
            }
        })

        // Set Options Menu
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Toast.makeText(requireContext(),"logout",Toast.LENGTH_SHORT).show()

        // pop up the ShoeListFragment from the backstack when Logout
        when(item.title){
            resources.getString(R.string.logout) -> findNavController().popBackStack(R.id.shoeListFragment,true)
        }

        return NavigationUI.onNavDestinationSelected(item,view!!.findNavController())
                || super.onOptionsItemSelected(item)
    }

    private fun gotoDetailScreen(){
        findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment())
    }

    override fun onResume() {
        super.onResume()
        // Remove the UpButton of the toolbar in this fragment
        (activity as AppCompatActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(false)

    }
}