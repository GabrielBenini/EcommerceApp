package com.example.ecommerceapp.presentation.carrinho

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.ecommerceapp.model.Produto
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

    val saldoUsuario by usuarioViewModel.saldo.observeAsState(0.0)
    val itensCarrinho by carrinhoViewModel.itensCarrinho.observeAsState(emptyList())
    val subtotal by carrinhoViewModel.subtotal.observeAsState(0.0)
    val mensagem by carrinhoViewModel.mensagemCompra.observeAsState()
    val todosProdutos by produtoViewModel.produtoList.observeAsState(emptyList())

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        produtoViewModel.carregarProdutos()
    }

    mensagem?.let { texto ->
        LaunchedEffect(texto) {
            snackbarHostState.showSnackbar(texto)
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { paddingValues ->
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {

            // TÍTULO
            item {
                Text(
                    text = "Meu Carrinho",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E293B),
                    modifier = Modifier
                        .padding(vertical = 30.dp, horizontal = 30.dp)
                        .fillMaxWidth()
                )
            }

            // MENSAGEM DE CARRINHO VAZIO
            item {
                if (itensCarrinho.isEmpty()) {
                    Text(
                        text = "Seu carrinho está vazio 🛒",
                        color = Color.Gray,
                        modifier = Modifier.padding(vertical = 20.dp, horizontal = 16.dp)
                    )
                }
            }

            // ✅ LISTA DE PRODUTOS NO CARRINHO
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

            // RESUMO DO PEDIDO
            if (itensCarrinho.isNotEmpty()) {
                item {
                    Column(modifier = Modifier.padding(16.dp)) {
                        ValorTotalCard(
                            titulo = "Subtotal",
                            subtitulo = "Taxa de Serviço",
                            total = "Total",
                            subTotalValor = "R$ %.2f".format(subtotal),
                            taxaServicoValor = "R$ 10,00",
                            totalValor = "R$ %.2f".format(subtotal + 10.0)
                        )

                        Spacer(modifier = Modifier.height(10.dp))
                    }
                }

                // BOTÃO CONFIRMAR COMPRA
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
                                text = "Confirmar Compra",
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