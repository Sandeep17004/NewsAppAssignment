package com.example.newsappassignment.di.modules.activity

import com.example.newsappassignment.MainActivity
import com.example.newsappassignment.di.modules.fragment.FragmentBuilderModule
import com.example.newsappassignment.di.modules.main.MainModule
import com.example.newsappassignment.di.modules.main.ViewModelFactoryModule
import com.example.newsappassignment.di.modules.main.ViewModelModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityBuildersModule {
    @ContributesAndroidInjector(
        modules = [MainModule::class, FragmentBuilderModule::class, ViewModelFactoryModule::class, ViewModelModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity
}