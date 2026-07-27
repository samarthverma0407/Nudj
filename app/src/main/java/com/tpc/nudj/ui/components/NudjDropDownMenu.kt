package com.tpc.nudj.ui.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.tpc.nudj.ui.theme.LocalAppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> NudjDropDownMenu(

    expanded: Boolean = false,
    onSelectedOptionChange: (T) -> Unit,
    selectedOption: T?,
    options: List<T>,
    placeholder: String,
    trailingIcon: ImageVector?=null,
    leadingIcon: ImageVector?=null,
    onExpandedStateChange: (Boolean) -> Unit,
    optionLabel: (T) -> String = { it.toString() }
) {
    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = onExpandedStateChange,
    ) {
        NudjTextField(
            value = selectedOption?.let(optionLabel) ?: "",
            onValueChange = {},
            placeholder = placeholder,

            trailingIcon = trailingIcon?.let{ icon ->
                {
                    Icon(
                        imageVector = icon,
                        contentDescription = "trailingIcon"
                    )
                }
            },
            leadingIcon = leadingIcon?.let{ icon ->
                {
                    Icon(
                        imageVector = icon,
                        contentDescription = "leadingIcon"
                    )
                }
            },
            modifier = Modifier

                .menuAnchor(
                    type = ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                    enabled = true
                ),
            readOnly = true

        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedStateChange(false) },
            modifier = Modifier
                .background(color = LocalAppColors.current.textFieldColor)
                .exposedDropdownSize(),
            shape = RoundedCornerShape(15.dp),
        ) {
            options.forEachIndexed { index, option ->
                DropdownMenuItem(

                    text = {
                        Text(
                            text = optionLabel(option),
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.Black
                        )

                    },

                    onClick = {
                        onSelectedOptionChange(option)
                        onExpandedStateChange(false)
                    },
                )
                if (index != options.lastIndex)
                    HorizontalDivider(color = LocalAppColors.current.surfaceColor)

            }
        }
    }
}





