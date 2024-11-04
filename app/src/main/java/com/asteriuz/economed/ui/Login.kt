package com.asteriuz.economed.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.asteriuz.economed.R
import com.asteriuz.economed.ui.theme.Normal
import com.asteriuz.economed.ui.theme.Primary
import com.asteriuz.economed.ui.theme.SubtleLight
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    navController: NavController,
    onLogin: () -> Unit = {},
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    var loginError by remember { mutableStateOf<String?>(null) }
    val scope = rememberCoroutineScope()
    val auth = FirebaseAuth.getInstance()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.login_img),
            contentDescription = "Icon of image placeholder",
        )
        Spacer(modifier = Modifier.height(48.dp))

        Text(
            text = "Login",
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
            value = email,
            onValueChange = { email = it },
            label = { Text("Email", style = MaterialTheme.typography.labelMedium) },
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
            value = password,
            onValueChange = { password = it },
            label = { Text("Senha", style = MaterialTheme.typography.labelMedium) },
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
            }
        )

        Text(
            text = "Esqueceu a senha?",
            style = MaterialTheme.typography.labelMedium,
            color = Color(0xFF7C7D81),
            modifier = Modifier
                .padding(vertical = 8.dp)
                .clickable { navController.navigate("forgotPassword") }
                .align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    scope.launch {
                        auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    loginError = null
                                    navController.navigate("AppContent") {
                                        popUpTo("home") {
                                            inclusive = true
                                        }
                                    }
                                } else {
                                    loginError = "Email ou senha incorretos"
                                }
                            }
                    }
                } else {
                    loginError = "Preencha todos os campos"
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Primary),
            shape = RoundedCornerShape(8.dp)
        ) {
            Text("Entrar", style = MaterialTheme.typography.labelLarge, color = Color.White)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    val navController = rememberNavController()
    LoginScreen(navController)
}