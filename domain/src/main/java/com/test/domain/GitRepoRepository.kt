package com.test.domain

interface GitRepoRepository {

    suspend fun searchRepo(username:String) : List<ReposResponseModel>
}