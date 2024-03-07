package com.training.ecommerce.ui.login.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.training.ecommerce.data.repository.user.UserPreferenceRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    val userPrefs :UserPreferenceRepository
) :ViewModel() {

        fun saveLoginState(isLogged:Boolean){
            viewModelScope.launch {

            }
        }
}