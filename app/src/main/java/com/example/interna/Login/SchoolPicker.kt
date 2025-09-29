package com.example.interna.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.interna.R

data class School(
    val id: Int,
    val imageRes: Int,
    val name: String,
    val region: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SchoolPickerScreen(navController: NavController) {
    val isDarkTheme = isSystemInDarkTheme()

    val context = LocalContext.current
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var selectedRegion by rememberSaveable { mutableStateOf("All") }
    var isDropdownExpanded by rememberSaveable { mutableStateOf(false) }

    val regions = listOf(
        "All",
        "Region I",
        "Region II",
        "Region III",
        "Region IV",
        "Region V",
        "Region VI",
        "Region VII",
        "Region VIII",
        "Region IX",
        "Region X"
    )

    val mockSchools = listOf(
        School(
            id = 1,
            name = "test",
            imageRes = R.drawable.schoollogo,
            region = "Region V"
        ),
        School(
            id = 2,
            name = "Northern Samar State University",
            imageRes = R.drawable.schoollogo,
            region = "Region VIII"
        ),
        School(
            id = 3,
            name = "Northwest Samar State University",
            imageRes = R.drawable.schoollogo,
            region = "Region VIII"
        )

    )
    // Filter companies based on search query and selected course
    val filteredCompanies = mockSchools.filter { school ->
        val matchesSearch = school.name.contains(searchQuery, ignoreCase = true)

        val matchesCourse = selectedRegion == "All" || school.region == selectedRegion

        matchesSearch && matchesCourse
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(
                text = "Choose your School",
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
            )
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        )  {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                if (isDarkTheme) {
                                    Color.Black.copy(alpha = 0.3f)
                                } else {
                                    MaterialTheme.colorScheme.surface
                                }
                            )
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {

                            BasicTextField(
                                value = searchQuery,
                                onValueChange = { searchQuery = it },
                                singleLine = true,
                                textStyle = LocalTextStyle.current.copy(
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontSize = 14.sp
                                ),
                                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(40.dp)
                                    .background(
                                        if (isDarkTheme) {
                                            Color.Black.copy(alpha = 0.3f)
                                        } else {
                                            MaterialTheme.colorScheme.surface
                                        },
                                        MaterialTheme.shapes.small
                                    )
                                    .clip(MaterialTheme.shapes.small)
                                    .border(
                                        width = 1.dp,
                                        color = MaterialTheme.colorScheme.outline,
                                        shape = MaterialTheme.shapes.small
                                    )
                                    .padding(horizontal = 8.dp, vertical = 8.dp), // slim padding
                                decorationBox = { innerTextField ->
                                    Row(
                                        Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        // Leading search icon
                                        Icon(
                                            imageVector = Icons.Default.Search,
                                            contentDescription = "Search",
                                            tint = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
                                            modifier = Modifier.padding(end = 6.dp)
                                        )

                                        Box(Modifier.weight(1f)) {
                                            if (searchQuery.isEmpty()) {
                                                Text(
                                                    text = "Search companies...",
                                                    style = LocalTextStyle.current.copy(
                                                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                                                        fontSize = 14.sp
                                                    )
                                                )
                                            }
                                            innerTextField()
                                        }
                                    }
                                }
                            )

                            // Course Filter
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_filter),
                                    contentDescription = "Filter",
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))

                                ExposedDropdownMenuBox(
                                    expanded = isDropdownExpanded,
                                    onExpandedChange = { isDropdownExpanded = !isDropdownExpanded },
                                    modifier = Modifier.weight(1f)
                                ) {

                                    BasicTextField(
                                        value = selectedRegion,
                                        onValueChange = { }, // keep it readOnly
                                        readOnly = true,
                                        singleLine = true,
                                        textStyle = LocalTextStyle.current.copy(
                                            color = MaterialTheme.colorScheme.onSurface,
                                            fontSize = 14.sp
                                        ),
                                        cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                                        modifier = Modifier
                                            .menuAnchor() // still works for dropdown menu
                                            .fillMaxWidth()
                                            .height(40.dp)
                                            .background(
                                                if (isDarkTheme) {
                                                    Color.Black.copy(alpha = 0.3f)
                                                } else {
                                                    MaterialTheme.colorScheme.surface
                                                },
                                                MaterialTheme.shapes.small
                                            )
                                            .clip(MaterialTheme.shapes.small)
                                            .border(
                                                width = 1.dp,
                                                color = MaterialTheme.colorScheme.outline,
                                                shape = MaterialTheme.shapes.small
                                            )
                                            .padding(horizontal = 8.dp), // slim padding
                                        decorationBox = { innerTextField ->
                                            Row(
                                                Modifier.fillMaxWidth(),
                                                verticalAlignment = Alignment.CenterVertically
                                            ) {
                                                Box(Modifier.weight(1f)) {
                                                    if (selectedRegion.isEmpty()) {
                                                        Text(
                                                            text = "Filter by Department",
                                                            style = LocalTextStyle.current.copy(
                                                                color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                                                                fontSize = 14.sp
                                                            )
                                                        )
                                                    }
                                                    innerTextField()
                                                }

                                                // Dropdown trailing icon
                                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropdownExpanded)
                                            }
                                        }
                                    )

                                    ExposedDropdownMenu(
                                        expanded = isDropdownExpanded,
                                        onDismissRequest = { isDropdownExpanded = false }
                                    ) {
                                        regions.forEach { region ->
                                            DropdownMenuItem(
                                                text = { Text(region) },
                                                onClick = {
                                                    selectedRegion = region
                                                    isDropdownExpanded = false
                                                }
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                }
            }

            if (filteredCompanies.isEmpty()) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(
                                    if (isDarkTheme) {
                                        Color.Black.copy(alpha = 0.3f)
                                    } else {
                                        MaterialTheme.colorScheme.surface
                                    }
                                )
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(32.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_info),
                                    contentDescription = "No companies",
                                    tint = Color(0xFFCCCCCC),
                                    modifier = Modifier.size(48.dp)
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = "No companies found",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Medium
                                )
                                Text(
                                    text = "Try adjusting your search or filter criteria",
                                    fontSize = 12.sp,
                                    color = Color(0xFF666666)
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                OutlinedButton(
                                    onClick = {
                                        searchQuery = ""
                                        selectedRegion = "All Regions"
                                    }
                                ) {
                                    Text("Clear Filters")
                                }
                            }
                        }
                    }
                }
            } else {
                items(filteredCompanies) { school ->
                    SchoolCard(school = school)
                }
            }

            item {
                Spacer(modifier = Modifier.height(40.dp)) // change to whatever height you need
            }
        }

    }
}

@Composable
fun SchoolCard(
    school: School
) {
    val isDarkTheme = isSystemInDarkTheme()

    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    if (isDarkTheme) {
                        Color.Black.copy(alpha = 0.3f)
                    } else {
                        MaterialTheme.colorScheme.surface
                    }
                )
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // School Header
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Image(
                                painter = painterResource(id = school.imageRes),
                                contentDescription = "School",
                                modifier = Modifier.size(46.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = school.name,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                }
            }
        }
    }
}
