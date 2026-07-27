package com.tpc.nudj.ui.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tpc.nudj.ui.theme.LocalAppColors
import com.tpc.nudj.ui.theme.NudjTheme

@Composable
fun DynamicTextFieldList(
    title: String,
    items: List<String>,
    placeholder: String,
    onValueChange: (Int, String) -> Unit,
    onAddClick : () -> Unit,
    onDeleteClick :(Int) -> Unit,
){
    Column(){
        Text(
            text=title,
            style= MaterialTheme.typography.titleMedium,
            color = LocalAppColors.current.onBackground
        )
        items.forEachIndexed { index, string ->
            Row(
                modifier=Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ){
                NudjTextField(
                    value = string,
                    onValueChange = { onValueChange(index,it) },
                    placeholder = placeholder,
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = {
                        if (index == items.lastIndex)
                            onAddClick()
                        else
                            onDeleteClick(index)
                    }
                ) {
                    Icon(
                        imageVector = if (index == items.lastIndex)
                            Icons.Default.AddCircleOutline
                        else
                            Icons.Default.Delete,
                        contentDescription = null,
                        tint = LocalAppColors.current.onBackground
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true , uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DynamicTextFieldListPreview() {
    NudjTheme {
        DynamicTextFieldList(
            title = "Achievements",
            items = listOf(
                "Winner - Hackathon 2025",
                "Runner-up - Coding Contest",
                ""
            ),
            placeholder = "If any",
            onValueChange = { _, _ -> },
            onAddClick = { },
            onDeleteClick = { }
        )
    }
}