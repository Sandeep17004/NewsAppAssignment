package com.example.newsappassignment.di

import com.example.newsappassignment.MainApplication
import com.example.newsappassignment.di.modules.activity.MainActivityBuildersModule
import com.example.newsappassignment.di.modules.main.AppModule
import com.example.newsappassignment.di.modules.main.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton


@Singleton
@Component(
    modules = [AndroidInjectionModule::class, AppModule::class, ViewModelFactoryModule::class, MainActivityBuildersModule::class]
)

interface AppComponent : AndroidInjector<MainApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(mainApplication: MainApplication): Builder
        fun build(): AppComponent
    }

    override fun inject(instance: MainApplication)
}