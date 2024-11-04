package com.asteriuz.economed.ui.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.asteriuz.economed.R
import com.asteriuz.economed.ui.theme.Normal
import com.asteriuz.economed.ui.theme.Primary
import com.asteriuz.economed.ui.theme.SubtleDark
import com.asteriuz.economed.ui.theme.SubtleLight
import com.asteriuz.economed.ui.utils.CepVisualTransformation
import com.asteriuz.economed.ui.utils.CpfVisualTransformation
import kotlinx.coroutines.launch

@Composable
fun RegisterOneScreen(
    navController: NavController,
    onRegister: () -> Unit = {}
) {
    var cep by remember { mutableStateOf("") }
    var logradouro by remember { mutableStateOf("") }
    var numero by remember { mutableStateOf("") }
    var cidade by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var checked by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = cep) {
        if (lengthConsideringOnlyNumbers(cep) == 11) {
            coroutineScope.launch {
                try {
                } catch (e: Exception) {
                    println("Error fetching CEP: ${e.message}")
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        Image(
            painter = painterResource(R.drawable.register_two_img),
            contentDescription = "Icon of image placeholder",
        )
        Spacer(modifier = Modifier.height(40.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = cep,
                onValueChange = { cep = it }, // Assuming you have formatCEP function
                label = { Text("CEP", style = MaterialTheme.typography.labelLarge) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Primary,
                    unfocusedBorderColor = SubtleLight,
                    focusedLabelColor = Normal,
                    unfocusedLabelColor = SubtleDark,
                ),
                visualTransformation = CepVisualTransformation()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = logradouro,
                    onValueChange = { logradouro = it },
                    label = { Text("Logradouro", style = MaterialTheme.typography.labelLarge) },
                    modifier = Modifier
                        .width(240.dp)
                        .padding(vertical = 8.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Primary,
                        unfocusedBorderColor = SubtleLight,
                        focusedLabelColor = Normal,
                        unfocusedLabelColor = SubtleDark,
                    )
                )
                OutlinedTextField(
                    value = numero,
                    onValueChange = { numero = it },
                    label = { Text("Nº", style = MaterialTheme.typography.labelLarge) },
                    modifier = Modifier
                        .width(72.dp)
                        .padding(vertical = 8.dp),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Primary,
                        unfocusedBorderColor = SubtleLight,
                        focusedLabelColor = Normal,
                        unfocusedLabelColor = SubtleDark,
                    )
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OutlinedTextField(
                    value = cidade,
                    onValueChange = { cidade = it },
                    label = { Text("Cidade", style = MaterialTheme.typography.labelLarge) },
                    modifier = Modifier
                        .width(208.dp)
                        .padding(vertical = 8.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Primary,
                        unfocusedBorderColor = SubtleLight,
                        focusedLabelColor = Normal,
                        unfocusedLabelColor = SubtleDark,
                    )
                )
                OutlinedTextField(
                    value = estado,
                    onValueChange = {
                        if (it.length <= 2 && it.all { char -> char.isLetter() }) {
                            estado = it
                        }
                    },
                    label = { Text("Estado", style = MaterialTheme.typography.labelLarge) },
                    modifier = Modifier
                        .width(104.dp)
                        .padding(vertical = 8.dp),
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Primary,
                        unfocusedBorderColor = SubtleLight,
                        focusedLabelColor = Normal,
                        unfocusedLabelColor = SubtleDark,
                    )
                )
            }

            OutlinedTextField(
                value = cpf,
                onValueChange = { cpf = it },
                label = { Text("CPF", style = MaterialTheme.typography.labelLarge) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Primary,
                    unfocusedBorderColor = SubtleLight,
                    focusedLabelColor = Normal,
                    unfocusedLabelColor = SubtleDark,
                ),
                visualTransformation = CpfVisualTransformation()
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { checked = it },
                colors = CheckboxDefaults.colors(checkedColor = Primary, uncheckedColor = Primary)
            )
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = SubtleDark, fontSize = 13.sp)) {
                        append("Concordo com os ")
                    }
                    withStyle(style = SpanStyle(color = Primary, fontSize = 13.sp)) {
                        append("Termos de Uso")
                    }
                    withStyle(style = SpanStyle(color = SubtleDark, fontSize = 13.sp)) {
                        append(" e ")
                    }
                    withStyle(style = SpanStyle(color = Primary, fontSize = 13.sp)) {
                        append("Política de Privacidade")
                    }
                },
                lineHeight = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = { navController.navigate("registerTwo") },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Primary),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Próximo", style = MaterialTheme.typography.labelLarge, color = Color.White)
        }
    }
}


fun lengthConsideringOnlyNumbers(input: String): Int {
    return input.filter { it.isDigit() }.length
}


@Preview(showBackground = true)
@Composable
fun RegisterOneScreenPreview() {
    val navController = rememberNavController()
    RegisterOneScreen(navController)
}