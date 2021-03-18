package com.test.data

import com.test.domain.GitRepoRepository
import com.test.domain.ReposResponseModel

class RepoRepositoryImpl(private val remote: RepoRemote) : GitRepoRepository {
    override suspend fun searchRepo(username: String): List<ReposResponseModel> {
        return remote.search(username).map{
            it.map()
        }
    }
}