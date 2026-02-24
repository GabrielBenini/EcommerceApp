package com.example.ecommerceapp.presentation.recarga

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.InsertDriveFile
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ecommerceapp.presentation.components.BoletoDialogBottomSheet
import com.example.ecommerceapp.presentation.components.CartaoDialogBottomSheet
import com.example.ecommerceapp.presentation.components.MetodoPagamentoCard
import com.example.ecommerceapp.presentation.components.PixDialogBottomSheet
import com.example.ecommerceapp.presentation.components.ValorRecargaButtons
import com.example.ecommerceapp.presentation.components.ValorTotalRecargaCard
import com.example.ecommerceapp.presentation.usuario.UsuarioViewModel
import com.example.ecommerceapp.ui.theme.BlueAgi
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RecargaScreen(
    navController: NavHostController,
    usuarioViewModel: UsuarioViewModel,
    viewModel: RecargaViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val dialogMetodo by viewModel.dialogMetodo.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        containerColor = Color.White,
        bottomBar = {
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                onClick = {
                    val valor = state.valorPersonalizado.toDoubleOrNull() ?: 0.0
                    val metodo = state.metodoSelecionado ?: "Indefinido"

                    if (valor > 0 && state.metodoSelecionado != null) {
                        usuarioViewModel.adicionarSaldo(valor, metodo)

                        scope.launch {
                            snackbarHostState.showSnackbar("Recarga realizada com sucesso!")
                        }

                    } else {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "Informe o valor e selecione o método de pagamento."
                            )
                        }
                    }
                },
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(BlueAgi.value)
                )
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        tint = Color.White,
                        contentDescription = "Confirmar"
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "Confirmar Recarga",
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }
    ) { paddingValues ->

        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .fillMaxSize(),
            contentPadding = PaddingValues(vertical = 16.dp)
        ) {
            item {
                Text(
                    text = "Recarregar Saldo",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
            }

            item { ValorRecargaButtons() }

            item {
                Text(
                    modifier = Modifier.padding(start = 8.dp, top = 16.dp),
                    text = "Método de Pagamento",
                    fontWeight = FontWeight.SemiBold
                )
            }

            item {
                MetodoPagamentoCard(
                    tipoPagamento = "PIX",
                    descricaoPagamento = "Pagamento Instantâneo",
                    icone = Icons.Default.CreditCard,
                    selecionado = state.metodoSelecionado == "PIX",
                    onSelect = {
                        viewModel.handleEvent(
                            RecargaContract.Event.OnMetodoPagamentoSelecionado("PIX")
                        )
                    }
                )
            }

            item {
                MetodoPagamentoCard(
                    tipoPagamento = "Cartão de Crédito",
                    descricaoPagamento = "Visa, Master, Elo",
                    icone = Icons.Default.CreditCard,
                    selecionado = state.metodoSelecionado == "Cartão de Crédito",
                    onSelect = {
                        viewModel.handleEvent(
                            RecargaContract.Event.OnMetodoPagamentoSelecionado("Cartão de Crédito")
                        )
                    }
                )
            }

            item {
                MetodoPagamentoCard(
                    tipoPagamento = "Boleto",
                    descricaoPagamento = "Até 3 dias úteis",
                    icone = Icons.Default.InsertDriveFile,
                    selecionado = state.metodoSelecionado == "Boleto",
                    onSelect = {
                        viewModel.handleEvent(
                            RecargaContract.Event.OnMetodoPagamentoSelecionado("Boleto")
                        )
                    }
                )
            }

            item {
                ValorTotalRecargaCard(
                    titulo = "Valor da Recarga",
                    subtitulo = "Taxa",
                    total = "Novo Saldo",
                    subTotalValor = state.valorPersonalizado,
                    taxaServicoValor = "Grátis",
                    totalValor = state.valorPersonalizado
                )
            }
        }

        if (dialogMetodo != null) {
            ModalBottomSheet(
                onDismissRequest = { viewModel.fecharDialog() },
                sheetState = bottomSheetState,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            ) {
                when (dialogMetodo) {
                    "PIX" -> PixDialogBottomSheet(onDismiss = { viewModel.fecharDialog() })
                    "Cartão de Crédito" -> CartaoDialogBottomSheet(
                        onCancelar = { viewModel.fecharDialog() },
                        onConfirmar = { viewModel.fecharDialog() }
                    )
                    "Boleto" -> BoletoDialogBottomSheet(onDismiss = { viewModel.fecharDialog() })
                }
            }
        }
    }
}