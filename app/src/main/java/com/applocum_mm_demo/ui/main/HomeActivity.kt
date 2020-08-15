package com.applocum_mm_demo.ui.main

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.applocum_mm_demo.R
import com.applocum_mm_demo.data.responce.HomeResponseItem
import com.applocum_mm_demo.ui.home.adpt.HomeDetailsAdapter
import com.applocum_mm_demo.ui.login.LoginActivity
import com.applocum_mm_demo.ui.profiledetails.ProfileDetailsActivity
import com.applocum_mm_demo.utils.ConnectivityDetector
import com.applocum_mm_demo.utils.GlobalMethods
import com.applocum_mm_demo.utils.MyProgressDialog
import com.applocum_mm_demo.utils.SessionManager.SessionManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_home.*
import org.json.JSONArray
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class HomeActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private lateinit var viewModel: HomeViewModel
    private val factory: HomeViewModelFactory by instance()

    lateinit var mContext: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mContext = MainActivity@ this
        viewModel = ViewModelProviders.of(this, factory).get(HomeViewModel::class.java)

        if (ConnectivityDetector.isConnectingToInternet(mContext)) {
            callGetUserDataAPI()
        } else {
            GlobalMethods.showInternetAlert(mContext)
        }

    }

    lateinit var alUserDetails: ArrayList<HomeResponseItem>
    private fun callGetUserDataAPI() {
        MyProgressDialog.showProgressDialog(mContext)
        viewModel.callHomeDetailsAPI(mContext!!)
            .observe(this, Observer {

                try {
                    //Todo: After Api call Success get data as per Return Values means Model class or any other
                    if (it != null) {
                        MyProgressDialog.hideProgressDialog()
                        alUserDetails = ArrayList()
                        alUserDetails.addAll(it)


                        setHomeData(alUserDetails)

                    } else {
                        MyProgressDialog.hideProgressDialog()
                    }
                } catch (e: Exception) {
                    MyProgressDialog.hideProgressDialog()
                }

            })
    }

    public fun logoutClick(view: View) {
        showLogoutDialog(mContext!!.resources.getString(R.string.logout_message))
    }


    private fun setHomeData(alUserDetails: java.util.ArrayList<HomeResponseItem>) {
        if (alUserDetails.size > 0) {

            var adapter = HomeDetailsAdapter(mContext, alUserDetails,
                object : HomeDetailsAdapter.BtnClickListener {
                    override fun onTopHomeBtnClick(position: Int) {
                        var intent = Intent(mContext, ProfileDetailsActivity::class.java)
                        intent.putExtra("url",alUserDetails.get(position).url)
                        intent.putExtra("title",alUserDetails.get(position).title)
                        startActivity(intent)
                    }
                })
            val linearLayoutManager = LinearLayoutManager(mContext)
            linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
            rcVwHomeData!!.layoutManager = linearLayoutManager

            rcVwHomeData!!.isNestedScrollingEnabled = true
            rcVwHomeData!!.setHasFixedSize(true)
            rcVwHomeData!!.setAdapter(adapter)
        } else {

        }
    }


    private fun showLogoutDialog(message: String) {
        try {
            val builder = AlertDialog.Builder(mContext!!)
            builder.setCancelable(false)
            builder.setTitle(resources.getString(R.string.app_name))
            builder.setMessage(message)
            builder.setPositiveButton(
                getString(R.string.logout)
            ) { dialog, which ->

                SessionManager.clearAppSession(mContext!!)

                val intent = Intent(mContext, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)

                dialog.dismiss()


            }
            builder.setNegativeButton(
                getString(R.string.cancel),
                object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface, which: Int) {
                        dialog.dismiss()
                    }
                })
            val dialog = builder.create()
            dialog.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
