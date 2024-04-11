package com.mouemen.repos_details_presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.moemen.repos_details_presentation.R
import com.mouemen.core.util.DateUtil
import com.mouemen.repos_list_domain.model.ReposDataUiState

@Composable
fun ReposDetailsScreen(data: ReposDataUiState) {

    Column {
        ProfileProperty("${stringResource(id = R.string.name)}:", data.name ?: "")
        ProfileProperty(
            "${stringResource(id = R.string.date)}:",
            DateUtil.formatDate(data.creationDate) ?: ""
        )

        val stargazersCount = data.stargazersCount
        stargazersCount?.let {
            ProfileProperty("${stringResource(id = R.string.stargazers_count)}:", it.toString())
        }
    }
}