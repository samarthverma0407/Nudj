package com.tpc.nudj.viewmodels.auth.createAndEditEvent

import android.net.Uri
import androidx.lifecycle.ViewModel
import com.tpc.nudj.model.enums.ClubCategory
import com.tpc.nudj.ui.screen.auth.createAndEditEvent.ClubFormUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ClubFormViewModel @Inject constructor() : ViewModel() {
    private val _clubFormUiState = MutableStateFlow(ClubFormUiState())
    val clubFormUiState: StateFlow<ClubFormUiState> = _clubFormUiState.asStateFlow()

    fun onLogoSelected(uri: Uri?) {
        _clubFormUiState.update {
            it.copy(logoUri = uri)
        }
    }
    fun onClubNameChange(newClubName: String){
        _clubFormUiState.update {
            it.copy(clubName = newClubName)
        }
    }
    fun onCategorySelected(category: ClubCategory) {
        _clubFormUiState.update {
            it.copy(category = category, isCategoryDropdownExpanded = false)
        }
    }
    fun onCategoryDropdownExpandedChange(isExpanded: Boolean) {
        _clubFormUiState.update {
            it.copy(isCategoryDropdownExpanded = isExpanded)
        }
    }
    fun onDescriptionChange(newDescription: String){
        _clubFormUiState.update {
            it.copy(description = newDescription)
        }
    }
    fun updateAchievement(index: Int, value: String) {
        _clubFormUiState.update {
            val list = it.achievements.toMutableList()
            list[index] = value
            it.copy(achievements = list)
        }
    }
    fun addAchievement() {
        _clubFormUiState.update {
            it.copy(
                achievements = it.achievements + ""
            )
        }
    }
    fun removeAchievement(index: Int) {
        _clubFormUiState.update {
            val list = it.achievements.toMutableList()
            if (index in list.indices) {
                list.removeAt(index)
            }
            it.copy(achievements = list)
        }
    }

    fun updateUpcomingEvent(index: Int, value: String) {
        _clubFormUiState.update {
            val list = it.upcomingEvents.toMutableList()
            list[index] = value
            it.copy(upcomingEvents = list)
        }
    }
    fun addUpcomingEvent() {
        _clubFormUiState.update {
            it.copy(
                upcomingEvents = it.upcomingEvents + ""
            )
        }
    }
    fun removeUpcomingEvent(index: Int) {
        _clubFormUiState.update {
            val list = it.upcomingEvents.toMutableList()
            if (index in list.indices) {
                list.removeAt(index)
            }
            it.copy(upcomingEvents = list)
        }
    }

    fun updatePastEvent(index: Int, value: String) {
        _clubFormUiState.update {
            val list = it.pastEvents.toMutableList()
            list[index] = value
            it.copy(pastEvents = list)
        }
    }
    fun addPastEvent() {
        _clubFormUiState.update {
            it.copy(
                pastEvents = it.pastEvents + ""
            )
        }
    }
    fun removePastEvent(index: Int) {
        _clubFormUiState.update {
            val list = it.pastEvents.toMutableList()
            if (index in list.indices) {
                list.removeAt(index)
            }
            it.copy(pastEvents = list)
        }
    }
}