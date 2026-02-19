package com.example.ecommerceapp.presentation.categoria

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ecommerceapp.model.Produto
import com.example.ecommerceapp.navigation.Destination
import com.example.ecommerceapp.presentation.components.ProdutosCard
import com.example.ecommerceapp.presentation.produto.ProdutoViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProdutosPorCategoriaScreen(
    navController: NavController,
    categoriaId: String,
    produtoViewModel: ProdutoViewModel = viewModel(),
    navigateToDetailScreen: (Produto) -> Unit = {}
) {
    val produtos = produtoViewModel.produtoList.observeAsState(emptyList())
    val isLoading = produtoViewModel.isLoading.observeAsState(false)

    LaunchedEffect(categoriaId) {
        produtoViewModel.carregarProdutosPorCategoria(categoriaId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Produtos da categoria") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Voltar"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        if (isLoading.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                item {
                    Text(
                        text = "Produtos",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E293B),
                        modifier = Modifier
                            .padding(bottom = 10.dp, start = 30.dp)
                            .fillMaxWidth()
                    )
                }

                item {
                    FlowRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                        maxItemsInEachRow = 2
                    ) {
                        produtos.value.forEach { produto ->
                            ProdutosCard(
                                produto = produto,
                                modifier = Modifier.padding(all = 8.dp),
                                onClick = {
                                    navController.navigate(
                                        Destination.Details(produto)
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}