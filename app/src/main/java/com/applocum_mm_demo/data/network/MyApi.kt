package com.applocum_mm_demo.network


import com.applocum_mm_demo.data.network.WebFields
import com.applocum_mm_demo.data.responce.HomeResponse
import com.applocum_mm_demo.data.responce.HomeResponseItem
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface MyApi {

    //Todo: Common Used... Start.............................>>>

    @GET(WebFields.GET_PHOTOS_PARAM)
    suspend fun callHomeDetailsApiExecuted(): Response<ArrayList<HomeResponseItem>>

    companion object {
        operator fun invoke(
            networkConnectionInterceptor: NetworkConnectionInterceptor
        ): MyApi {

            val okHttpClient = OkHttpClient.Builder().addInterceptor(networkConnectionInterceptor)
                .build() // Used to check internet connections.

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(WebFields.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }
}