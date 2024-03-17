package com.training.ecommerce.ui.login.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.training.ecommerce.R
import com.training.ecommerce.data.datasource.datastore.UserPreferenceDataSource
import com.training.ecommerce.data.repository.user.UserDataStoreRepositoryImpl
import com.training.ecommerce.databinding.FragmentLoginBinding
import com.training.ecommerce.ui.login.viewmodel.LoginViewModel


class LoginFragment : Fragment() {

    val loginViewModel: LoginViewModel by lazy {
        LoginViewModel(userPrefs = UserDataStoreRepositoryImpl(UserPreferenceDataSource( requireActivity())))
    }

    lateinit var binding: FragmentLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {

        private const val TAG = "LoginFragment"
    }
}