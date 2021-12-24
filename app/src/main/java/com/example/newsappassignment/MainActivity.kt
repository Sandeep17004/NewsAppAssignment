package com.example.newsappassignment

import android.os.Bundle
import com.example.newsappassignment.di.modules.main.ViewModelProvidersFactory
import com.example.newsappassignment.ui.main.MainFragment
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var providerFactory: ViewModelProvidersFactory

    @Inject
    lateinit var mainFragment: MainFragment
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, mainFragment)
                .commitNow()
        }
    }
}