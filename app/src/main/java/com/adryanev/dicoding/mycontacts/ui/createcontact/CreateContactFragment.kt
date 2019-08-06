package com.adryanev.dicoding.mycontacts.ui.createcontact


import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.TextUtilsCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.adryanev.dicoding.mycontacts.R
import com.adryanev.dicoding.mycontacts.databinding.FragmentContactDetailBinding
import com.adryanev.dicoding.mycontacts.databinding.FragmentCreateContactBinding
import com.adryanev.dicoding.mycontacts.utils.InjectorUtils
import com.adryanev.dicoding.mycontacts.viewmodels.CreateContactViewModel
import kotlinx.android.synthetic.main.fragment_create_contact.view.*
import java.util.*


/**
 * A simple [Fragment] subclass.
 *
 */
class CreateContactFragment : Fragment() {


    private val viewModel : CreateContactViewModel by viewModels {
        InjectorUtils.provideCreateContactViewModelFactory(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       val binding = FragmentCreateContactBinding.inflate(inflater,container,false).apply {
            vm = viewModel
           lifecycleOwner = this@CreateContactFragment
       }

        viewModel.getContact().observe(this,androidx.lifecycle.Observer {
            if(TextUtils.isEmpty(Objects.requireNonNull(it).email)){
                binding.createEmailEditText.error = "Email tidak boleh kosong"
                binding.createEmailEditText.requestFocus()
            }
            else if(!Patterns.EMAIL_ADDRESS.matcher(it.email).matches()){
                binding.createEmailEditText.error = "Email tidak valid"
                binding.createEmailEditText.requestFocus()
            }
            else if(TextUtils.isEmpty(Objects.requireNonNull(it).nomorHp)){
                binding.createNomorHpEditText.error = "Nomor Hp Tidak Boleh Kosong"
                binding.createNomorHpEditText.requestFocus()
            }

            else if(TextUtils.isEmpty(Objects.requireNonNull(it).nama)){
                binding.createNamaEditText.error = "Nama Tidak Boleh Kosong"
                binding.createNamaEditText.requestFocus()
            }

            else if(TextUtils.isEmpty(Objects.requireNonNull(it).alamat)){
                binding.createAlamatEditText.error = "Alamat Tidak Boleh Kosong"
                binding.createAlamatEditText.requestFocus()
            }
            else{
               viewModel.saveContact(it)
                findNavController().navigateUp()
                Toast.makeText(context, "Berhasil Menambahkan Kontak",Toast.LENGTH_SHORT).show()

            }

        })

        return binding.root
    }


}
