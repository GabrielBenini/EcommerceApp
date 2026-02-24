package com.example.ecommerceapp.presentation.extrato

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ecommerceapp.model.ExtratoMovimento
import com.example.ecommerceapp.presentation.usuario.UsuarioViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ExtratoSaldoScreen(
    navController: NavController,
    usuarioViewModel: UsuarioViewModel = viewModel()
) {
    var extrato by remember { mutableStateOf<List<ExtratoMovimento>>(emptyList()) }
    val df = remember { SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("pt", "BR")) }

    LaunchedEffect(Unit) {
        val userId = usuarioViewModel.usuarioId.value ?: return@LaunchedEffect
        usuarioViewModel.firestore.collection("usuarios")
            .document(userId)
            .collection("extrato")
            .get()
            .addOnSuccessListener { res ->
                extrato = res.documents.mapNotNull {
                    it.toObject(ExtratoMovimento::class.java)
                }.sortedByDescending { it.data }
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Extrato de Saldo",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (extrato.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Nenhuma movimentação encontrada")
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                items(extrato) { item ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F7F7))
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column {
                                Text(
                                    text = if (item.tipo == "recarga") "Recarga via ${item.metodo}" else "Compra",
                                    fontWeight = FontWeight.SemiBold
                                )
                                Text(
                                    text = df.format(Date(item.data)),
                                    color = Color.Gray,
                                    fontSize = MaterialTheme.typography.bodySmall.fontSize
                                )
                            }
                            Text(
                                text = "R$ %.2f".format(item.valor),
                                color = if (item.tipo == "recarga") Color(0xFF2E7D32) else Color.Red,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }
            }
        }
    }
}