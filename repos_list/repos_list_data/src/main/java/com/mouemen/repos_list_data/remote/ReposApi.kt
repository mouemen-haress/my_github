package com.example.tracker_data.remote

import com.mouemen.repos_list_data.remote.dto.ReposDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ReposApi {
    @GET
    suspend fun fetchRepos(@Url url: String): Response<List<ReposDto>>

}