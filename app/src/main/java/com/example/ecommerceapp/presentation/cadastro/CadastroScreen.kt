package com.example.ecommerceapp.presentation.cadastro

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.navigation.NavController
import com.example.ecommerceapp.navigation.Destination
import com.example.ecommerceapp.presentation.components.CadastroTextField
import com.example.ecommerceapp.presentation.usuario.UsuarioViewModel
import com.example.ecommerceapp.ui.theme.BlueAgi
import com.example.ecommerceapp.presentation.cadastro.CadastroContract.Event.*
import com.example.ecommerceapp.presentation.cadastro.CadastroContract.Effect.*
import kotlinx.coroutines.launch

@Composable
fun CadastroScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    usuarioViewModel: UsuarioViewModel,
    viewModel: CadastroViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    var mostrarDialogBoasVindas by remember { mutableStateOf(false) }
    var nomeUsuario by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var aceitaTermos by remember { mutableStateOf(false) }
    var desejaOfertas by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .padding(horizontal = 24.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SnackbarHost(hostState = snackbarHostState)

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Criar Conta",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 24.dp)
            )
        }

        Text(
            modifier = Modifier.padding(bottom = 24.dp),
            text = "Junte-se à AgiStore em segundos"
        )

        CadastroTextField(
            value = state.nomeCompleto,
            onValueChange = { viewModel.handleEvent(OnNomeCompletoChange(it)) },
            label = "Nome Completo"
        )

        CadastroTextField(
            value = state.email,
            onValueChange = { viewModel.handleEvent(OnEmailChange(it)) },
            label = "Email"
        )

        CadastroTextField(
            value = state.telefone,
            onValueChange = { viewModel.handleEvent(OnTelefoneChange(it)) },
            label = "Telefone"
        )

        CadastroTextField(
            value = state.dataNascimento,
            onValueChange = { viewModel.handleEvent(OnDataNascimentoChange(it)) },
            label = "Data de Nascimento"
        )

        CadastroTextField(
            value = state.senha,
            onValueChange = { viewModel.handleEvent(OnSenhaChange(it)) },
            label = "Senha"
        )

        CadastroTextField(
            value = state.confirmacaoSenha,
            onValueChange = { viewModel.handleEvent(OnConfirmacaoSenhaChange(it)) },
            label = "Confirmar Senha"
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { aceitaTermos = !aceitaTermos }
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = aceitaTermos,
                onCheckedChange = { aceitaTermos = it },
                colors = CheckboxDefaults.colors(checkedColor = Color(BlueAgi.value))
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = "Concordo com os Termos de Serviço e Política de Privacidade"
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { desejaOfertas = !desejaOfertas }
                .padding(vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = desejaOfertas,
                onCheckedChange = { desejaOfertas = it },
                colors = CheckboxDefaults.colors(checkedColor = Color(BlueAgi.value))
            )
            Spacer(Modifier.width(4.dp))
            Text(
                text = "Quero receber ofertas e novidades da AgiStore"
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            modifier = Modifier
                .padding(vertical = 24.dp)
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(BlueAgi.value),
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            onClick = {
                if (!aceitaTermos) {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = "Você precisa concordar com os Termos de Serviço para continuar.",
                            duration = SnackbarDuration.Short
                        )
                    }
                } else {
                    viewModel.cadastro()
                }
            }
        ) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp),
                text = "Criar Minha Conta"
            )
        }

        Row(
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Já tem uma conta?")
            Text(
                text = " Faça Login",
                fontWeight = FontWeight.SemiBold,
                color = Color(BlueAgi.value),
                modifier = Modifier.clickable {
                    navController.navigate(Destination.Login)
                }
            )
        }
    }

    if (mostrarDialogBoasVindas) {
        AlertDialog(
            onDismissRequest = { },
            title = {
                Text(
                    text = "🎉 Bem-vindo à AgiStore!",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            text = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Olá, $nomeUsuario! 👋",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "Sua conta foi criada com sucesso!",
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        mostrarDialogBoasVindas = false
                        navController.navigate(Destination.Home) {
                            popUpTo(Destination.Cadastro) { inclusive = true }
                            popUpTo(Destination.Login) { inclusive = true }
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(BlueAgi.value)
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Text(
                        text = "Começar a Comprar 🛒",
                        modifier = Modifier.padding(vertical = 8.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            },
            shape = RoundedCornerShape(20.dp)
        )
    }

    LaunchedEffect(Unit) {
        viewModel.uiEffect.collect { effect ->
            when (effect) {
                is ShowCadastroSuccess -> {
                    val email = state.email
                    val nome = state.nomeCompleto
                    nomeUsuario = nome.ifEmpty { email.substringBefore("@") }

                    val firebaseUserId = com.google.firebase.auth.FirebaseAuth.getInstance().currentUser?.uid ?: return@collect

                    usuarioViewModel.fazerLogin(
                        userId = firebaseUserId,
                        nome = nomeUsuario,
                        email = email,
                        saldoInicial = 0.0
                    )

                    mostrarDialogBoasVindas = true
                }

                is ShowCadastroError -> {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = effect.message,
                            duration = SnackbarDuration.Short,
                            withDismissAction = true
                        )
                    }
                }
            }
        }
    }
}