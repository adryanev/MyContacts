package com.adryanev.dicoding.mycontacts.bindings

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hbb20.GThumb
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("imageFromName")
fun bindImageFromName(view: GThumb, name: String?){
    if(!name.isNullOrEmpty()){
        view.loadThumbForName("",name)
    }

}

@BindingAdapter("isGone")
fun bindIsGone(view: FloatingActionButton, isGone: Boolean?) {
    if (isGone == null || isGone) {
        view.hide()
    } else {
        view.show()
    }
}