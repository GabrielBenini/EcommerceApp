package com.example.ecommerceapp.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ecommerceapp.ui.theme.BlueAgi
import kotlinx.coroutines.launch

@Composable
fun PixDialogBottomSheet(onDismiss: () -> Unit) {
    val codigoPix = remember {
        "00020126360014BR.GOV.BCB.PIX0114+55819999999990214AGISTORE5204000053039865802BR5920AGISTORE LTDA6009SAOPAULO62140510ABC12345678903"
    }

    val clipboardManager = LocalClipboardManager.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Pague com PIX", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(Modifier.height(8.dp))
        Text("Código copia e cola:", fontWeight = FontWeight.SemiBold)
        Text(
            codigoPix,
            fontSize = 12.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        SnackbarHost(hostState = snackbarHostState)

        Button(
            onClick = {
                clipboardManager.setText(AnnotatedString(codigoPix))
                scope.launch {
                    snackbarHostState.showSnackbar(
                        "Código copiado com sucesso!",
                        duration = SnackbarDuration.Short
                    )
                }
            },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(BlueAgi.value),
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Copiar código PIX")
        }
        Spacer(Modifier.height(16.dp))
        TextButton(onClick = onDismiss) { Text("Fechar") }
    }
}

@Composable
fun CartaoDialogBottomSheet(
    onCancelar: () -> Unit,
    onConfirmar: (String) -> Unit
) {
    var numeroCartao by remember { mutableStateOf("") }
    var validadeCartao by remember { mutableStateOf("") }
    var cvv by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Cartão de Crédito", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = numeroCartao,
            onValueChange = { numeroCartao = it.filter { c -> c.isDigit() }.take(16) },
            label = { Text("Número do cartão") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = validadeCartao,
            onValueChange = {
                validadeCartao = it.filter { c -> c.isDigit() || c == '/' }.take(5)
            },
            label = { Text("Data de validade (MM/YY)") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = cvv,
            onValueChange = { cvv = it.filter { c -> c.isDigit() }.take(3) },
            label = { Text("CVV") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(10.dp))
        SnackbarHost(hostState = snackbarHostState)
        Spacer(Modifier.height(10.dp))

        Button(
            onClick = {
                when {
                    numeroCartao.length != 16 -> {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "Número do cartão deve ter 16 dígitos",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                    validadeCartao.length != 5 || !validadeCartao.contains("/") -> {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "Data inválida (use MM/YY)",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                    cvv.length != 3 -> {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "CVV deve ter 3 dígitos",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                    else -> {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                "Cartão aceito!",
                                duration = SnackbarDuration.Short
                            )
                        }
                        onConfirmar(numeroCartao)
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(BlueAgi.value),
                contentColor = Color.White
            )
        ) {
            Text("Cadastrar Cartão")
        }

        Spacer(Modifier.height(8.dp))
        TextButton(onClick = onCancelar) { Text("Cancelar") }
    }
}

@Composable
fun BoletoDialogBottomSheet(onDismiss: () -> Unit) {
    val codigoBoleto =
        "23793.38128 60000.014729 04000.000301 9 81920000049900"

    val clipboardManager = LocalClipboardManager.current
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Pagamento com Boleto", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(Modifier.height(8.dp))
        Text("Utilize o código abaixo para pagamento:")
        Spacer(Modifier.height(8.dp))
        Text(
            codigoBoleto,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        SnackbarHost(hostState = snackbarHostState)

        Button(
            onClick = {
                clipboardManager.setText(AnnotatedString(codigoBoleto))
                scope.launch {
                    snackbarHostState.showSnackbar(
                        "Código copiado com sucesso!",
                        duration = SnackbarDuration.Short
                    )
                }
            },
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(BlueAgi.value),
                contentColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Copiar código do boleto")
        }
        Spacer(Modifier.height(10.dp))
        TextButton(onClick = onDismiss) { Text("Fechar") }
    }
}