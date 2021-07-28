package com.example.newssearch


import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


/**
 * Created by Sara Elmoghazy
 * Helper Class for Binding actions to views given custom attributes.
 */
object BindingUtils {

    @BindingAdapter(value = ["image"], requireAll = false)
    @JvmStatic
    fun ImageView.loadImage(imageUrl: String?) {
        Picasso.get()
            .load(imageUrl)
            .placeholder(R.drawable.ic_cover_place_holder)
            .error(R.drawable.ic_cover_place_holder)
            .into(this)
    }

}
