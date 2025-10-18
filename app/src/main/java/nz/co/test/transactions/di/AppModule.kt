package nz.co.test.transactions.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import nz.co.test.transactions.ui.utils.CurrencyFormatter
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesCurrencyFormatter(@ApplicationContext context: Context) =
        CurrencyFormatter(context.resources.configuration.locales[0])

    @Provides
    @Singleton
    fun providesIoDispatcher() =
        Dispatchers.IO
}