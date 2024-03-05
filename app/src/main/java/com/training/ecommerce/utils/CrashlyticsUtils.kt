package com.training.ecommerce.utils

import com.google.firebase.crashlytics.FirebaseCrashlytics

object CrashlyticsUtils {

    /**
     * End point key
     */
    const val CUSTOM_ENDPOINT_KEY="CUSTOM_ENDPOINT_KEY"
    /**
     * random case key
     */
    const val CUSTOM_KEY="CUSTOM_KEY"

    fun sendLogToCrashlytics(msg:String ,vararg keys :String){
        keys.forEach {key ->
            FirebaseCrashlytics.getInstance().setCustomKey(key , msg)
        }
        FirebaseCrashlytics.getInstance().recordException(CustomCrashlyticsLogException(msg))
    }
    fun sendLogToCrashlytics(msg:String ,vararg keys :Pair<String,String>){
        keys.forEach {key ->
            FirebaseCrashlytics.getInstance().setCustomKey(key.first ,key.second)
        }
        FirebaseCrashlytics.getInstance().recordException(CustomCrashlyticsLogException(msg))
    }

    inline fun < reified T : Exception> sendCustomLogToCrashlytics( msg: String , vararg keys :Pair<String,String>){
        keys.forEach {
            key->
            FirebaseCrashlytics.getInstance().setCustomKey(key.first ,key.second)
        }
        val exception = T::class.java.getConstructor(String::class.java).newInstance(msg)
        FirebaseCrashlytics.getInstance().recordException(exception)
    }
}

class CustomCrashlyticsLogException(message: String) : Exception(message)

