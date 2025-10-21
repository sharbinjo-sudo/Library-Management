package com.library.management.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.library.management.data.IssuedBook
import com.library.management.viewmodel.IssuedBookViewModel
import com.library.management.viewmodel.IssuedBookViewModelFactory

@Composable
fun IssueBookScreen(navController: NavController) {
    val context = LocalContext.current
    val factory = IssuedBookViewModelFactory(context)
    val viewModel: IssuedBookViewModel = viewModel(factory = factory)

    var bookTitle by remember { mutableStateOf(TextFieldValue("")) }
    var memberName by remember { mutableStateOf(TextFieldValue("")) }
    var issueDate by remember { mutableStateOf(TextFieldValue("")) }
    var returnDate by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("📘 Issue Book", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = bookTitle,
            onValueChange = { bookTitle = it },
            label = { Text("Book Title") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = memberName,
            onValueChange = { memberName = it },
            label = { Text("Member Name") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = issueDate,
            onValueChange = { issueDate = it },
            label = { Text("Issue Date (dd/mm/yyyy)") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = returnDate,
            onValueChange = { returnDate = it },
            label = { Text("Return Date (dd/mm/yyyy)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                if (bookTitle.text.isNotBlank() && memberName.text.isNotBlank()) {
                    viewModel.addIssuedBook(
                        IssuedBook(
                            id = 0,
                            bookTitle = bookTitle.text,
                            memberName = memberName.text,
                            issueDate = issueDate.text,
                            returnDate = returnDate.text
                        )
                    )
                    navController.navigate("view_issued")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Issue Book")
        }

        Spacer(Modifier.height(8.dp))

        TextButton(onClick = { navController.popBackStack() }) {
            Text("Back to Dashboard")
        }
    }
}
