package com.moemen.tracker_domain.di

import com.mouemen.repos_list_domain.reposiory.RepsoRepository
import com.mouemen.repos_list_domain.use_case.FetchReposUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object TrackerDomainModule {

    @ViewModelScoped
    @Provides
    fun provideFetchReposUseCase(
        reposRepository: RepsoRepository
    ): FetchReposUseCase {
        return FetchReposUseCase(reposRepository)
    }
}