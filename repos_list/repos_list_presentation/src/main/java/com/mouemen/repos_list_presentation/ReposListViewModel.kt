package com.plcoding.tracker_presentation.search

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moemen.core.util.UiEvent
import com.mouemen.repos_list_domain.model.ReposDataUiState
import com.mouemen.repos_list_domain.use_case.FetchReposUseCase

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ReposListViewModel @Inject constructor(
    var fetchReposUseCase: FetchReposUseCase
) : ViewModel() {

    var state by mutableStateOf(ReposScreenState())
        private set

    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()


    private val _reposList = MutableStateFlow(emptyList<ReposDataUiState>())
    val reposList = query.combine(_reposList) { text, reposList ->
        if (text.isBlank()) {
            state=state.copy(isSearching = false)
            reposList
        } else {
            reposList.filter {
                state=state.copy(isSearching = true)
                it.doesMatchSearchQuery(text)
            }
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        _reposList.value
    )

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        viewModelScope.launch {
            loadRepos()
        }

    }

    fun onEvent(event: ReposScreenEvent) {
        when (event) {

            is ReposScreenEvent.OnQueryChange -> {
                _query.value = event.value
            }

            is ReposScreenEvent.OnSearchFocusChange -> {
                state = state.copy(
                    isHintVisible = !event.isFocused && _query.value.isBlank()
                )
            }

            is ReposScreenEvent.LoadMore -> {
                viewModelScope.launch { loadMore() }

            }

            else -> {}
        }
    }

    private suspend fun loadMore() {
        loadRepos()
    }

    private suspend fun loadRepos() {
        state = state.copy(isLoading = true)
        val response = fetchReposUseCase.invoke(state.nextUrl)
        response.first.onSuccess {
            _reposList.value = reposList.value + it
            val nextUrl = response.second
            state = if (nextUrl.isNullOrEmpty()) {
                state.copy(endReached = true)
            } else {
                state.copy(nextUrl = nextUrl)

            }
        }.onFailure {
            Log.e("myTag", "failure")
        }
        state = state.copy(isLoading = false)

    }
}

