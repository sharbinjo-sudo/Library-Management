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
import com.library.management.data.Member
import com.library.management.viewmodel.MemberViewModel

@Composable
fun ViewMembersScreen(
    navController: NavController,
    viewModel: MemberViewModel = viewModel()
) {
    val members by viewModel.members.collectAsState()
    var editingMember by remember { mutableStateOf<Member?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("👥 View Members", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(16.dp))

        if (members.isEmpty()) {
            Text("No members found.", style = MaterialTheme.typography.bodyMedium)
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(members) { member ->
                    MemberCard(
                        member = member,
                        onDelete = { viewModel.deleteMember(it) },
                        onEdit = { editingMember = it }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.popBackStack() }) {
            Text("Back to Dashboard")
        }

        if (editingMember != null) {
            EditMemberDialog(
                member = editingMember!!,
                onDismiss = { editingMember = null },
                onSave = { updated ->
                    viewModel.updateMember(updated)
                    editingMember = null
                }
            )
        }
    }
}

@Composable
fun MemberCard(member: Member, onDelete: (Member) -> Unit, onEdit: (Member) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(3.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text("👤 Name: ${member.name}", style = MaterialTheme.typography.bodyLarge)
            Text("🆔 Roll No: ${member.rollNo}", style = MaterialTheme.typography.bodyMedium)
            Text("📞 Contact: ${member.contact}", style = MaterialTheme.typography.bodySmall)
            Spacer(modifier = Modifier.height(8.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = { onEdit(member) }) { Text("Edit") }
                OutlinedButton(onClick = { onDelete(member) }) { Text("Delete") }
            }
        }
    }
}

@Composable
fun EditMemberDialog(member: Member, onDismiss: () -> Unit, onSave: (Member) -> Unit) {
    var newName by remember { mutableStateOf(member.name) }
    var newRollNo by remember { mutableStateOf(member.rollNo) }
    var newContact by remember { mutableStateOf(member.contact) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("✏️ Edit Member") },
        text = {
            Column {
                OutlinedTextField(
                    value = newName,
                    onValueChange = { newName = it },
                    label = { Text("Name") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = newRollNo,
                    onValueChange = { newRollNo = it },
                    label = { Text("Roll No") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = newContact,
                    onValueChange = { newContact = it },
                    label = { Text("Contact") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                onSave(member.copy(name = newName, rollNo = newRollNo, contact = newContact))
            }) { Text("Save") }
        },
        dismissButton = {
            OutlinedButton(onClick = onDismiss) { Text("Cancel") }
        }
    )
}
