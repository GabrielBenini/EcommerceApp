package com.example.ecommerceapp.presentation.carrinho

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ecommerceapp.presentation.components.CarrinhoCard
import com.example.ecommerceapp.presentation.components.ValorTotalCard
import com.example.ecommerceapp.presentation.produto.ProdutoViewModel
import com.example.ecommerceapp.presentation.usuario.UsuarioViewModel
import com.example.ecommerceapp.ui.theme.BlueAgi

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CarrinhoScreen(
    navController: NavHostController,
    carrinhoViewModel: CarrinhoViewModel,
    usuarioViewModel: UsuarioViewModel,
    produtoViewModel: ProdutoViewModel,
    modifier: Modifier = Modifier
) {
    val itensCarrinho by carrinhoViewModel.itensCarrinho.observeAsState(emptyList())
    val subtotal by carrinhoViewModel.subtotal.observeAsState(0.0)
    val mensagem by carrinhoViewModel.mensagemCompra.observeAsState()
    val todosProdutos by produtoViewModel.produtoList.observeAsState(emptyList())

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        produtoViewModel.carregarProdutos()
    }

    LaunchedEffect(mensagem) {
        mensagem?.let { texto ->
            snackbarHostState.showSnackbar(
                message = texto,
                duration = SnackbarDuration.Short
            )
            carrinhoViewModel.limparMensagem()
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {

            item {
                Text(
                    text = "Meu Carrinho",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E293B),
                    modifier = Modifier
                        .padding(bottom = 70.dp, start = 30.dp, end = 30.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            }

            item {
                if (itensCarrinho.isEmpty()) {
                    Text(
                        text = "Seu carrinho está vazio 🛒",
                        color = Color.Gray,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp, horizontal = 16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }

            items(itensCarrinho) { item ->
                val produtoCompleto = todosProdutos.find { it.id == item.produtoId }

                if (produtoCompleto != null) {
                    CarrinhoCard(
                        produto = produtoCompleto,
                        qtdeProduto = item.quantidade.toString(),
                        onAdd = {
                            carrinhoViewModel.aumentarQuantidade(item)
                        },
                        onRemove = {
                            carrinhoViewModel.diminuirQuantidade(item)
                        },
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }
            }

            if (itensCarrinho.isNotEmpty()) {
                item {
                    Column(modifier = Modifier.padding(16.dp)) {
                        ValorTotalCard(
                            titulo = "Subtotal",
                            subtitulo = "Taxa de Serviço",
                            total = "Total",
                            subTotalValor = "%.2f".format(subtotal),
                            taxaServicoValor = "10,00",
                            totalValor = "%.2f".format(subtotal + 10.0)
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }

                item {
                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        onClick = {
                            carrinhoViewModel.confirmarCompra()
                        },
                        shape = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(BlueAgi.value)
                        )
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Lock,
                                contentDescription = "Ícone cadeado",
                                tint = Color.White,
                                modifier = Modifier
                                    .size(30.dp)
                                    .padding(end = 8.dp)
                            )
                            Text(
                                text = "Comprar",
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}