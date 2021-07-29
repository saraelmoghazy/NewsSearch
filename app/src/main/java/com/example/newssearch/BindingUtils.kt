package com.example.newssearch


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso


/**
 * Created by Sara Elmoghazy
 */
object BindingUtils {

    @BindingAdapter(value = ["image"], requireAll = false)
    @JvmStatic
    fun ImageView.loadImage(imageUrl: String?) {
        Picasso.get()
            .load(imageUrl)
            .placeholder(R.drawable.ic_cover_place_holder)
            .error(R.drawable.ic_cover_place_holder)
            .resize(2048, 1600)
            .onlyScaleDown()
            .into(this)
    }

}
