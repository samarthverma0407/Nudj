package com.tpc.nudj.ui.screen.auth.createAndEditEvent

import android.net.Uri
import com.tpc.nudj.model.enums.ClubCategory

data class ClubFormUiState (
    val logoUri: Uri? = null,
    val clubName: String = "",
    val category: ClubCategory? = null,
    val description: String = "",
    val achievements: List<String> = listOf(""),
    val upcomingEvents: List<String> = listOf(""),
    val pastEvents: List<String> = listOf(""),
    val isCategoryDropdownExpanded: Boolean = false,
)