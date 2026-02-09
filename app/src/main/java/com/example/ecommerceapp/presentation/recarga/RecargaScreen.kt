package com.example.ecommerceapp.presentation.recarga

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.InsertDriveFile
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.ecommerceapp.presentation.components.AgiStoreHeader
import com.example.ecommerceapp.presentation.components.BottomBar
import com.example.ecommerceapp.presentation.components.MetodoPagamentoCard
import com.example.ecommerceapp.presentation.components.ValorRecargaButtons
import com.example.ecommerceapp.presentation.components.ValorTotalRecargaCard
import com.example.ecommerceapp.ui.theme.BlueAgi

//@Preview
@Composable
fun RecargaScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: RecargaViewModel = viewModel()
) {

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(modifier = Modifier.fillMaxSize()) {


        LazyColumn(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .weight(1f),
            contentPadding = PaddingValues(top = 0.dp)
        ) {
            item {
                Text(
                    text = "Recarregar Saldo",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }

            item { Text(text = "Escolha o valor para adicionar ao seu saldo") }

            item { ValorRecargaButtons() }

            item {
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "Ou digite um valor personalizado abaixo"
                )
            }

            item {
                OutlinedTextField(
                    maxLines = 1,
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.LightGray.copy(alpha = 0.3f),
                        unfocusedContainerColor = Color.LightGray.copy(alpha = 0.3f)
                    ),
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .fillMaxWidth(),
                    value = state.valorPersonalizado,
                    label = {Text(text = "Valor Personalizado")},
                    onValueChange = { viewModel.handleEvent(RecargaContract.Event.OnValorRecargaChange(valor = it)) }
                )
            }

            item {
                Text(
                    modifier = Modifier.padding(top = 16.dp),
                    text = "Método de Pagamento"
                )
            }

            item {
                MetodoPagamentoCard(
                    tipoPagamento = "PIX",
                    descricaoPagamento = "Pagamento Instantâneo",
                    icone = Icons.Default.CreditCard
                )
            }

            item {
                MetodoPagamentoCard(
                    tipoPagamento = "Cartão de Crédito",
                    descricaoPagamento = "Visa, Master, Elo",
                    icone = Icons.Default.CreditCard
                )
            }

            item {
                MetodoPagamentoCard(
                    tipoPagamento = "Boleto",
                    descricaoPagamento = "Até 3 dias úteis",
                    icone = Icons.Default.InsertDriveFile
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

            item {

                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    onClick = { TODO() },
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(BlueAgi.value)
                    )
                ) {

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(4.dp)
                    ) {

                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Ícone de cadeado",
                            tint = Color.White,
                            modifier = Modifier
                                .size(30.dp)
                                .padding(end = 8.dp)
                        )

                        Text(
                            text = "Confirmar Recarga",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )

                    }

                }

            }
        }
    }
}
