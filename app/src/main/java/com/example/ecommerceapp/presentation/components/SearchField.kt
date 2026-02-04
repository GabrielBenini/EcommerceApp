package com.example.ecommerceapp.presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ecommerceapp.presentation.home.HomeViewModel



//@Preview
@Composable
fun SearchField(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel()
) {

    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    Row(
        modifier = Modifier
    ) {


        OutlinedTextField(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth(),

            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF0066CC),
                unfocusedBorderColor = Color(0xFFE2E8F0),
                focusedContainerColor = Color(0xFFF1F5F9),
                unfocusedContainerColor = Color(0xFFF1F5F9),
                cursorColor = Color(0xFF0066CC),
                focusedTextColor = Color(0xFF1E293B),
                unfocusedTextColor = Color(0xFF64748B),
                focusedPlaceholderColor = Color(0xFF94A3B8),
                unfocusedPlaceholderColor = Color(0xFF94A3B8),
                focusedLeadingIconColor = Color(0xFF0066CC),
                unfocusedLeadingIconColor = Color(0xFF94A3B8),
                // Adicione essas linhas:
                errorBorderColor = Color.Transparent,
                errorContainerColor = Color(0xFFF1F5F9),
                errorCursorColor = Color(0xFF0066CC)
            ),

            singleLine = true,
            shape = RoundedCornerShape(20.dp),
            value = uiState.value.searchProduct,
            onValueChange = { viewModel.updateSearchProduct(it) },
            label = { Text(text = "Buscar Produtos: ") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Ícone de busca"
                )
            }
        )

    }
}

