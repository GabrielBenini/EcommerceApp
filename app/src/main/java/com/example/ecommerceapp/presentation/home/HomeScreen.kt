package com.example.ecommerceapp.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.ecommerceapp.model.Produto
import com.example.ecommerceapp.presentation.components.AgiStoreHeader
import com.example.ecommerceapp.presentation.components.BottomBar
import com.example.ecommerceapp.presentation.components.CategoriaCard
import com.example.ecommerceapp.presentation.components.DestaqueCard
import com.example.ecommerceapp.presentation.components.ProdutosCard
import com.example.ecommerceapp.presentation.components.SearchField
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ecommerceapp.presentation.categoria.CategoriaViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.livedata.observeAsState
import com.example.ecommerceapp.navigation.Destination
import com.example.ecommerceapp.presentation.produto.ProdutoViewModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToDetailScreen: (Produto) -> Unit = {},
    produtoViewModel: ProdutoViewModel = viewModel(),
    navController: NavController,
    categoriaViewModel: CategoriaViewModel = viewModel()
) {

    val produtos = produtoViewModel.filteredList.observeAsState(emptyList())
    val produtosLoading = produtoViewModel.isLoading.observeAsState(false)
    val categorias = categoriaViewModel.categoriaList.observeAsState(emptyList())
    val categoriasLoading = categoriaViewModel.isLoading.observeAsState(false)
    val isGlobalLoading = produtosLoading.value || categoriasLoading.value

    val searchQuery by produtoViewModel.searchQuery
    val filteredProducts by produtoViewModel.filteredList.observeAsState(emptyList())

    LaunchedEffect(Unit) {
        categoriaViewModel.carregarCategorias()
        produtoViewModel.carregarProdutos()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {

        item {
            SearchField(
                query = produtoViewModel.searchQuery.value,
                onQueryChange = {
                    produtoViewModel.searchQuery.value = it
                    produtoViewModel.atualizarFiltro()
                }
            )
        }

        item {
            Text(
                text = "Categorias",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E293B),
                modifier = Modifier
                    .padding(bottom = 10.dp, start = 30.dp)
                    .fillMaxWidth()
            )
        }

        item {
            if (categoriasLoading.value) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            } else {
                LazyRow {
                    items(categorias.value) { categoria ->
                        CategoriaCard(
                            categoria = categoria,
                            modifier = Modifier,
                            onClick = {
                                navController.navigate(Destination.ProdutosPorCategoria(categoria.id))
                            }
                        )
                    }
                }
            }
        }

        item {

            Row(
                modifier = Modifier
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "\uD83D\uDD25 Destaques",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E293B),
                    modifier = Modifier
                        .padding(bottom = 10.dp, start = 30.dp)
                )

                Spacer(Modifier.weight(1f))

                Text(
                    text = "Ver todos",
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue,
                    modifier = Modifier
                        .padding(end = 16.dp)
                )
            }

            DestaqueCard()

        }

        item {

            Row(
                modifier = Modifier
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Produtos",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E293B),
                    modifier = Modifier
                        .padding(bottom = 10.dp, start = 30.dp)
                )

                Spacer(Modifier.weight(1f))

                Text(
                    text = "Filtrar",
                    fontWeight = FontWeight.Bold,
                    color = Color.Blue,
                    modifier = Modifier
                        .padding(end = 16.dp)
                )
            }
        }

        item {

            if (produtosLoading.value) {
                Box(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp)
                ) {

                    filteredProducts.chunked(2).forEach { rowItems ->

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {

                            rowItems.forEach { item ->
                                ProdutosCard(
                                    onClick = { navigateToDetailScreen(item) },
                                    produto = item,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(vertical = 8.dp)
                                )
                            }

                            // Se tiver número ímpar de produtos
                            if (rowItems.size == 1) {
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }

        }
    }
}


@Preview(showBackground = true)
@Composable
fun Prewview() {
//    HomeScreen()
}