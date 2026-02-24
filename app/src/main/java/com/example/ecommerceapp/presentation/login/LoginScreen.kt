package com.example.ecommerceapp.presentation.login

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ecommerceapp.R
import com.example.ecommerceapp.navigation.Destination
import com.example.ecommerceapp.ui.theme.BlueAgi
import com.example.ecommerceapp.presentation.login.LoginContract.Event.OnEmailChange
import com.example.ecommerceapp.presentation.login.LoginContract.Event.OnPasswordChange
import com.example.ecommerceapp.presentation.login.LoginContract.Effect
import com.example.ecommerceapp.presentation.usuario.UsuarioViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    usuarioViewModel: UsuarioViewModel,
    viewModel: LoginViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->

        Column(
            modifier = modifier
                .padding(horizontal = 24.dp)
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 46.dp, bottom = 32.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logoapp),
                    contentDescription = "Logo AgiStore",
                    modifier = Modifier
                        .size(160.dp)
                        .shadow(elevation = 4.dp, shape = RoundedCornerShape(8.dp))
                )
            }

            OutlinedTextField(
                value = state.email,
                singleLine = true,
                maxLines = 1,
                onValueChange = { viewModel.handleEvent(OnEmailChange(it)) },
                label = { Text(text = "Email") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            var senhaVisivel by remember { mutableStateOf(false) }

            OutlinedTextField(
                value = state.password,
                onValueChange = { viewModel.handleEvent(OnPasswordChange(it)) },
                label = { Text(text = "Senha") },
                singleLine = true,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password
                ),
                visualTransformation = if (senhaVisivel) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val icone = if (senhaVisivel) Icons.Default.VisibilityOff else Icons.Default.Visibility
                    val descricao = if (senhaVisivel) "Ocultar senha" else "Mostrar senha"
                    IconButton(onClick = { senhaVisivel = !senhaVisivel }) {
                        Icon(icone, contentDescription = descricao)
                    }
                },
                modifier = Modifier.fillMaxWidth()
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Esqueceu a senha?",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(BlueAgi.value)
                )
            }

            Button(
                modifier = Modifier
                    .padding(bottom = 44.dp)
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(BlueAgi.value),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(12.dp),
                onClick = {
                    viewModel.login()
                }
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = "Entrar"
                )
            }

            HorizontalDivider(thickness = 1.dp)

            Card(
                border = BorderStroke(1.dp, Color.LightGray),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .padding(top = 44.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.padding(end = 8.dp),
                        imageVector = Icons.Default.Search,
                        contentDescription = "Entrar com Google"
                    )
                    Text(
                        text = "Continuar com Google",
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Card(
                border = BorderStroke(1.dp, Color.LightGray),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.padding(end = 8.dp),
                        imageVector = Icons.Default.Login,
                        contentDescription = "Entrar com Apple"
                    )
                    Text(
                        text = "Continuar com Apple",
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Row(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(text = "Não tem uma conta?")
                Text(
                    text = " Criar agora ",
                    fontWeight = FontWeight.SemiBold,
                    color = Color(BlueAgi.value),
                    modifier = Modifier.clickable(
                        onClick = { navController.navigate(Destination.Cadastro) }
                    )
                )
            }
        }

        LaunchedEffect(Unit) {
            viewModel.uiEffect.collect { effect ->
                when (effect) {
                    is Effect.ShowLoginSuccess -> {
                        val email = state.email
                        val nome = email.substringBefore("@").replaceFirstChar { it.uppercaseChar() }

                        usuarioViewModel.fazerLogin(
                            userId = "user_${email.substringBefore("@")}",
                            nome = nome,
                            email = email,
                            saldoInicial = 0.0
                        )

                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Bem-vindo(a), $nome!",
                                duration = SnackbarDuration.Short
                            )
                        }

                        navController.navigate(Destination.Home) {
                            popUpTo(Destination.Login) { inclusive = true }
                        }
                    }

                    is Effect.ShowLoginError -> {
                        scope.launch {
                            snackbarHostState.showSnackbar(
                                message = effect.message.ifBlank { "Erro ao realizar login" },
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                }
            }
        }
    }
}