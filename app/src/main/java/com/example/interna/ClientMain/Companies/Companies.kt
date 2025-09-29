package com.example.interna.ClientMain.Companies

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.interna.R

data class Company(
    val id: String,
    val name: String,
    val coordinator: String,
    val phone: String,
    val email: String,
    val department: String,
    val location: String,
    val description: String,
    val slots: Int,
    val industry: String
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompaniesScreen(navController: NavController) {
    val isDarkTheme = isSystemInDarkTheme()

    val context = LocalContext.current
    var searchQuery by rememberSaveable { mutableStateOf("") }
    var selectedCourse by rememberSaveable { mutableStateOf("All Departments") }
    var isDropdownExpanded by rememberSaveable { mutableStateOf(false) }

    val departments = listOf(
        "All Departments",
        "College of Engineering and Architecture",
        "College of Education",
        "College of Management",
        "College of Computing and Information Sciences",
        "College of Criminal Justice and Sciences",
        "College of Agriculture and Technology",
        "College of Nursing",
        "Graduate School"
    )

    val mockCompanies = listOf(
        Company(
            id = "1",
            name = "TechCorp Solutions",
            coordinator = "Maria Santos",
            phone = "+63 917 123 4567",
            email = "maria.santos@techcorp.com",
            department = "College of Computing and Information Sciences",
            location = "Iloilo City",
            description = "Leading software development company specializing in web and mobile applications.",
            slots = 5,
            industry = "Technology"
        ),
        Company(
            id = "2",
            name = "CreativeHub Agency",
            coordinator = "John Dela Cruz",
            phone = "+63 925 987 6543",
            email = "john.delacruz@creativehub.ph",
            department = "College of Education", // for Multimedia/Arts/Design-related
            location = "Iloilo City",
            description = "Digital marketing and design agency serving local and international clients.",
            slots = 3,
            industry = "Marketing"
        ),
        Company(
            id = "3",
            name = "DataFlow Analytics",
            coordinator = "Sarah Rodriguez",
            phone = "+63 999 555 7777",
            email = "s.rodriguez@dataflow.com",
            department = "College of Computing and Information Sciences",
            location = "Iloilo City",
            description = "Business intelligence and data analytics consulting firm.",
            slots = 4,
            industry = "Analytics"
        ),
        Company(
            id = "4",
            name = "GreenTech Innovations",
            coordinator = "Michael Chen",
            phone = "+63 918 333 9999",
            email = "michael.chen@greentech.ph",
            department = "College of Engineering and Architecture",
            location = "Iloilo City",
            description = "Renewable energy and IoT solutions company.",
            slots = 2,
            industry = "Green Technology"
        ),
        Company(
            id = "5",
            name = "PixelPerfect Studio",
            coordinator = "Anna Martinez",
            phone = "+63 926 444 1111",
            email = "anna.martinez@pixelperfect.com",
            department = "College of Education", // for creative/media/arts
            location = "Iloilo City",
            description = "Creative studio specializing in branding, web design, and digital content.",
            slots = 6,
            industry = "Design"
        ),
        Company(
            id = "6",
            name = "SecureNet Systems",
            coordinator = "Roberto Garcia",
            phone = "+63 917 888 2222",
            email = "roberto.garcia@securenet.ph",
            department = "College of Computing and Information Sciences",
            location = "Iloilo City",
            description = "Cybersecurity and network infrastructure solutions provider.",
            slots = 3,
            industry = "Security"
        ),
        Company(
            id = "7",
            name = "MobileFirst Dev",
            coordinator = "Lisa Fernandez",
            phone = "+63 928 666 3333",
            email = "lisa.fernandez@mobilefirst.com",
            department = "College of Computing and Information Sciences",
            location = "Iloilo City",
            description = "Mobile app development company focusing on iOS and Android platforms.",
            slots = 4,
            industry = "Mobile Development"
        ),
        Company(
            id = "8",
            name = "CloudTech Solutions",
            coordinator = "David Lim",
            phone = "+63 919 777 4444",
            email = "david.lim@cloudtech.ph",
            department = "College of Engineering and Architecture",
            location = "Iloilo City",
            description = "Cloud computing and infrastructure as a service provider.",
            slots = 5,
            industry = "Cloud Computing"
        )
    )


    // Filter companies based on search query and selected course
    val filteredCompanies = mockCompanies.filter { company ->
        val matchesSearch = company.name.contains(searchQuery, ignoreCase = true) ||
                company.coordinator.contains(searchQuery, ignoreCase = true) ||
                company.industry.contains(searchQuery, ignoreCase = true)

        val matchesCourse = selectedCourse == "All Departments" || company.department == selectedCourse

        matchesSearch && matchesCourse
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize())
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 14.dp, end = 14.dp, top = 4.dp, bottom = 15.5.dp)
            ) {
                Text(
                    text = "Partnered Companies",
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp,
                )
            }

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            )  {
                // Search and Filter Section
                item{
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
                                            value = selectedCourse,
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
                                                .padding(horizontal = 8.dp, vertical = 8.dp), // slim padding
                                            decorationBox = { innerTextField ->
                                                Row(
                                                    Modifier.fillMaxWidth(),
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    Box(Modifier.weight(1f)) {
                                                        if (selectedCourse.isEmpty()) {
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
                                            departments.forEach { course ->
                                                DropdownMenuItem(
                                                    text = { Text(course) },
                                                    onClick = {
                                                        selectedCourse = course
                                                        isDropdownExpanded = false
                                                    }
                                                )
                                            }
                                        }
                                    }
                                }

                                // Results Count
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Row {
                                        Text(
                                            text = "${filteredCompanies.size} companies available with MOA",
                                            fontSize = 12.sp
                                        )

                                        Icon(
                                            painter = painterResource(R.drawable.ic_tutorial),
                                            contentDescription = "MOA guide",
                                            modifier = Modifier
                                                .size(28.dp)
                                                .padding(start = 8.dp, bottom = 4.dp)
                                                .clip(CircleShape)
                                                .clickable {

                                                },
                                            tint = MaterialTheme.colorScheme.onSurface.copy(0.6f)
                                        )
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
                                            selectedCourse = "All Departments"
                                        }
                                    ) {
                                        Text("Clear Filters")
                                    }
                                }
                            }
                        }
                    }
                } else {
                    items(filteredCompanies) { company ->
                        CompanyCard(company = company)
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(40.dp)) // change to whatever height you need
                }

            }
        }


    }
}

