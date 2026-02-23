package com.example.ecommerceapp.presentation.perfil.meusdados

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.ecommerceapp.presentation.perfil.dadospessoais.MeusDadosViewModel
import com.example.ecommerceapp.ui.theme.BlueAgi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeusDadosScreen(
    navController: NavController,
    viewModel: MeusDadosViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    var modoEdicao by remember { mutableStateOf(false) }

    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.carregarDadosUsuario()
    }

    LaunchedEffect(state.nome, state.email, state.telefone) {
        nome = state.nome
        email = state.email
        telefone = state.telefone
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Meus Dados",
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Voltar",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(BlueAgi.value)
                )
            )
        },
        containerColor = Color(0xFFF8F8F8)
    ) { paddingValues ->

        when {
            state.carregando -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color(BlueAgi.value))
                }
            }

            state.erro != null -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentAlignment = Alignment.Center
                ) {
                    Text(state.erro ?: "Erro desconhecido", color = Color.Red)
                }
            }

            else -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                        .padding(paddingValues)
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(BlueAgi.value)),
                        elevation = CardDefaults.cardElevation(2.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(85.dp)
                                    .background(Color.White, shape = RoundedCornerShape(42.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = nome.take(2).uppercase(),
                                    fontSize = 30.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(BlueAgi.value)
                                )
                            }

                            Spacer(Modifier.height(12.dp))
                            Text(
                                text = nome,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White
                            )
                            Text(
                                text = email,
                                fontSize = 16.sp,
                                color = Color.White.copy(alpha = 0.9f)
                            )
                        }
                    }

                    Spacer(Modifier.height(24.dp))

                    if (modoEdicao) {
                        DadosTextField(Icons.Default.Person, "Nome Completo", nome) { nome = it }
                        DadosTextField(Icons.Default.Email, "E-mail", email) { email = it }
                        DadosTextField(
                            Icons.Default.Phone,
                            "Telefone",
                            telefone,
                            keyboardType = KeyboardType.Phone
                        ) { telefone = it }

                        DadosItem(Icons.Default.CalendarToday, "Data de Nascimento", state.dataNascimento)
                    } else {
                        DadosItem(Icons.Default.Person, "Nome Completo", nome)
                        DadosItem(Icons.Default.Email, "E-mail", email)
                        DadosItem(Icons.Default.Phone, "Telefone", telefone)
                        DadosItem(Icons.Default.CalendarToday, "Data de Nascimento", state.dataNascimento)
                    }

                    Spacer(Modifier.height(24.dp))

                    Button(
                        onClick = {
                            if (modoEdicao) {
                                viewModel.atualizarDadosUsuario(
                                    nome = nome,
                                    email = email,
                                    telefone = telefone
                                )
                            }
                            modoEdicao = !modoEdicao
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(BlueAgi.value)
                        ),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = if (modoEdicao) "Salvar Alterações" else "Editar Dados",
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp,
                            color = Color.White,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }

                    Spacer(Modifier.height(32.dp))
                }
            }
        }
    }
}


@Composable
private fun DadosItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = label, tint = Color(BlueAgi.value))
            Spacer(Modifier.width(16.dp))
            Column {
                Text(text = label, fontWeight = FontWeight.SemiBold, color = Color.Gray)
                Text(text = value, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }
        }
    }
}

@Composable
private fun DadosTextField(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    value: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    onValueChange: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = label, tint = Color(BlueAgi.value))
            Spacer(Modifier.width(16.dp))
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = label, fontWeight = FontWeight.SemiBold, color = Color.Gray)
                TextField(
                    value = value,
                    onValueChange = onValueChange,
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White,
                        focusedIndicatorColor = Color(BlueAgi.value),
                        unfocusedIndicatorColor = Color(0xFFD3D3D3),
                        cursorColor = Color(BlueAgi.value),
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Black
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(fontSize = 16.sp)
                )
            }
        }
    }
}