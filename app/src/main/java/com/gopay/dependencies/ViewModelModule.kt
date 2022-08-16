package com.gopay.dependencies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gopay.viewmodels.MainActivityViewModel
import com.gopay.viewmodels.ViewModelFactory
import com.gopay.viewmodels.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun providesViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    abstract fun providesMainActivityViewModel(mainActivityViewModel: MainActivityViewModel): ViewModel

}
