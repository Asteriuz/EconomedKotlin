package com.asteriuz.economed.ui

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.asteriuz.economed.ui.theme.Normal
import com.asteriuz.economed.ui.theme.Primary
import com.asteriuz.economed.ui.theme.SubtleDark
import com.asteriuz.economed.ui.theme.SubtleLight
import com.google.firebase.auth.FirebaseAuth
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.delay

@Composable
fun ForgotPasswordScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var resetMessage by remember { mutableStateOf<String?>(null) }
    var showMessageOnly by remember { mutableStateOf(false) }

    if (showMessageOnly) {
        LaunchedEffect(Unit) {
            delay(2000) // 2-second delay
            navController.navigate("login")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Recuperar Senha",
            style = MaterialTheme.typography.displaySmall.copy(
                fontWeight = FontWeight.SemiBold,
                fontSize = 36.sp
            ),
            color = Normal,
        )

        Spacer(modifier = Modifier.height(16.dp))

        if (resetMessage != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .background(
                        Color(0xFFFFF3CD), // Background color for warning/info message
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(12.dp)
            ) {
                Text(
                    text = resetMessage!!,
                    color = Color(0xFF856404), // Text color for warning/info message
                    style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

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

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                if (email.isNotEmpty()) {
                    FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                resetMessage = "Email de recuperação de senha enviado."
                                showMessageOnly = true
                            } else {
                                resetMessage = "Este email não está cadastrado."
                            }
                        }
                } else {
                    resetMessage = "Preencha o campo de email."
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Primary),
            shape = RoundedCornerShape(12.dp),
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 14.dp)
        ) {
            Text("Enviar", style = MaterialTheme.typography.headlineSmall, color = Color.White)
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ForgotPasswordPreview() {
    val navController = rememberNavController()
    ForgotPasswordScreen(navController)
}