package com.tpc.nudj.ui.layouts

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tpc.nudj.ui.components.DynamicTextFieldList
import com.tpc.nudj.ui.components.NudjDescriptionTextField
import com.tpc.nudj.ui.components.NudjDropDownMenu
import com.tpc.nudj.ui.components.NudjImagePicker
import com.tpc.nudj.ui.components.NudjTextField
import com.tpc.nudj.ui.components.PrimaryButton
import com.tpc.nudj.ui.screen.auth.createAndEditEvent.EventFormUiState
import com.tpc.nudj.ui.theme.LocalAppColors
import com.tpc.nudj.ui.theme.NudjTheme

@Composable
fun EventDetailsEditLayout(
    uiState: EventFormUiState,
    onLogoClick: () -> Unit,
    onBannerClick: () -> Unit,
    onPhotoClick: (Int) -> Unit,
    onEventNameChange: (String) -> Unit,
    onDateClick: () -> Unit,
    onTimeClick: () -> Unit,
    onVenueChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onYearSelected: (Int) -> Unit,
    onYearDropdownExpandedChange: (Boolean) -> Unit,
    onPastEventValueChange: (Int, String) -> Unit,
    onAddPastEvent: () -> Unit,
    onDeletePastEvent: (Int) -> Unit,
    onCreateClick: () -> Unit,
){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ){
        Column(
            modifier=Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            NudjImagePicker(
                imageUri = uiState.logoUri,
                onClick = onLogoClick,
                modifier = Modifier.size(100.dp)
            )
            Text(
                text="Logo",
                style= MaterialTheme.typography.titleMedium,
                color = LocalAppColors.current.onBackground
            )
        }

        Spacer(modifier=Modifier.height(12.dp))

        NudjTextField(
            value = uiState.eventName,
            onValueChange = onEventNameChange,
            placeholder = "Event Name",
            modifier = Modifier.padding(horizontal=32.dp)
        )

        Spacer(modifier=Modifier.height(12.dp))

        Column {
            Text(
                text="Event Banner",
                style= MaterialTheme.typography.titleMedium,
                color = LocalAppColors.current.onBackground
                )
            NudjImagePicker(
                imageUri = uiState.bannerUri,
                onClick = onBannerClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                shape = RoundedCornerShape(12.dp)
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier= Modifier.fillMaxWidth()
        ) {
            Column(modifier=Modifier.weight(1f)){
                Text(
                    text="Event Date",
                    style = MaterialTheme.typography.titleMedium,
                    color = LocalAppColors.current.onBackground
                )
                NudjTextField(
                    value = uiState.eventDate,
                    onValueChange = {},
                    placeholder = "Date",
                    readOnly = true,
                    trailingIcon = {
                        IconButton(
                            onClick = onDateClick
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = null
                            )
                        }
                    }
                )
            }

            Spacer(modifier = Modifier.width(10.dp))

            Column(modifier=Modifier.weight(1f)){
                Text(
                    text="Event Time",
                    style = MaterialTheme.typography.titleMedium,
                    color = LocalAppColors.current.onBackground
                    )
                NudjTextField(
                    value = uiState.eventTime,
                    onValueChange = {},
                    placeholder = "Time",
                    readOnly = true,
                    trailingIcon = {
                        IconButton(
                            onClick = onTimeClick
                        ) {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = null
                            )
                        }
                    }
                )
            }
        }

        Spacer(modifier=Modifier.height(12.dp))

        Text(
            text="Event Venue",
            style = MaterialTheme.typography.titleMedium,
            color = LocalAppColors.current.onBackground
            )
        NudjTextField(
            value = uiState.venue,
            onValueChange = onVenueChange,
            placeholder = "Eg: Auditorium"
        )

        Spacer(modifier=Modifier.height(12.dp))

        NudjDescriptionTextField(
            value = uiState.description,
            onValueChange = onDescriptionChange,
            placeholder = "About event (optional)",
            title = "Event Description"
        )

        Spacer(modifier=Modifier.height(12.dp))

        DynamicTextFieldList(
            title = "Past Events",
            items = uiState.pastEvents,
            placeholder = "If any",
            onValueChange = onPastEventValueChange,
            onAddClick = onAddPastEvent,
            onDeleteClick = onDeletePastEvent
        )

        Text(text="Add Event Photos",
            style= MaterialTheme.typography.titleMedium,
            color = LocalAppColors.current.onBackground
            )
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            uiState.photoUris.forEachIndexed { index, uri ->

                NudjImagePicker(
                    imageUri = uri,
                    onClick = {
                        onPhotoClick(index)
                    },
                    modifier = Modifier.size(75.dp).weight(1f),
                    shape = RoundedCornerShape(12.dp)
                )
            }
        }

        Spacer(modifier=Modifier.height(12.dp))

        Text(
            text="Select the years it's applicable to ",
            style= MaterialTheme.typography.titleMedium,
            color = LocalAppColors.current.onBackground
            )
        NudjDropDownMenu(
            selectedOption = uiState.selectedYear,
            options = listOf(1, 2, 3, 4),
            expanded = uiState.isYearDropdownExpanded,
            onExpandedStateChange = onYearDropdownExpandedChange,
            onSelectedOptionChange = onYearSelected,
            placeholder = "Which year?",
            trailingIcon = Icons.Default.KeyboardArrowDown
        )

        Spacer(modifier = Modifier.height(24.dp))

        PrimaryButton(
            text = "Create",
            onClick = onCreateClick,
            modifier=Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true , uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun EventDetailsEditLayoutPreview() {
    NudjTheme {
        EventDetailsEditLayout(
            uiState = EventFormUiState(
                eventName = "Event Name",
                eventDate = "25 Jul 2026",
                eventTime = "6:30 PM",
                venue = "Event Venue",
                description = "Join us for this great event",
                pastEvents = listOf(
                    "Workshop",
                    "Camp"
                ),
                selectedYear = 2
            ),
            onLogoClick = {},
            onBannerClick = {},
            onPhotoClick = {},
            onEventNameChange = {},
            onVenueChange = {},
            onDescriptionChange = {},
            onYearSelected = {},
            onYearDropdownExpandedChange = {},
            onPastEventValueChange = { _, _ -> },
            onAddPastEvent = {},
            onDeletePastEvent = {},
            onCreateClick = {},
            onDateClick = {},
            onTimeClick = {}
        )
    }
}