@Composable
fun CompanyCard(
    company: Company
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
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                // Company Header
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
                            Icon(
                                painter = painterResource(R.drawable.ic_office),
                                contentDescription = "Company",
                                tint = Color(0xFF2196F3),
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = company.name,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = company.description,
                            fontSize = 12.sp,
                            lineHeight = 16.sp
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.LocationOn,
                                contentDescription = "Location",
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = company.location,
                                fontSize = 10.sp,
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_course), // update icon for department
                                contentDescription = "Department",
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = company.department, // updated field
                                fontSize = 10.sp
                            )
                        }
                    }
                }

                // Coordinator Information
                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                if (isDarkTheme) {
                                    Color.Black.copy(alpha = 0.1f)
                                } else {
                                    MaterialTheme.colorScheme.surface.copy(alpha = 0.4f)
                                }
                            )
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Default.Person,
                                    contentDescription = "Coordinator",
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Coordinator",
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }

                            Text(
                                text = company.coordinator,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Medium
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Default.Phone,
                                    contentDescription = "Phone",
                                    modifier = Modifier.size(12.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = company.phone,
                                    fontSize = 12.sp,
                                )
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Default.Email,
                                    contentDescription = "Email",
                                    modifier = Modifier.size(12.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = company.email,
                                    fontSize = 12.sp,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
