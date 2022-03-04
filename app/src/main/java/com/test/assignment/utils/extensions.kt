package com.test.assignment.utils

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.test.assignment.R

fun ImageView.loadImage(
    resource: Any?,
    placeholder: Int = R.drawable.ic_launcher_foreground,
    requestOptions: RequestOptions? = null,
    onSuccess: () -> Unit = {},
    onFailed: () -> Unit = {}
) {
    if (isValidContextForGlide(context)) {
        val reqOptions = Glide.with(context)
            .load(resource)
            .placeholder(placeholder)

        requestOptions?.let {
            reqOptions.apply(requestOptions)
        }

        reqOptions.addListener(getGlideRequestListener(onSuccess, onFailed)).into(this)
    }
}

private fun getGlideRequestListener(
    onSuccess: () -> Unit = {},
    onFailed: () -> Unit = {}
): RequestListener<Drawable> {
    return object : RequestListener<Drawable> {
        override fun onLoadFailed(
            e: GlideException?,
            model: Any?,
            target: Target<Drawable>?,
            isFirstResource: Boolean
        ): Boolean {
            onFailed()
            return false
        }

        override fun onResourceReady(
            resource: Drawable?,
            model: Any?,
            target: Target<Drawable>?,
            dataSource: DataSource?,
            isFirstResource: Boolean
        ): Boolean {
            onSuccess()
            return false
        }
    }
}

private fun isValidContextForGlide(context: Context?): Boolean {
    if (context is Activity)
        return !context.isDestroyed && !context.isFinishing

    return true
}