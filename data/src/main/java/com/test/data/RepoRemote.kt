package com.test.data

import com.test.data.model.ReposResponseData


interface RepoRemote {

    suspend fun search(username:String):List<ReposResponseData>
}