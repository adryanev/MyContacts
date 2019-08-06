package com.adryanev.dicoding.mycontacts.ui.detail


import androidx.appcompat.app.AlertDialog
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.adryanev.dicoding.mycontacts.R
import com.adryanev.dicoding.mycontacts.databinding.FragmentContactDetailBinding
import com.adryanev.dicoding.mycontacts.utils.InjectorUtils
import com.adryanev.dicoding.mycontacts.viewmodels.ContactDetailViewModel
import kotlinx.android.synthetic.main.fragment_contact_detail.*
import kotlinx.android.synthetic.main.main_contact_item.*
import timber.log.Timber


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
        binding.editButton.setOnClickListener {
            val directions = ContactDetailFragmentDirections.actionContactDetailFragmentToEditContactFragment(args.id)
            Timber.d("TO EDIT CONTACT: ${args.id}")
            it.findNavController().navigate(directions)
        }




        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_detail, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_delete -> {

               val builder =  AlertDialog.Builder(context!!)
                builder.setTitle("Hapus Kontak")
                builder.setMessage("Anda yakin untuk menghapus kontak?")
                builder.setPositiveButton("Ya"){
                    dialog, which ->
                    run {
                        contactDetailViewModel.deleteContact(args.id)
                        Toast.makeText(context, "Kontak terhapus", Toast.LENGTH_SHORT).show()
                        findNavController().navigateUp()
                    }
                }
                builder.setNegativeButton("Tidak"){
                    dialog, which -> Toast.makeText(context, "Kontak tidak terhapus",Toast.LENGTH_SHORT).show()
                }
                builder.show()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}
