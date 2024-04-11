package com.mouemen.repos_list_data.repository

import com.example.tracker_data.remote.ReposApi
import com.mouemen.repos_list_domain.model.ReposDataUiState
import com.mouemen.repos_list_domain.reposiory.RepsoRepository
import java.text.SimpleDateFormat
import java.util.TimeZone

class RepsoRepositoryImpl(
    private val api: ReposApi
) : RepsoRepository {


    override suspend fun fetchRepos(url:String): Pair<Result<List<ReposDataUiState>>, String?> {

        return try {
            var reposDto = api.fetchRepos(url)
            reposDto.body()?.let {
                val linkHeader = reposDto.headers()["Link"]

                Pair(Result.success(
                    it.mapNotNull {
                        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
                        val date = dateFormat.parse(it.createdAt)
                        ReposDataUiState(
                            it.name,
                            it.owner.avatarUrl,
                            date,
                            it.stargazersCount
                        )
                    }
                ),
                    parseNextUrl(linkHeader))
            } ?: run {
                Pair(Result.failure(Exception()), null)
            }

        } catch (e: Exception) {
            e.printStackTrace()
            Pair(Result.failure(e), null)
        }
    }

    private fun parseNextUrl(linkHeader: String?): String? {
        linkHeader?.let {
            val links = it.split(", ")
            for (link in links) {
                val parts = link.split("; ")
                if (parts.size == 2) {
                    val url = parts[0].trim().removePrefix("<").removeSuffix(">")
                    val rel = parts[1].trim().removePrefix("rel=\"").removeSuffix("\"")
                    if (rel == "next") {
                        return url
                    }
                }
            }
        }
        return null
    }
}