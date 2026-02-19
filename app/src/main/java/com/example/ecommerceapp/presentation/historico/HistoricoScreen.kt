package com.example.ecommerceapp.presentation.historico

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ecommerceapp.model.HistoricoCompra
import com.example.ecommerceapp.presentation.carrinho.CarrinhoViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoricoScreen(
    navController: NavHostController,
    carrinhoViewModel: CarrinhoViewModel // ✅ Recebe do NavHost
) {
    val scope = rememberCoroutineScope()
    var listaHistorico by remember { mutableStateOf<List<HistoricoCompra>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        scope.launch {
            listaHistorico = carrinhoViewModel.buscarHistoricoCompras()
            isLoading = false
        }
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(
                text = "Histórico de Compras",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E293B),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 24.dp)
            )

            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }
                listaHistorico.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Nenhuma compra realizada ainda 💸", color = Color.Gray)
                    }
                }
                else -> {
                    LazyColumn(modifier = Modifier.padding(horizontal = 16.dp)) {
                        items(listaHistorico) { compra ->
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 12.dp),
                                elevation = CardDefaults.cardElevation(4.dp)
                            ) {
                                Column(Modifier.padding(16.dp)) {
                                    Text(
                                        "Data: ${java.text.SimpleDateFormat("dd/MM/yyyy HH:mm").format(compra.dataCompra)}",
                                        fontWeight = FontWeight.SemiBold,
                                        fontSize = 14.sp
                                    )
                                    Text(
                                        "Total: R$ %.2f".format(compra.total),
                                        fontSize = 16.sp,
                                        color = Color(0xFF0066CC),
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(vertical = 4.dp)
                                    )
                                    Text(
                                        "Itens comprados:",
                                        fontWeight = FontWeight.SemiBold
                                    )
                                    compra.itens.forEach {
                                        Text("• ${it.nome} - ${it.quantidade}x  R$%.2f".format(it.price))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}