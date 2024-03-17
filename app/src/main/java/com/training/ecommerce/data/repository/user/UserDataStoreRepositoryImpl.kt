package com.training.ecommerce.data.repository.user

import android.content.Context
import androidx.datastore.preferences.core.edit
import com.training.ecommerce.data.datasource.datastore.DataStoreKeys.IS_USER_LOGGED_IN
import com.training.ecommerce.data.datasource.datastore.DataStoreKeys.USER_ID
import com.training.ecommerce.data.datasource.datastore.UserPreferenceDataSource
import com.training.ecommerce.data.datasource.datastore.dataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow

class UserDataStoreRepositoryImpl(
    private val userPreferenceDataSource: UserPreferenceDataSource)
    :UserPreferenceRepository {
    //write datastore
    override suspend fun saveLoginState(isLoggedIn: Boolean) {
        userPreferenceDataSource.saveLoginState(isLoggedIn)
    }

    override suspend fun saveUserID(userId: String) {
        userPreferenceDataSource.saveUserID(userId)
    }

    override suspend fun isUserLoggedIn(): Flow<Boolean> {
        return userPreferenceDataSource.isUserLoggedIn
    }

    override fun getUserID(): Flow<String?>{
        return userPreferenceDataSource.userID
    }

}