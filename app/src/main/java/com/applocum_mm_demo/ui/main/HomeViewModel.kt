package com.applocum_mm_demo.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.applocum_mm_demo.data.repository.HomeRepository
import com.applocum_mm_demo.data.responce.HomeResponse
import com.applocum_mm_demo.data.responce.HomeResponseItem
import com.applocum_mm_demo.utils.ApiExceptions
import com.applocum_mm_demo.utils.Coroutines
import com.applocum_mm_demo.utils.GlobalMethods
import com.applocum_mm_demo.utils.NoInternetException
import okhttp3.ResponseBody

//Todo: implement View Model it is compulsory as per ViewModel class Life cycle
class HomeViewModel(private val userRepository: HomeRepository) : ViewModel() {

    var authData: MutableLiveData<ArrayList<HomeResponseItem>>? = null
    var apiResponse:ArrayList<HomeResponseItem>? = null

    //Todo: Get activity thru data Send request param in api using Live data.
    // MutableLiveData using stored data in view
    fun callHomeDetailsAPI(context: Context): LiveData<ArrayList<HomeResponseItem>> {

        authData = MutableLiveData<ArrayList<HomeResponseItem>>()

        Coroutines.main {
            try {
                apiResponse = userRepository.callHomeDetailsApiExecuted()
                authData!!.value = apiResponse

            } catch (e: ApiExceptions) {
                  authData!!.value =null
                /*
                TastyToast.makeText(context, e.message, TastyToast.LENGTH_LONG,
                    TastyToast.ERROR)*/
            }
        }
        return authData!!
    }
}