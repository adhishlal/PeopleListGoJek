package com.gopay.dependencies

import com.gopay.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun providesMainActivity(): MainActivity

}