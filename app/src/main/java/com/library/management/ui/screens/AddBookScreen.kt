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
import com.library.management.viewmodel.BookViewModel
import com.library.management.data.Book

@Composable
fun AddBookScreen(navController: NavController, viewModel: BookViewModel = viewModel()) {
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var author by remember { mutableStateOf(TextFieldValue("")) }
    var category by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("➕ Add Book", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Book Title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Author") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = category,
            onValueChange = { category = it },
            label = { Text("Category") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (title.text.isNotBlank() && author.text.isNotBlank()) {
                    // ✅ Create a Book object as your ViewModel expects
                    val newBook = Book(
                        title = title.text,
                        author = author.text,
                        category = category.text,
                        availableCopies = 1
                    )
                    viewModel.addBook(newBook)
                    navController.navigate("view_books")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Book")
        }

        Spacer(modifier = Modifier.height(8.dp))
        TextButton(onClick = { navController.popBackStack() }) {
            Text("Back to Dashboard")
        }
    }
}
