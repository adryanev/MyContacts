package com.adryanev.dicoding.mycontacts.ui.profil


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.adryanev.dicoding.mycontacts.R

/**
 * A simple [Fragment] subclass.
 *
 */
class ProfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profil, container, false)
    }


}
