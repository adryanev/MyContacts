package com.adryanev.dicoding.mycontacts.ui.editcontact


import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.adryanev.dicoding.mycontacts.databinding.FragmentEditContactBinding
import com.adryanev.dicoding.mycontacts.utils.InjectorUtils
import com.adryanev.dicoding.mycontacts.viewmodels.EditContactViewModel
import timber.log.Timber
import java.util.*

/**
 * A simple [Fragment] subclass.
 *
 */
class EditContactFragment : Fragment() {


    private val args : EditContactFragmentArgs by navArgs()
    private val viewModel: EditContactViewModel by viewModels {
        InjectorUtils.provideEditContactViewModelFactory(requireContext(), args.id)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Timber.d("ON CREATE EDIT CONTACT")
        val binding = FragmentEditContactBinding.inflate(inflater, container, false).apply {
            vm = viewModel
            lifecycleOwner = this@EditContactFragment
        }

        viewModel.getContact().observe(this,androidx.lifecycle.Observer {
            if(TextUtils.isEmpty(Objects.requireNonNull(it).email)){
                binding.editEmailEditText.error = "Email tidak boleh kosong"
                binding.editEmailEditText.requestFocus()
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(it.email).matches()){
                binding.editEmailEditText.error = "Email tidak valid"
                binding.editEmailEditText.requestFocus()
            }
            else if(TextUtils.isEmpty(Objects.requireNonNull(it).nomorHp)){
                binding.editNomorHpEditText.error = "Nomor Hp Tidak Boleh Kosong"
                binding.editNomorHpEditText.requestFocus()
            }

            else if(TextUtils.isEmpty(Objects.requireNonNull(it).nama)){
                binding.editNamaEditText.error = "Nama Tidak Boleh Kosong"
                binding.editNamaEditText.requestFocus()
            }

            else if(TextUtils.isEmpty(Objects.requireNonNull(it).alamat)){
                binding.editAlamatEditText.error = "Alamat Tidak Boleh Kosong"
                binding.editAlamatEditText.requestFocus()
            }
            else{
                viewModel.updateContact(it)
                findNavController().navigateUp()
                Toast.makeText(context, "Berhasil Update Kontak", Toast.LENGTH_SHORT).show()

            }

        })

        return binding.root
    }


}
