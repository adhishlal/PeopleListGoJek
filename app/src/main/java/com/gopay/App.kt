package com.gopay

import android.app.Activity
import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.gopay.dependencies.AppModule
import com.gopay.dependencies.CoreComponent
import com.gopay.dependencies.DaggerCoreComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingInjector: DispatchingAndroidInjector<Activity>

    companion object {
        lateinit var appComponent: CoreComponent

        fun providesAppComponent() = appComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerCoreComponent.builder()
            .appModule(AppModule(this))
            .build()

        appComponent.inject(this)

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    private val activityInjector = AndroidInjector<Activity> { instance ->
        if (activityDispatchingInjector.maybeInject(instance)) {
            return@AndroidInjector
        }

        throw IllegalStateException("Injector not found for $instance")
    }

    override fun activityInjector() = activityInjector
}