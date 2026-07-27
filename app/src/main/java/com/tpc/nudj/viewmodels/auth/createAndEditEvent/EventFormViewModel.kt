package com.tpc.nudj.viewmodels.auth.createAndEditEvent

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.tpc.nudj.ui.screen.auth.createAndEditEvent.EventFormUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class EventFormViewModel @Inject constructor() : ViewModel() {
    private val _eventFormUiState = MutableStateFlow(EventFormUiState())
    val eventFormUiState = _eventFormUiState.asStateFlow()

    fun onLogoSelected(uri: Uri?) {
        _eventFormUiState.update {
            it.copy(logoUri = uri)
        }
    }

    fun onBannerSelected(uri: Uri?) {
        _eventFormUiState.update {
            it.copy(bannerUri = uri)
        }
    }

    fun onEventNameChange(name: String) {
        _eventFormUiState.update {
            it.copy(eventName = name)
        }
    }

    fun onEventDateChange(date: String) {
        _eventFormUiState.update {
            it.copy(eventDate = date)
        }
    }

    fun onEventTimeChange(time: String) {
        _eventFormUiState.update {
            it.copy(eventTime = time)
        }
    }

    fun onVenueChange(venue: String) {
        _eventFormUiState.update {
            it.copy(venue = venue)
        }
    }

    fun onDescriptionChange(description: String) {
        _eventFormUiState.update {
            it.copy(description = description)
        }
    }

    fun updatePastEvent(index: Int, value: String) {
        _eventFormUiState.update {
            val list = it.pastEvents.toMutableList()
            list[index] = value
            it.copy(pastEvents = list)
        }
    }

    fun addPastEvent() {
        _eventFormUiState.update {
            it.copy(
                pastEvents = it.pastEvents + ""
            )
        }
    }

    fun removePastEvent(index: Int) {
        _eventFormUiState.update {
            val list = it.pastEvents.toMutableList()
            if(index in list.indices){
                list.removeAt(index)
            }
            it.copy(pastEvents = list)
        }
    }

    fun onPhotoSelected(index: Int, uri: Uri?) {
        _eventFormUiState.update {
            val photos = it.photoUris.toMutableList()
            photos[index] = uri
            it.copy(photoUris = photos)
        }
    }

    fun onYearSelected(year: Int) {
        _eventFormUiState.update {
            it.copy(selectedYear = year)
        }
    }

    fun onYearDropdownExpandedChange(expanded: Boolean) {
        _eventFormUiState.update {
            it.copy(isYearDropdownExpanded = expanded)
        }
    }
}