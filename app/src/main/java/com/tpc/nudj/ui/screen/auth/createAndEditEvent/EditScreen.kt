package com.tpc.nudj.ui.screen.auth.createAndEditEvent

import java.util.Calendar
import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import android.app.DatePickerDialog
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.tpc.nudj.model.enums.FormType
import com.tpc.nudj.model.enums.ImagePickerType
import com.tpc.nudj.ui.components.NudjTopAppBar
import com.tpc.nudj.ui.layouts.ClubDetailsEditLayout
import com.tpc.nudj.ui.layouts.EventDetailsEditLayout
import com.tpc.nudj.ui.theme.LocalAppColors
import com.tpc.nudj.viewmodels.auth.createAndEditEvent.ClubFormViewModel
import com.tpc.nudj.viewmodels.auth.createAndEditEvent.EventFormViewModel
import java.util.Locale

@Composable
fun EditScreen(
    formType: FormType,
    clubFormViewModel: ClubFormViewModel = hiltViewModel(),
    eventFormViewModel: EventFormViewModel = hiltViewModel()
) {
    val clubUiState by clubFormViewModel.clubFormUiState.collectAsState()
    val eventUiState by eventFormViewModel.eventFormUiState.collectAsState()
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    var currentImagePicker by remember {
        mutableStateOf<ImagePickerType?>(null)
    }
    val imagePickerLauncher =
        rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri ->
            uri ?: return@rememberLauncherForActivityResult
            when (currentImagePicker) {
                ImagePickerType.CLUB_LOGO ->
                    clubFormViewModel.onLogoSelected(uri)
                ImagePickerType.EVENT_LOGO ->
                    eventFormViewModel.onLogoSelected(uri)
                ImagePickerType.EVENT_BANNER ->
                    eventFormViewModel.onBannerSelected(uri)
                ImagePickerType.EVENT_PHOTO_1 ->
                    eventFormViewModel.onPhotoSelected(0, uri)
                ImagePickerType.EVENT_PHOTO_2 ->
                    eventFormViewModel.onPhotoSelected(1, uri)
                ImagePickerType.EVENT_PHOTO_3 ->
                    eventFormViewModel.onPhotoSelected(2, uri)
                null -> {}
            }
        }
    Scaffold(
        containerColor = LocalAppColors.current.background,
        topBar = {
            NudjTopAppBar(
                onBackClick = {}
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (formType) {
                FormType.CLUB -> {
                    ClubDetailsEditLayout(
                        uiState = clubUiState,

                        onClubNameChange = clubFormViewModel::onClubNameChange,
                        onDescriptionChange = clubFormViewModel::onDescriptionChange,

                        onCategorySelected = clubFormViewModel::onCategorySelected,
                        onCategoryDropdownExpandedChange = clubFormViewModel::onCategoryDropdownExpandedChange,

                        updateAchievement = clubFormViewModel::updateAchievement,
                        addAchievement = clubFormViewModel::addAchievement,
                        removeAchievement = clubFormViewModel::removeAchievement,

                        updateUpcomingEvent = clubFormViewModel::updateUpcomingEvent,
                        addUpcomingEvent = clubFormViewModel::addUpcomingEvent,
                        removeUpcomingEvent = clubFormViewModel::removeUpcomingEvent,

                        updatePastEvent = clubFormViewModel::updatePastEvent,
                        addPastEvent = clubFormViewModel::addPastEvent,
                        removePastEvent = clubFormViewModel::removePastEvent,

                        onLogoSelected = {
                            currentImagePicker = ImagePickerType.CLUB_LOGO
                            imagePickerLauncher.launch("image/*")
                        },

                        onCreateClick = {

                        }
                    )
                }

                FormType.GENERAL_EVENT -> {
                    EventDetailsEditLayout(
                        uiState = eventUiState,

                        onLogoClick = {
                            currentImagePicker = ImagePickerType.EVENT_LOGO
                            imagePickerLauncher.launch("image/*")
                        },

                        onBannerClick = {
                            currentImagePicker = ImagePickerType.EVENT_BANNER
                            imagePickerLauncher.launch("image/*")
                        },

                        onPhotoClick = { index ->
                            currentImagePicker = when (index) {
                                0 -> ImagePickerType.EVENT_PHOTO_1
                                1 -> ImagePickerType.EVENT_PHOTO_2
                                else -> ImagePickerType.EVENT_PHOTO_3
                            }
                            imagePickerLauncher.launch("image/*")
                        },

                        onEventNameChange = eventFormViewModel::onEventNameChange,
                        onVenueChange = eventFormViewModel::onVenueChange,
                        onDescriptionChange = eventFormViewModel::onDescriptionChange,

                        onDateClick = {
                            DatePickerDialog(
                                context,
                                { _, year, month, dayOfMonth ->
                                    val selectedDate = "$dayOfMonth/${month + 1}/$year"
                                    eventFormViewModel.onEventDateChange(selectedDate)
                                },
                                calendar.get(Calendar.YEAR),
                                calendar.get(Calendar.MONTH),
                                calendar.get(Calendar.DAY_OF_MONTH)
                            ).show()
                        },

                        onTimeClick = {
                            TimePickerDialog(
                                context,
                                { _, hourOfDay, minute ->
                                    val selectedTime = String.format(
                                        Locale.getDefault(),
                                        "%02d:%02d",
                                        hourOfDay,
                                        minute
                                    )
                                    eventFormViewModel.onEventTimeChange(selectedTime)
                                },
                                calendar.get(Calendar.HOUR_OF_DAY),
                                calendar.get(Calendar.MINUTE),
                                true
                            ).show()
                        },

                        onYearSelected = eventFormViewModel::onYearSelected,
                        onYearDropdownExpandedChange = eventFormViewModel::onYearDropdownExpandedChange,

                        onPastEventValueChange = eventFormViewModel::updatePastEvent,
                        onAddPastEvent = eventFormViewModel::addPastEvent,
                        onDeletePastEvent = eventFormViewModel::removePastEvent,

                        onCreateClick = {
                        }
                    )
                }
            }
        }
    }
}