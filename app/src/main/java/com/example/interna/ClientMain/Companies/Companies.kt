package com.example.interna.ClientMain.Companies

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import android.content.Intent
import android.net.Uri
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.interna.R

data class Company(
    val id: String,
    val name: String,
    val coordinator: String,
    val phone: String,
    val email: String,
    val course: String,
    val location: String,
    val description: String,
    val slots: Int,
    val industry: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompaniesScreen(navController: NavController) {
    val context = LocalContext.current
    var searchQuery by remember { mutableStateOf("") }
    var selectedCourse by remember { mutableStateOf("All Courses") }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    val courses = listOf(
        "All Courses",
        "Computer Science",
        "Information Technology",
        "Computer Engineering",
        "Multimedia Arts"
    )

    val mockCompanies = listOf(
        Company(
            id = "1",
            name = "TechCorp Solutions",
            coordinator = "Maria Santos",
            phone = "+63 917 123 4567",
            email = "maria.santos@techcorp.com",
            course = "Computer Science",
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
            course = "Multimedia Arts",
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
            course = "Information Technology",
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
            course = "Computer Engineering",
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
            course = "Multimedia Arts",
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
            course = "Information Technology",
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
            course = "Computer Science",
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
            course = "Computer Engineering",
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

        val matchesCourse = selectedCourse == "All Courses" || company.course == selectedCourse

        matchesSearch && matchesCourse
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize())
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 4.dp)
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
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        colors = CardDefaults.cardColors(containerColor = Color.White)
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            // Search Bar
                            OutlinedTextField(
                                value = searchQuery,
                                onValueChange = { searchQuery = it },
                                placeholder = { Text("Search companies, coordinators, or industries...") },
                                leadingIcon = {
                                    Icon(Icons.Default.Search, contentDescription = "Search")
                                },
                                modifier = Modifier.fillMaxWidth(),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedContainerColor = Color(0xFFF8F9FA),
                                    unfocusedContainerColor = Color(0xFFF8F9FA)
                                )
                            )

                            // Course Filter
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    painter = painterResource(R.drawable.ic_filter),
                                    contentDescription = "Filter",
                                    tint = Color(0xFF666666),
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(8.dp))

                                ExposedDropdownMenuBox(
                                    expanded = isDropdownExpanded,
                                    onExpandedChange = { isDropdownExpanded = !isDropdownExpanded },
                                    modifier = Modifier.weight(1f)
                                ) {
                                    OutlinedTextField(
                                        value = selectedCourse,
                                        onValueChange = { },
                                        readOnly = true,
                                        placeholder = { Text("Filter by course") },
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(expanded = isDropdownExpanded)
                                        },
                                        modifier = Modifier
                                            .menuAnchor()
                                            .fillMaxWidth(),
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedContainerColor = Color(0xFFF8F9FA),
                                            unfocusedContainerColor = Color(0xFFF8F9FA)
                                        )
                                    )

                                    ExposedDropdownMenu(
                                        expanded = isDropdownExpanded,
                                        onDismissRequest = { isDropdownExpanded = false }
                                    ) {
                                        courses.forEach { course ->
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
                                Text(
                                    text = "${filteredCompanies.size} companies found",
                                    fontSize = 12.sp,
                                    color = Color(0xFF666666)
                                )

                                if (selectedCourse != "All Courses") {
                                    AssistChip(
                                        onClick = { },
                                        label = {
                                            Text(
                                                selectedCourse,
                                                fontSize = 10.sp
                                            )
                                        },
                                        colors = AssistChipDefaults.assistChipColors(
                                            containerColor = Color(0xFF2196F3).copy(alpha = 0.1f),
                                            labelColor = Color(0xFF2196F3)
                                        )
                                    )
                                }
                            }
                        }
                    }
                }

                if (filteredCompanies.isEmpty()) {
                    item {
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                            colors = CardDefaults.cardColors(containerColor = Color.White)
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
                                        selectedCourse = "All Courses"
                                    }
                                ) {
                                    Text("Clear Filters")
                                }
                            }
                        }
                    }
                } else {
                    items(filteredCompanies) { company ->
                        CompanyCard(company = company)
                    }
                }


            }
        }


    }
}

@Composable
fun CompanyCard(
    company: Company
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
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
                            painter = painterResource(R.drawable.ic_info),
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
                        color = Color(0xFF666666)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.LocationOn,
                                contentDescription = "Location",
                                tint = Color(0xFF666666),
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = company.location,
                                fontSize = 10.sp,
                                color = Color(0xFF666666)
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.ic_info),
                                contentDescription = "Course",
                                tint = Color(0xFF666666),
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = company.course,
                                fontSize = 10.sp,
                                color = Color(0xFF666666)
                            )
                        }
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    AssistChip(
                        onClick = { },
                        label = {
                            Text(
                                "${company.slots} slots",
                                fontSize = 10.sp
                            )
                        },
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = Color(0xFF4CAF50).copy(alpha = 0.1f),
                            labelColor = Color(0xFF4CAF50)
                        )
                    )

                    Icon(
                        Icons.Default.Warning,
                        contentDescription = "Warning",
                        tint = Color(0xFFFF9800),
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            // Coordinator Information
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFF8F9FA)
                )
            ) {
                Column(
                    modifier = Modifier.padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            Icons.Default.Person,
                            contentDescription = "Coordinator",
                            tint = Color(0xFF666666),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Coordinator",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF333333)
                        )
                    }

                    Text(
                        text = company.coordinator,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )

                    Column(
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Phone,
                                contentDescription = "Phone",
                                tint = Color(0xFF666666),
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = company.phone,
                                fontSize = 12.sp,
                                color = Color(0xFF666666)
                            )
                        }

                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                Icons.Default.Email,
                                contentDescription = "Email",
                                tint = Color(0xFF666666),
                                modifier = Modifier.size(12.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = company.email,
                                fontSize = 12.sp,
                                color = Color(0xFF666666)
                            )
                        }
                    }
                }
            }


        }
    }
}