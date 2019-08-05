package com.adryanev.dicoding.mycontacts.ui.main


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController

import com.adryanev.dicoding.mycontacts.R
import com.adryanev.dicoding.mycontacts.adapters.MainAdapter
import com.adryanev.dicoding.mycontacts.data.entities.Contact
import com.adryanev.dicoding.mycontacts.databinding.FragmentMainBinding
import com.adryanev.dicoding.mycontacts.utils.InjectorUtils
import com.adryanev.dicoding.mycontacts.viewmodels.MainViewModel

/**
 * A simple [Fragment] subclass.
 *
 */
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels{
        InjectorUtils.provideMainViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentMainBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val adapter = MainAdapter()
        binding.rvContactList.adapter = adapter

        subscribeUi(adapter)
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_profil -> {
                val directions = MainFragmentDirections.actionMainFragmentToProfilFragment()
                findNavController().navigate(directions)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun subscribeUi(adapter: MainAdapter){
        viewModel.contacts.observe(viewLifecycleOwner){
            contacts ->  if(contacts!=null) adapter.submitList(contacts)

        }
    }


}
