package com.asteriuz.economed.ui.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.asteriuz.economed.R
import com.asteriuz.economed.ui.theme.Normal
import com.asteriuz.economed.ui.theme.Primary
import com.asteriuz.economed.ui.theme.SubtleDark
import com.asteriuz.economed.ui.theme.SubtleLight
import com.google.firebase.auth.FirebaseAuth

@Composable
fun RegisterTwoScreen(
    navController: NavController, onRegister: () -> Unit = {}
) {
    val context = LocalContext.current
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var loginError by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Image(
            painter = painterResource(R.drawable.register_one_img),
            contentDescription = "Icon of image placeholder",
        )
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = "Registre-se",
            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.SemiBold),
            color = Normal,
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (loginError != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(
                        Color(0xFFFFCDD2),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(12.dp)
            ) {
                Text(
                    text = loginError!!,
                    color = Color(0xFFD32F2F),
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            shape = RoundedCornerShape(8.dp),
            value = nome,
            onValueChange = { nome = it },
            label = { Text("Nome", style = MaterialTheme.typography.labelLarge) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Primary,
                unfocusedBorderColor = SubtleLight,
                focusedLabelColor = Normal,
            )
        )

        OutlinedTextField(
            shape = RoundedCornerShape(8.dp),
            value = email,
            onValueChange = { email = it },
            label = { Text("Email", style = MaterialTheme.typography.labelLarge) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Primary,
                unfocusedBorderColor = SubtleLight,
                focusedLabelColor = Normal,
                unfocusedLabelColor = SubtleDark,
            )
        )

        OutlinedTextField(shape = RoundedCornerShape(8.dp),
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha", style = MaterialTheme.typography.labelLarge) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Primary,
                unfocusedBorderColor = SubtleLight,
                focusedLabelColor = Normal,
            ),
            trailingIcon = {
                IconButton(
                    onClick = { showPassword = !showPassword },
                ) {
                    Icon(
                        painter = if (showPassword) painterResource(R.drawable.ic_show_eye) else painterResource(
                            R.drawable.ic_hide_eye
                        ),
                        contentDescription = "Icon of Show Password",
                        tint = SubtleLight,
                    )
                }
            })

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                navController.navigate("AppContent") {
                                    popUpTo("home") {
                                        inclusive = true
                                    }
                                }
                            } else {
                                loginError = "Erro ao criar conta"
                            }
                        }
                } else {
                    loginError = "Preencha todos os campos"
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Primary),
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 14.dp)
        ) {
            Text("Próximo", style = MaterialTheme.typography.headlineSmall, color = Color.White)
            Icon(
                painter = painterResource(R.drawable.ic_arrow_right),
                contentDescription = "Icon of Show Password",
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrarTwoPreview() {
    val navController = NavController(LocalContext.current)
    RegisterTwoScreen(navController)
}
