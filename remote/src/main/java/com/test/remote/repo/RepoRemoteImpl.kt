package com.test.remote.repo

import com.test.data.RepoRemote
import com.test.data.model.ReposResponseData
import com.test.remote.ApiGit

class RepoRemoteImpl(private val service:ApiGit) : RepoRemote {
    override suspend fun search(username: String): List<ReposResponseData> {
        val resultList = ArrayList<ReposResponseData> ()
        service.search(username).body()!!.map {

                resultList.add( ReposResponseData(it!!.id!!, it.name!!, it.updatedAt!!))
            }

        return resultList.toList()
    }
}