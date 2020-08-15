package com.applocum_mm_demo.data.repository

import com.applocum_mm_demo.data.network.SafeAPIRequest
import com.applocum_mm_demo.data.responce.HomeResponse
import com.applocum_mm_demo.data.responce.HomeResponseItem
import com.applocum_mm_demo.network.MyApi
import okhttp3.ResponseBody

class HomeRepository(private val api: MyApi): SafeAPIRequest() {


    suspend fun callHomeDetailsApiExecuted(): ArrayList<HomeResponseItem> {
        return apiRequest { api.callHomeDetailsApiExecuted() }
    }



}