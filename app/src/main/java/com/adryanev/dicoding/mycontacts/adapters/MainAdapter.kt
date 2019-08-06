package com.adryanev.dicoding.mycontacts.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.adryanev.dicoding.mycontacts.data.entities.Contact
import com.adryanev.dicoding.mycontacts.databinding.MainContactItemBinding
import com.adryanev.dicoding.mycontacts.ui.main.MainFragmentDirections

class MainAdapter : ListAdapter<Contact, MainAdapter.ContactViewHolder>(ContactDiffCallback()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {

        return ContactViewHolder(MainContactItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = getItem(position)
        holder.apply {
            bind(createOnClickListener(contact.id),contact)

        }

    }
    private fun createOnClickListener(contactId: Int): View.OnClickListener {
        return View.OnClickListener {
            val direction = MainFragmentDirections.actionMainFragmentToContactDetailFragment(contactId)
            it.findNavController().navigate(direction)
        }
    }

    class ContactViewHolder(private val binding: MainContactItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: View.OnClickListener, item: Contact){
            binding.apply {
                clicklistener = listener
                contact = item
                this.mainContactAvatar.loadThumbForName("",item.nama)
                executePendingBindings()
            }

        }
    }

    class ContactDiffCallback: DiffUtil.ItemCallback<Contact>() {
        override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean {
            return oldItem == newItem
        }

    }


}