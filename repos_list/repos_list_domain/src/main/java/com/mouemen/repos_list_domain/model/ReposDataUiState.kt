package com.mouemen.repos_list_domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.Date

@Parcelize
data class ReposDataUiState(
    var name: String?, var avatar: String?, var creationDate: Date, var stargazersCount: Int?
): Parcelable {
    fun doesMatchSearchQuery(query: String): Boolean {
        return name?.let {
            it.contains(query)
        } ?: run {
            false
        }
    }
}
