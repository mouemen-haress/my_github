package com.mouemen.repos_list_presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mouemen.core.util.DateUtil
import com.mouemen.repos_list_domain.model.ReposDataUiState
import com.mouemen.repos_list_presentation.components.RepoCell
import com.plcoding.tracker_presentation.search.ReposListViewModel
import com.plcoding.tracker_presentation.search.ReposScreenEvent
import com.plcoding.tracker_presentation.search.components.SearchTextField
import java.time.LocalDate

@ExperimentalComposeUiApi
@Composable
fun ReposListScreen(
    viewModel: ReposListViewModel = hiltViewModel(),
    onItemClicked: (data: ReposDataUiState) -> Unit,

    ) {
    val state = viewModel.state
    val reposList by viewModel.reposList.collectAsState()
    val query by viewModel.query.collectAsState()


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SearchTextField(
            text = query,
            onValueChange = {
                viewModel.onEvent(ReposScreenEvent.OnQueryChange(it))
            },
            onSearch = {

            },
            onFocusChanged = {
                viewModel.onEvent(ReposScreenEvent.OnSearchFocusChange(it.isFocused))

            },
            shouldShowHint = state.isHintVisible

        )
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(reposList.size) { i ->
                if (i >= reposList.size - 1 && !state.isLoading && !state.endReached && !state.isSearching) {
                    viewModel.onEvent(ReposScreenEvent.LoadMore())
                }
                var item = reposList.get(i)
                RepoCell(
                    title = item.name ?: "",
                    subtitle = DateUtil.formatDate(item.creationDate) ?: "",
                    onClick = {
                        onItemClicked(item)
                    },
                    avatar = item.avatar ?: ""
                )
            }

            item {
                if (state.isLoading && !state.isSearching) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
            }
        }

    }

//    Box(
//        modifier = Modifier.fillMaxSize(),
//        contentAlignment = Alignment.Center
//    ) {
//        when {
//            state.isLoading -> CircularProgressIndicator()
//        }
//    }
}