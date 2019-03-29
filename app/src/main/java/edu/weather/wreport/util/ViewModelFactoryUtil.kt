package edu.weather.wreport.util

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ViewModelFactoryUtil {

    fun <T : ViewModel> createFor(viewModel: T): ViewModelProvider.Factory {
        return object : ViewModelProvider.Factory {

            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(viewModel.javaClass)) {
                    return viewModel as T
                }
                throw IllegalArgumentException("unexpected viewModel class " + modelClass)
            }
        }
    }
}