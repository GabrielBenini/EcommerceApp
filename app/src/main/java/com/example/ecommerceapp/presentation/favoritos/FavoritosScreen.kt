package com.example.ecommerceapp.presentation.favoritos


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.ecommerceapp.model.Produto
import com.example.ecommerceapp.presentation.usuario.UsuarioViewModel

@Composable
fun FavoritosScreen(
    navController: NavController,
    usuarioViewModel: UsuarioViewModel
) {
    var favoritos by remember { mutableStateOf<List<Produto>>(emptyList()) }

    LaunchedEffect(Unit) {
        val userId = usuarioViewModel.usuarioId.value ?: return@LaunchedEffect
        usuarioViewModel.firestore.collection("usuarios")
            .document(userId)
            .collection("favoritos")
            .get()
            .addOnSuccessListener { docs ->
                favoritos = docs.map { snap ->
                    Produto(
                        id = snap.getString("id") ?: "",
                        nome = snap.getString("nome") ?: "",
                        imagemUrl = snap.getString("imagemUrl") ?: "",
                        preco = snap.getDouble("preco") ?: 0.0,
                        descricao = ""
                    )
                }
            }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Favoritos",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (favoritos.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Nenhum produto favoritado.")
            }
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(favoritos) { produto ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 4.dp, vertical = 4.dp),
                        shape = RoundedCornerShape(12.dp),
                        border = BorderStroke(1.dp, Color.LightGray.copy(alpha = 0.4f)),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(produto.imagemUrl),
                                contentDescription = produto.nome,
                                modifier = Modifier.size(60.dp)
                            )
                            Spacer(Modifier.width(12.dp))
                            Column {
                                Text(produto.nome, fontWeight = FontWeight.SemiBold)
                                Text("R$ %.2f".format(produto.preco), color = Color.Gray)
                            }
                        }
                    }
                }
            }
        }
    }
}