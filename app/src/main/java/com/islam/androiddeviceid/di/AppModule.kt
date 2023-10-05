package com.islam.androiddeviceid.di

import android.content.Context
import com.islam.androiddeviceid.data.DeviceInfoModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesDeviceInfoModel(@ApplicationContext context: Context) =
        DeviceInfoModule(context = context)

}