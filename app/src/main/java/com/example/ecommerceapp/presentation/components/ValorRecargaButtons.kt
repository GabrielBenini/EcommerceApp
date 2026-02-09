package com.example.ecommerceapp.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ecommerceapp.presentation.recarga.RecargaContract
import com.example.ecommerceapp.presentation.recarga.RecargaViewModel

@Preview
@Composable
fun ValorRecargaButtons(
    modifier: Modifier = Modifier,
    viewModel: RecargaViewModel = viewModel()
){

    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Row(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        Column() {

            Button(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .height(50.dp)
                    .width(100.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray.copy(alpha = 0.3f),
                    contentColor = Color.Black
                ),
                onClick = { viewModel.handleEvent(RecargaContract.Event.OnValorFixoSelecionado(valorFixo = "20")) }
            ) {

                Text(
                    text = "R$ 20",
                    fontSize = 16.sp
                )

            }

            Button(
                modifier = Modifier
                    .height(50.dp)
                    .width(100.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray.copy(alpha = 0.3f),
                    contentColor = Color.Black
                ),
                onClick = { TODO() }
            ) {

                Text(
                    text = "R$ 50",
                    fontSize = 16.sp
                )

            }

        }

        Column() {

            Button(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .height(50.dp)
                    .width(100.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray.copy(alpha = 0.3f),
                    contentColor = Color.Black
                ),
                onClick = { TODO() }
            ) {

                Text(
                    text = "R$ 100",
                    fontSize = 16.sp
                )

            }

            Button(
                modifier = Modifier
                    .height(50.dp)
                    .width(100.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray.copy(alpha = 0.3f),
                    contentColor = Color.Black
                ),
                onClick = { TODO() }
            ) {

                Text(
                    text = "R$ 150",
                    fontSize = 16.sp
                )

            }

        }

        Column() {

            Button(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .height(50.dp)
                    .width(100.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray.copy(alpha = 0.3f),
                    contentColor = Color.Black
                ),
                onClick = { TODO() }
            ) {

                Text(
                    text = "R$ 200",
                    fontSize = 16.sp
                )

            }

            Button(
                modifier = Modifier
                    .height(50.dp)
                    .width(100.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.LightGray.copy(alpha = 0.3f),
                    contentColor = Color.Black
                ),
                onClick = { TODO() }
            ) {

                Text(
                    text = "Outro",
                    fontSize = 16.sp
                )

            }
        }
    }


}