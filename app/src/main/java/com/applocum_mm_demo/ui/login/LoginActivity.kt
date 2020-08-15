package com.applocum_mm_demo.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import com.applocum_mm_demo.R
import com.applocum_mm_demo.ui.main.HomeActivity
import com.applocum_mm_demo.utils.ConnectivityDetector
import com.applocum_mm_demo.utils.GlobalMethods
import com.applocum_mm_demo.utils.KeyboardUtility
import com.applocum_mm_demo.utils.SessionManager.SessionManager
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    lateinit var mContext: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mContext = LoginActivity@ this

        imgPassWrdShHdALWE.setImageDrawable(resources.getDrawable(R.drawable.ic_pw_dont_show_blk))
    }

    var isCheckPassword: Boolean = false
    fun passwordOnOffClick(view: View) {
        if (isCheckPassword == false) {
            isCheckPassword = true
            imgPassWrdShHdALWE.setImageDrawable(resources.getDrawable(R.drawable.ic_pw_show_blk))
            edtTvPassWrdALWE.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            isCheckPassword = false
            imgPassWrdShHdALWE.setImageDrawable(resources.getDrawable(R.drawable.ic_pw_dont_show_blk))
            edtTvPassWrdALWE.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
        }

    }

    fun loginClick(view: View) {
        if (isValidation()) {
            if (ConnectivityDetector.isConnectingToInternet(mContext)) {
                SessionManager.setIsUserLoggedin(mContext,true)
                var intent = Intent(mContext, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            } else {
                GlobalMethods.showInternetAlert(mContext)
            }

        }
    }

    var email: String? = ""
    var password: String? = ""

    private fun isValidation(): Boolean {

        email = edtTvEmailALWE.text.toString()
        password = edtTvPassWrdALWE.text.toString()

        if (email.equals("", ignoreCase = true)) {
            GlobalMethods.showToast(mContext, resources.getString(R.string.email_validation))
            edtTvEmailALWE.requestFocus()
            KeyboardUtility.hideKeyboard(mContext, edtTvEmailALWE)
            return false
        } else if (!GlobalMethods.isEmailValid(email!!)) {
            GlobalMethods.showToast(mContext, resources.getString(R.string.valid_email_validation))
            edtTvEmailALWE.requestFocus();
            KeyboardUtility.hideKeyboard(mContext, edtTvEmailALWE)
            return false
        } else if (password.equals("", ignoreCase = true)) {
            GlobalMethods.showToast(mContext, resources.getString(R.string.password_validation))
            edtTvPassWrdALWE.requestFocus();
            KeyboardUtility.hideKeyboard(mContext, edtTvPassWrdALWE)
            return false
        } else if (password!!.length < 6 || password!!.length > 16) {
            GlobalMethods.showToast(
                mContext,
                resources.getString(R.string.password_length_validation)
            )
            edtTvPassWrdALWE.requestFocus();
            KeyboardUtility.hideKeyboard(mContext, edtTvPassWrdALWE)
            return false
        } else if (!email.equals("hello@yopmail.com") && !password.equals(
                "Password@123",
                ignoreCase = true
            )
        ) {
            GlobalMethods.showToast(mContext, resources.getString(R.string.valid_email_password))
            edtTvEmailALWE.requestFocus();
            KeyboardUtility.hideKeyboard(mContext, edtTvEmailALWE)
            return false
        }
        return true
    }
}
