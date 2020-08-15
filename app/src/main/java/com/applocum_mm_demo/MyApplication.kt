package com.applocum_mm_demo

import android.app.Application
import com.applocum_mm_demo.data.repository.HomeRepository
import com.applocum_mm_demo.network.MyApi
import com.applocum_mm_demo.network.NetworkConnectionInterceptor
import com.applocum_mm_demo.ui.main.HomeViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MyApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {

        import(androidXModule(this@MyApplication))

        //Comman used
        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }

        //Home screen Injection
        bind() from singleton { HomeRepository(instance()) }
        bind() from provider { HomeViewModelFactory(instance()) }



    }

}