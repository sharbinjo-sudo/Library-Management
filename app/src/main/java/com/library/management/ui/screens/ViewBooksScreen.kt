package com.library.management.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.library.management.data.Book
import com.library.management.viewmodel.BookViewModel

@Composable
fun ViewBooksScreen(
    navController: NavController,
    viewModel: BookViewModel = viewModel()
) {
    // ✅ fixed name — matches val books in BookViewModel
    val books by viewModel.books.collectAsState()
    var editingBook by remember { mutableStateOf<Book?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("📚 View Books", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        if (books.isEmpty()) {
            Text("No books added yet.", style = MaterialTheme.typography.bodyMedium)
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(books) { book ->
                    BookCard(
                        book = book,
                        onDelete = { viewModel.deleteBook(it) },
                        onEdit = { editingBook = it }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Back to Dashboard")
        }

        // 📝 Edit dialog
        if (editingBook != null) {
            EditBookDialog(
                book = editingBook!!,
                onDismiss = { editingBook = null },
                onSave = { updated ->
                    viewModel.updateBook(updated)
                    editingBook = null
                }
            )
        }
    }
}

@Composable
fun BookCard(
    book: Book,
    onDelete: (Book) -> Unit,
    onEdit: (Book) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(text = "📖 Title: ${book.title}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "👤 Author: ${book.author}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "🏷️ Category: ${book.category}", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = { onEdit(book) }) {
                    Text("Edit")
                }
                OutlinedButton(onClick = { onDelete(book) }) {
                    Text("Delete")
                }
            }
        }
    }
}

@Composable
fun EditBookDialog(
    book: Book,
    onDismiss: () -> Unit,
    onSave: (Book) -> Unit
) {
    var newTitle by remember { mutableStateOf(book.title) }
    var newAuthor by remember { mutableStateOf(book.author) }
    var newCategory by remember { mutableStateOf(book.category) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("✏️ Edit Book") },
        text = {
            Column {
                OutlinedTextField(
                    value = newTitle,
                    onValueChange = { newTitle = it },
                    label = { Text("Book Title") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = newAuthor,
                    onValueChange = { newAuthor = it },
                    label = { Text("Author") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = newCategory,
                    onValueChange = { newCategory = it },
                    label = { Text("Category") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                onSave(
                    book.copy(
                        title = newTitle,
                        author = newAuthor,
                        category = newCategory
                    )
                )
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}
