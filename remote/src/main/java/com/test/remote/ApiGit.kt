package com.test.remote

//import com.test.remote.repo.model.GetReposResponse
import com.test.remote.repo.model.GetReposResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiGit {

    @GET("users/{username}/repos")
    suspend fun search(
        @Path("username")user:String
    ):Response<List<GetReposResponseItem>>
}