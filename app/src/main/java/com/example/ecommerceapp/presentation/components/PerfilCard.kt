package com.example.ecommerceapp.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun PerfilCard(
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    iconColor: Color? = null,
    text: String = "",
    iconBackgroundColor: Color? = null

) {

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {


        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {

            if (iconBackgroundColor != null) {
                Surface(
                    modifier = Modifier
                        .padding(16.dp)
                        .clip(CircleShape)
                        .size(50.dp),
                    color = iconBackgroundColor

                ) {

                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        if (icon != null) {
                            if (iconColor != null) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = "Icon de Perfil",
                                    tint = iconColor,
                                    modifier = Modifier
                                )
                            }
                        }
                    }
                }
            }

            Text(
                text = text,
                fontSize = 16.sp
            )

            Spacer(Modifier.weight(1f))

            Icon(
                imageVector = Icons.Default.ArrowForwardIos,
                contentDescription = "Seta para direita",
                tint = Color.Gray,
                modifier = Modifier
                    .padding(end = 8.dp)
            )

        }
    }
}