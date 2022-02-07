package com.example.samplemvi.utility

import android.app.Activity
import android.content.Context
import com.example.samplemvi.MainActivity
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [SharedPreferenceModule::class])
interface SharedPreferenceComponent {
    fun inject(activity: MainActivity)

    companion object {
        fun init(activity: MainActivity) {
            val sharedPref =  DaggerSharedPreferenceComponent
                .builder()
                .sharedPreferenceModule(SharedPreferenceModule(activity))
                .build()

            sharedPref.inject(activity)
        }
    }
}