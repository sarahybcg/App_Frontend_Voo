package com.voo.bustracker.voo_app_frontend.viewmodel

//import com.voo.bustracker.voo_app_frontend.model.repository.DriverRepository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.voo.bustracker.voo_app_frontend.model.repository.UserRepository

class DriverViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DriverViewModel::class.java)) {
            return DriverViewModel(userRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


//class PassengerViewModelFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(DriverViewModel::class.java)) {
//            return PassengerViewModel(userRepository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
