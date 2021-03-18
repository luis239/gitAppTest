package com.test.git.injection

import com.test.data.RepoRemote
import com.test.data.RepoRepositoryImpl
import com.test.domain.GitRepoRepository
import com.test.domain.SearchRepoUseCase
import com.test.remote.repo.RepoRemoteImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun gitReposRepositoryProvider(
        remote: RepoRemote
    ):GitRepoRepository = RepoRepositoryImpl(remote)

    @Provides
    fun gitRepoUseCaseProvider(gitRepoRepository: GitRepoRepository) = SearchRepoUseCase(gitRepoRepository)

}