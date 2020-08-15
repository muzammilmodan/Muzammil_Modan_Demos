package com.applocum_mm_demo.ui.home.adpt

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.applocum_mm_demo.R
import com.applocum_mm_demo.data.responce.HomeResponseItem
import com.applocum_mm_demo.utils.Applog
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.makeramen.roundedimageview.RoundedTransformationBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.raw_home_details.view.*


class HomeDetailsAdapter(
    var context: Context,
    var alTopRatedExpList: ArrayList<HomeResponseItem>,
    var btnlistener: BtnClickListener
) :
    RecyclerView.Adapter<HomeDetailsAdapter.ViewHolder>() {

    companion object {
        var mClickListener: BtnClickListener? = null
    }

    open interface BtnClickListener {
        fun onTopHomeBtnClick(position: Int)
    }

    var lastSelectedPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.raw_home_details, parent, false)
        return ViewHolder(v)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgVwProfilePicRHD = view.imgVwProfilePicRHD
        val tvUserTitle = view.tvUserTitle
        val rlMain=view.rlMain
    }


    override fun getItemCount(): Int {
        return alTopRatedExpList.size
    }


    override fun onBindViewHolder(viewholder: ViewHolder, pos: Int) {
        try {
            val itemList = alTopRatedExpList!!.get(pos)
            viewholder.tvUserTitle.setText(itemList.title)

            Applog.E("url-=>"+itemList.thumbnailUrl)

            val options: RequestOptions = RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.mipmap.ic_launcher_round)

            Glide.with(context).load(itemList.thumbnailUrl).apply(options).into(viewholder.imgVwProfilePicRHD)




            mClickListener = btnlistener
            viewholder.rlMain.setOnClickListener {
                if (mClickListener != null) {
                    mClickListener?.onTopHomeBtnClick(pos)
                    lastSelectedPosition = pos
                    notifyDataSetChanged()
                }
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}