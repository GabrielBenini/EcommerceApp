package com.example.ecommerceapp.presentation.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import com.example.ecommerceapp.data.CategoriaData
import com.example.ecommerceapp.data.ProdutoData
import com.example.ecommerceapp.presentation.components.AgiStoreHeader
import com.example.ecommerceapp.presentation.components.BottomBar
import com.example.ecommerceapp.presentation.components.CategoriaCard
import com.example.ecommerceapp.presentation.components.DestaqueCard
import com.example.ecommerceapp.presentation.components.ProdutosCard
import com.example.ecommerceapp.presentation.components.SearchField


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    categoria: CategoriaData = CategoriaData,
    produto: ProdutoData = ProdutoData,
    navigateToDetailScreen: () -> Unit = {},
    navigateToCarrinho: () -> Unit = {}
) {


    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
             BottomBar(onClickCarrinho = navigateToCarrinho)
        }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()

        ) {
            AgiStoreHeader()

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                item {
                    SearchField()
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

                    LazyRow(
                        modifier = Modifier
                            .padding(start = 16.dp)
                    ) {

                        items(categoria.categoriaList) { item ->
                            CategoriaCard(
                                categoria = item,
                                modifier = Modifier
                            )

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

                    LazyVerticalStaggeredGrid(
                        modifier = Modifier.height(600.dp),
                        columns = StaggeredGridCells.Fixed(2)
                    ) {

                        items(produto.productList) { item ->

                            ProdutosCard(
                                onClick = {
                                    // Navegar para a tela de detalhes do produto
                                    navigateToDetailScreen()
                                },
                                produto = item,
                                modifier = Modifier
                                    .padding(horizontal = 12.dp, vertical = 8.dp)
                            )

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
    HomeScreen()
}