package com.mouemen.repos_list_domain.reposiory

import com.mouemen.repos_list_domain.model.ReposDataUiState
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface RepsoRepository {
    suspend fun fetchRepos(url:String):Pair<Result<List<ReposDataUiState>>,String?>
}