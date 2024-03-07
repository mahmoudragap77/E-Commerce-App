package com.training.ecommerce.ui.common.viewmodel

import android.app.assist.AssistStructure.ViewNode
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.training.ecommerce.data.repository.user.UserDataStoreRepositoryImpl
import com.training.ecommerce.data.repository.user.UserPreferenceRepository
import kotlinx.coroutines.launch

class UserViewModel(
    private val userPreferenceRepository: UserDataStoreRepositoryImpl,
) : ViewModel() {

    suspend fun isUserLoggedIn() = userPreferenceRepository.isUserLoggedIn()


}

class UserViewModelFactory(private val userPreferenceRepository: UserDataStoreRepositoryImpl) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return UserViewModel(userPreferenceRepository) as T
        }
        throw IllegalArgumentException("UnKnown ViewModel Class")

    }
}