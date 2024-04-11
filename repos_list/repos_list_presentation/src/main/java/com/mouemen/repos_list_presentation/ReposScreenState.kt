package com.plcoding.tracker_presentation.search

import com.mouemen.repos_list_domain.model.ReposDataUiState


data class ReposScreenState(
    val isHintVisible: Boolean = false,
    val isLoading: Boolean = false,
    val endReached: Boolean = false,
    val isSearching: Boolean = false,
    val nextUrl: String = "https://api.github.com/orgs/google/repos"

)