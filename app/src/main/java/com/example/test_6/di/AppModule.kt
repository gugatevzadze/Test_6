package com.example.test_6.di

import com.example.test_6.data.PasscodeRepositoryImpl
import com.example.test_6.domain.PasscodeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePasscodeRepository(): PasscodeRepository {
        return PasscodeRepositoryImpl()
    }
}