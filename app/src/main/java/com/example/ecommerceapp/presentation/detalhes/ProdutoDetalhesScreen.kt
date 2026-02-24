package com.example.ecommerceapp.presentation.detalhes

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.ecommerceapp.model.Produto
import com.example.ecommerceapp.navigation.Destination
import com.example.ecommerceapp.presentation.carrinho.CarrinhoViewModel
import com.example.ecommerceapp.presentation.detalhes.ProdutoDetalhesContract.Event.OnQtdProdutoChange
import com.example.ecommerceapp.presentation.usuario.UsuarioViewModel
import com.example.ecommerceapp.ui.theme.BlueAgi
import com.example.ecommerceapp.ui.theme.Verde

@Composable
fun ProdutoDetalhesScreen(
    navController: NavController,
    produto: Produto,
    modifier: Modifier = Modifier,
    carrinhoViewModel: CarrinhoViewModel,
    usuarioViewModel: UsuarioViewModel,
    navigateBack: () -> Boolean = { false },
    viewModel: ProdutoDetalhesViewModel = viewModel()
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    var isFavorito by remember { mutableStateOf(false) }

    LaunchedEffect(produto.id) {
        usuarioViewModel.verificarFavorito(produto.id) { isFav ->
            isFavorito = isFav
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {

        Surface(
            modifier = Modifier.height(250.dp),
            color = Color(0xFFE2EFFF)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                FilledIconButton(
                    onClick = { navigateBack() },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    )
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "Voltar"
                    )
                }

                Spacer(Modifier.weight(1f))

                FilledIconButton(
                    onClick = {
                        usuarioViewModel.alternarFavorito(
                            produto.id,
                            mapOf(
                                "id" to produto.id,
                                "nome" to produto.nome,
                                "imagemUrl" to produto.imagemUrl,
                                "preco" to produto.preco,
                                "descricao" to produto.descricao
                            )
                        )
                        isFavorito = !isFavorito
                    },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.White,
                        contentColor = if (isFavorito) Color.Red else Color.Black
                    )
                ) {
                    Icon(
                        imageVector = if (isFavorito) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favoritar"
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = produto.imagemUrl,
                    contentDescription = produto.nome,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(220.dp)
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 35.dp, horizontal = 16.dp)
        ) {
            Text(
                text = produto.nome,
                fontSize = 26.sp,
                fontWeight = FontWeight.SemiBold
            )

            Spacer(Modifier.weight(1f))

            Text(
                text = "R$ %.2f".format(produto.preco),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(BlueAgi.value)
            )
        }

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "• Em estoque",
            color = Color(Verde.value)
        )

        Card(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 35.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.LightGray.copy(alpha = 0.1f)
            ),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Descrição",
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = produto.descricao,
                    fontSize = 15.sp,
                    textAlign = TextAlign.Justify,
                    lineHeight = 22.sp
                )
            }
        }

        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = "Quantidade",
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )

        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    viewModel.handleEvent(
                        OnQtdProdutoChange(state.value.qtdProduto - 1)
                    )
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.Gray.copy(alpha = 0.1f)
                ),
                modifier = Modifier.size(50.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Remove,
                    contentDescription = "Remover"
                )
            }

            Text(
                text = "${state.value.qtdProduto}",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 22.dp)
            )

            IconButton(
                onClick = {
                    viewModel.handleEvent(
                        OnQtdProdutoChange(state.value.qtdProduto + 1)
                    )
                },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.Gray.copy(alpha = 0.1f)
                ),
                modifier = Modifier.size(50.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar"
                )
            }
        }

        HorizontalDivider(thickness = 1.dp)

        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 30.dp)
        ) {
            Text(
                text = "Total",
                fontSize = 24.sp,
                fontWeight = FontWeight.Normal
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = "R$ %.2f".format(state.value.qtdProduto * produto.preco),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Button(
            onClick = {
                carrinhoViewModel.adicionarProdutoComQuantidade(
                    produto = produto,
                    quantidade = state.value.qtdProduto
                )
                navController.navigate(Destination.Carrinho)
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(BlueAgi.value)
            )
        ) {
            Row(
                modifier = Modifier.padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Adicionar ao carrinho"
                )
                Text(
                    modifier = Modifier.padding(start = 9.dp),
                    text = "Adicionar ao Carrinho",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            }
        }

        Spacer(Modifier.height(24.dp))
    }
}