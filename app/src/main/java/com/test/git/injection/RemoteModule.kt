package com.test.git.injection

import com.test.data.RepoRemote
import com.test.remote.ApiGit
import com.test.remote.ApiGitImpl
import com.test.remote.repo.RepoRemoteImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent ::class)
class RemoteModule {

    @Singleton
    @Provides
    fun serviceProvider(): ApiGit = ApiGitImpl().getApi()

    @Provides
    fun gitRepoProvider(service:ApiGit):RepoRemote = RepoRemoteImpl(service)


}