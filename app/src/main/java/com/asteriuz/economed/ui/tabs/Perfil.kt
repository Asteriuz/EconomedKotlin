package com.asteriuz.economed.ui.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.asteriuz.economed.R
import com.asteriuz.economed.ui.theme.EconomedTheme
import com.asteriuz.economed.ui.theme.Normal
import com.asteriuz.economed.ui.theme.Primary
import com.asteriuz.economed.ui.theme.SubtleDark
import com.google.firebase.auth.FirebaseAuth

@Composable
fun PerfilTabScreen(navController: NavController) {
    val auth = FirebaseAuth.getInstance()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(horizontal = 24.dp)
                .fillMaxSize()
                .background(Color.White)
        ) {
            Box(
                modifier = Modifier
                    .size(240.dp)
                    .background(Color(0xFFEFEFEF), RoundedCornerShape(8.dp))
                    .border(2.dp, Color(0xFFD0D0D0), RoundedCornerShape(8.dp)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_image),
                    contentDescription = "Icon of image placeholder",
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            Text(
                text = "Desculpas",
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                color = Normal,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Text(
                text = "Em manutenção",
                style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold),
                color = Normal,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            )
            {
                Text(
                    text = "Perdão, a página está em manutenção. Tente novamente mais tarde",
                    style = MaterialTheme.typography.labelLarge,
                    color = SubtleDark,
                    textAlign = TextAlign.Center,
                )
            }
            Spacer(modifier = Modifier.height(48.dp))

            Button(
                onClick = {
                    auth.signOut()
                    navController.navigate("home") {
                        popUpTo(0) { inclusive = true } // This clears the entire stack
                        launchSingleTop = true // Ensures only one instance of 'home' is launched
                        restoreState = true
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Primary),
                shape = RoundedCornerShape(10.dp),
                contentPadding = PaddingValues(12.dp)
            ) {
                Text(
                    text = "DESCONECTAR",
                    color = Color.White,
                    style = MaterialTheme.typography.headlineSmall
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewPerfilScreen() {
    EconomedTheme {
        val navController = rememberNavController()
        PerfilTabScreen(navController)
    }
}