package com.udacity.project4

import android.app.Application
import androidx.annotation.VisibleForTesting
import com.udacity.project4.locationreminders.data.DefaultRemindersRepository
import com.udacity.project4.locationreminders.data.ReminderDataSource
import com.udacity.project4.locationreminders.data.RemindersRepository
import com.udacity.project4.locationreminders.data.source.local.LocalDB
import com.udacity.project4.locationreminders.data.source.local.LocalDataSource
import com.udacity.project4.locationreminders.reminderslist.RemindersListViewModel
import com.udacity.project4.locationreminders.savereminder.SaveReminderViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        /**
         * use Koin Library as a service locator
         */
        @VisibleForTesting
        val myModule = module {
            //Declare a ViewModel - be later inject into Fragment with dedicated injector using by viewModel()
            viewModel {
                RemindersListViewModel(
                    get(),
                    get() as RemindersRepository
                )
            }
            //Declare singleton definitions to be later injected using by inject()
            single {
                //This view model is declared singleton to be used across multiple fragments
                SaveReminderViewModel(
                    get(),
                    get() as RemindersRepository
                )
            }
            single<RemindersRepository>{ get() as DefaultRemindersRepository }
            single { DefaultRemindersRepository(get()) }

            single<ReminderDataSource>{ get() as LocalDataSource }
            single { LocalDataSource(get()) }

            single { LocalDB.createRemindersDao(this@MyApp) }
        }

        startKoin {
            androidContext(this@MyApp)
            modules(listOf(myModule))
        }
    }
}