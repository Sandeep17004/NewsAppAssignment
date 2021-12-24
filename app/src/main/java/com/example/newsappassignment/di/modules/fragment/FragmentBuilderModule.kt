package com.example.newsappassignment.di.modules.fragment

import com.example.newsappassignment.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {
    @ContributesAndroidInjector
     abstract fun contributeMainFragment(): MainFragment
}