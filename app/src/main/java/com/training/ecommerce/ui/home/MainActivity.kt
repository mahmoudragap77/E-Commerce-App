package com.training.ecommerce.ui.home

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.AnticipateInterpolator
import androidx.activity.viewModels
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.training.ecommerce.R
import com.training.ecommerce.data.datasource.datastore.UserPreferenceDataSource
import com.training.ecommerce.data.repository.user.UserDataStoreRepositoryImpl
import com.training.ecommerce.data.repository.user.UserPreferenceRepository
import com.training.ecommerce.ui.common.viewmodel.UserViewModel
import com.training.ecommerce.ui.common.viewmodel.UserViewModelFactory
import com.training.ecommerce.ui.login.AuthActivity
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private val userViewModel :UserViewModel by viewModels(){
        UserViewModelFactory(UserDataStoreRepositoryImpl(UserPreferenceDataSource(this)))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        initSplash()
        super.onCreate(savedInstanceState)


        runBlocking {
            delay(3000L)
        val isLoggedIn = userViewModel.isUserLoggedIn().first()
            Log.d(TAG,"onCreate: isLoggedIn: $isLoggedIn")
            if (isLoggedIn){
                setContentView(R.layout.activity_main)
            }else{
                goToAuthActivity()
            }

        }

    }

    private fun goToAuthActivity() {
        val intent =Intent(this,AuthActivity::class.java).apply {
            flags =Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val options =ActivityOptions.makeCustomAnimation(
            this,android.R.anim.fade_in ,android.R.anim.fade_out
        )
        startActivity(intent,options.toBundle())
    }

    private fun initSplash() {
        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.S){
            installSplashScreen()
            // Add a callback that's called when the splash screen is animating to the
            // app content.
            splashScreen.setOnExitAnimationListener { splashScreenView ->
                // Create your custom animation.
                val slideUp = ObjectAnimator.ofFloat(
                    splashScreenView,
                    View.TRANSLATION_Y,
                    0f,
                    -splashScreenView.height.toFloat()
                )
                slideUp.interpolator = AnticipateInterpolator()
                slideUp.duration = 200L

                // Call SplashScreenView.remove at the end of your custom animation.
                slideUp.doOnEnd { splashScreenView.remove() }

                // Run your animation.
                slideUp.start()
            }
        }else
        {
            setTheme(R.style.Theme_ECommerce)
        }
    }

    companion object{
        const val TAG ="MAIN_ACTIVITY"
    }
}