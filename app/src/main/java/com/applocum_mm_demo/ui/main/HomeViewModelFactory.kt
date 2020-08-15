package com.applocum_mm_demo.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.applocum_mm_demo.data.repository.HomeRepository
import java.lang.IllegalArgumentException

class HomeViewModelFactory(private val homeRepository : HomeRepository)
    : ViewModelProvider.NewInstanceFactory() {

    //Todo: Using InstanceFactory as per using ViewModel Crete
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(homeRepository) as T
        }
        throw IllegalArgumentException("HomeViewModelFactory exception")
    }

}