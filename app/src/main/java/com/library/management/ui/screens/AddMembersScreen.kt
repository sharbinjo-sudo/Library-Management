package com.library.management.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.library.management.data.Member
import com.library.management.viewmodel.MemberViewModel

@Composable
fun AddMemberScreen(
    navController: NavController,
    viewModel: MemberViewModel = viewModel()
) {
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var rollNo by remember { mutableStateOf(TextFieldValue("")) }
    var contact by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("➕ Add Member", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Full Name") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = rollNo,
            onValueChange = { rollNo = it },
            label = { Text("Roll Number / ID") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = contact,
            onValueChange = { contact = it },
            label = { Text("Contact Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (name.text.isNotBlank() && rollNo.text.isNotBlank()) {
                    val newMember = Member(
                        name = name.text,
                        rollNo = rollNo.text,
                        contact = contact.text
                    )
                    viewModel.addMember(newMember)
                    navController.navigate("view_members")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Member")
        }

        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { navController.popBackStack() }) {
            Text("Back to Dashboard")
        }
    }
}
