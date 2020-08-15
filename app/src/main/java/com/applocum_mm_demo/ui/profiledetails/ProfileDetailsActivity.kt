package com.applocum_mm_demo.ui.profiledetails

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.applocum_mm_demo.R
import com.applocum_mm_demo.utils.Applog
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_profile_details.*

class ProfileDetailsActivity : AppCompatActivity() {


    lateinit var mContext: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_details)

        mContext = ProfileDetailsActivity@ this


        var title = intent.getStringExtra("title")
        var url = intent.getStringExtra("url")


        Applog.E("url-=>" + url)
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)

        Glide.with(this).load(url).apply(options).into(imgVwProfilePicAPD)


    }

    fun detailsBackClick(view: View) {
        finish()
    }


}
