package com.example.newsappassignment.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.api.load
import com.example.newsappassignment.R


class BindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("articleImage")
        fun loadNewsArticleImage(
            view: ImageView,
            imageUrl: String?
        ) {
            imageUrl?.let {
                view.load(it) {
                    placeholder(R.drawable.placeholder_image)
                    error(R.drawable.placeholder_image)
                }
            }

        }
    }
}