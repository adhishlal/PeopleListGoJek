package com.gopay.dependencies

import android.app.Activity
import android.content.SharedPreferences
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.room.RoomDatabase
import com.google.gson.Gson
import com.gopay.App
import com.gopay.activities.MainActivity
import com.gopay.dispatcher.CoroutineDispatcherProvider
import com.gopay.network.ApiService
import com.gopay.viewmodels.MainActivityViewModel
import com.gopay.viewmodels.ViewModelFactory
import com.squareup.picasso.Picasso
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        NetworkModule::class,
        ViewModelModule::class,
        StorageModule::class,
        ImageModule::class,
        DispatcherModule::class]
)
interface CoreComponent {

    fun inject(app: App)

    fun retrofit(): Retrofit

    fun apiService(): ApiService

    fun gson(): Gson

    fun sharedPreferences(): SharedPreferences

    fun roomDb(): RoomDatabase

    fun picasso(): Picasso

    fun viewModelFactory(): ViewModelFactory

    fun mainActivityViewModel(): MainActivityViewModel

    fun coroutineDispatcher(): CoroutineDispatcherProvider

    fun activityInjector(): DispatchingAndroidInjector<Activity>

    fun fragmentInjector(): DispatchingAndroidInjector<Fragment>
}
