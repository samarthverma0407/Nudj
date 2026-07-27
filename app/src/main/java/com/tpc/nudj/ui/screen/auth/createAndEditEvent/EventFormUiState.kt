package com.tpc.nudj.ui.screen.auth.createAndEditEvent

import android.net.Uri

data class EventFormUiState(
    val logoUri: Uri? = null,
    val bannerUri: Uri? = null,
    val eventName: String = "",
    val eventDate: String = "",
    val eventTime: String = "",
    val venue: String = "",
    val description: String = "",
    val pastEvents: List<String> = listOf(""),
    val photoUris: List<Uri?> = List(3) { null },
    val selectedYear: Int? = null,
    val isYearDropdownExpanded: Boolean = false
)