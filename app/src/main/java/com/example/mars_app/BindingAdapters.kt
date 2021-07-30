package com.example.mars_app

import android.view.View
import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mars_app.network.MarsProperty
import com.example.mars_app.overview.MarsApiStatus


@BindingAdapter("loadImageUrl")
    fun loadingImage(imageView: ImageView, url: String?) {
        url?.let {
            val uri = it.toUri().buildUpon().scheme("https").build()
            Glide.with(imageView.context).load(uri).apply (RequestOptions().placeholder(R.drawable.loading_animation).
            error(R.drawable.ic_baseline_broken_image_24))
                . into(imageView)
        }
    }

@BindingAdapter("listData")
  fun bindRecyclerview(recyclerView: RecyclerView, data : ArrayList<MarsProperty>?){
      val adapter=recyclerView.adapter as PhotoGridAdapter
    if (data != null) {
        adapter.updateProperties(data)
    }
  }

@BindingAdapter("marsApiStatus")
fun bindStatus(statusImageView: ImageView,status: MarsApiStatus?){
  when (status){
      MarsApiStatus.LOADING -> {
          statusImageView.visibility= View.VISIBLE
          statusImageView.setImageResource(R.drawable.loading_animation)
      }
      MarsApiStatus.ERROR -> {
          statusImageView.visibility= View.VISIBLE
          statusImageView.setImageResource(R.drawable.ic_signal_wifi_off)
      }
      MarsApiStatus.DONE -> {
          statusImageView.visibility= View.GONE
      }

  }
}
