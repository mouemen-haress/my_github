package com.plcoding.tracker_presentation.search


import java.time.LocalDate

sealed class ReposScreenEvent {
    data class OnQueryChange(var value: String) : ReposScreenEvent()
    data class OnSearchFocusChange(var isFocused: Boolean) : ReposScreenEvent()
    class LoadMore : ReposScreenEvent()

    object OnStartReposFetching : ReposScreenEvent()

}
