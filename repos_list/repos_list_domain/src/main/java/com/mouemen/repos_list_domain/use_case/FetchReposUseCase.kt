package com.mouemen.repos_list_domain.use_case

import com.mouemen.repos_list_domain.model.ReposDataUiState
import com.mouemen.repos_list_domain.reposiory.RepsoRepository
import java.time.LocalDate

class FetchReposUseCase(
    private val repository: RepsoRepository
) {

    suspend operator fun invoke(
        url: String
    ): Pair<Result<List<ReposDataUiState>>, String?> {
        return repository.fetchRepos(url)
    }
}