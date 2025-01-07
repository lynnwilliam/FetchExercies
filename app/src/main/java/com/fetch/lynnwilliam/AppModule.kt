package com.fetch.lynnwilliam

import com.fetch.lynnwilliam.data.FetchRecordsUseCase
import com.fetch.lynnwilliam.webapi.FetchAPICall
import com.fetch.lynnwilliam.webapi.RecordsRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.Provides

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFetchRecordsUseCase(recordsRepository: RecordsRepository): FetchRecordsUseCase {
        return FetchRecordsUseCase(recordsRepository)
    }

    @Provides
    fun provideRecordsRepository(): RecordsRepository {
        return FetchAPICall()
    }
}
