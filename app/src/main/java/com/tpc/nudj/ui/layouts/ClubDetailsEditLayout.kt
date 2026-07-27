package com.tpc.nudj.ui.layouts

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tpc.nudj.model.enums.ClubCategory
import com.tpc.nudj.ui.components.DynamicTextFieldList
import com.tpc.nudj.ui.components.NudjDescriptionTextField
import com.tpc.nudj.ui.components.NudjDropDownMenu
import com.tpc.nudj.ui.components.NudjImagePicker
import com.tpc.nudj.ui.components.NudjTextField
import com.tpc.nudj.ui.components.PrimaryButton
import com.tpc.nudj.ui.screen.auth.createAndEditEvent.ClubFormUiState
import com.tpc.nudj.ui.theme.LocalAppColors
import com.tpc.nudj.ui.theme.NudjTheme

@Composable
fun ClubDetailsEditLayout(
    uiState: ClubFormUiState,

    onClubNameChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,

    onCategorySelected: (ClubCategory) -> Unit,
    onCategoryDropdownExpandedChange: (Boolean) -> Unit,

    updateAchievement: (Int, String) -> Unit,
    addAchievement: () -> Unit,
    removeAchievement: (Int) -> Unit,

    updateUpcomingEvent: (Int, String) -> Unit,
    addUpcomingEvent: () -> Unit,
    removeUpcomingEvent: (Int) -> Unit,

    updatePastEvent: (Int, String) -> Unit,
    addPastEvent: () -> Unit,
    removePastEvent: (Int) -> Unit,

    onLogoSelected: () -> Unit,
    onCreateClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier=Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            NudjImagePicker(
                imageUri = uiState.logoUri,
                onClick = onLogoSelected,
                modifier = Modifier.size(100.dp)
            )
            Text(
                text="Logo",
                style= MaterialTheme.typography.titleMedium,
                color = LocalAppColors.current.onBackground
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        NudjTextField(
            value = uiState.clubName,
            onValueChange = onClubNameChange,
            placeholder = "Club name",
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text="Club Category",
            style= MaterialTheme.typography.titleMedium,
            color = LocalAppColors.current.onBackground
            )
        NudjDropDownMenu(
            expanded = uiState.isCategoryDropdownExpanded,
            selectedOption = uiState.category,
            options = ClubCategory.entries,
            placeholder = "Club Category",
            trailingIcon = Icons.Default.KeyboardArrowDown,
            onExpandedStateChange = onCategoryDropdownExpandedChange,
            onSelectedOptionChange = onCategorySelected,
            optionLabel = { it.categoryName }
        )

        Spacer(modifier = Modifier.height(12.dp))

        NudjDescriptionTextField(
            value = uiState.description,
            onValueChange = onDescriptionChange,
            placeholder = "About club (optional)",
            title="Club Description"
        )

        Spacer(modifier = Modifier.height(12.dp))

        DynamicTextFieldList(
            title = "Achievements",
            items = uiState.achievements,
            placeholder = "If any",
            onValueChange = updateAchievement,
            onAddClick = addAchievement,
            onDeleteClick = removeAchievement
        )

        DynamicTextFieldList(
            title = "Upcoming Events",
            items = uiState.upcomingEvents,
            placeholder = "If any",
            onValueChange = updateUpcomingEvent,
            onAddClick = addUpcomingEvent,
            onDeleteClick = removeUpcomingEvent
        )

        DynamicTextFieldList(
            title = "Past Events",
            items = uiState.pastEvents,
            placeholder = "If any",
            onValueChange = updatePastEvent,
            onAddClick = addPastEvent,
            onDeleteClick = removePastEvent
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
fun ClubDetailsEditLayoutPreview() {
    NudjTheme {
        ClubDetailsEditLayout(
            uiState = ClubFormUiState(
                clubName = "The Programming Club",
                category = ClubCategory.TECHNICAL,
                description = "Official Coding Club of IIITDMJ",
                achievements = listOf(
                    "Organised Central India's largest Hackathon"
                ),
                upcomingEvents = listOf(
                    "CodeRumble",
                    "Lockouts"
                ),
                pastEvents = listOf(
                    "Hackbyte"
                ),
                isCategoryDropdownExpanded = false
            ),

            onClubNameChange = {},
            onDescriptionChange = {},

            onCategorySelected = {},
            onCategoryDropdownExpandedChange = {},

            updateAchievement = { _, _ -> },
            addAchievement = {},
            removeAchievement = {},

            updateUpcomingEvent = { _, _ -> },
            addUpcomingEvent = {},
            removeUpcomingEvent = {},

            updatePastEvent = { _, _ -> },
            addPastEvent = {},
            removePastEvent = {},

            onLogoSelected = {},
            onCreateClick = {}
        )
    }
}