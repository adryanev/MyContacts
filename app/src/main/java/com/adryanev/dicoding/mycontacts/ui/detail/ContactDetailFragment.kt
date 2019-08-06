package com.adryanev.dicoding.mycontacts.ui.detail


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.adryanev.dicoding.mycontacts.R
import com.adryanev.dicoding.mycontacts.databinding.FragmentContactDetailBinding
import com.adryanev.dicoding.mycontacts.utils.InjectorUtils
import com.adryanev.dicoding.mycontacts.viewmodels.ContactDetailViewModel
import kotlinx.android.synthetic.main.fragment_contact_detail.*
import kotlinx.android.synthetic.main.main_contact_item.*


/**
 * A simple [Fragment] subclass.
 *
 */
class ContactDetailFragment : Fragment() {

    private val args: ContactDetailFragmentArgs by navArgs()
    private val contactDetailViewModel: ContactDetailViewModel by viewModels {
        InjectorUtils.provideDetailContactViewModelFactory(requireContext(),args.id)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentContactDetailBinding.inflate(inflater,container,false).apply {
            vm = contactDetailViewModel
            lifecycleOwner = this@ContactDetailFragment


        }




        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return binding.root
    }


}
