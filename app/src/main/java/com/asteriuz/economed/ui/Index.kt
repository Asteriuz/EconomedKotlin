package com.asteriuz.economed.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.asteriuz.economed.R
import com.asteriuz.economed.ui.theme.Black
import com.asteriuz.economed.ui.theme.Normal
import com.asteriuz.economed.ui.theme.Primary
import com.asteriuz.economed.ui.theme.SubtleDark
import com.asteriuz.economed.ui.theme.White

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier
//                .fillMaxSize()
                .padding(start = 32.dp, end = 32.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(80.dp))

            Image(
                painter = painterResource(R.drawable.economed_logo),
                contentDescription = "Silhoutte of doctors",
            )

            Spacer(modifier = Modifier.height(40.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = buildAnnotatedString {
                        withStyle(style = SpanStyle(color = Normal)) {
                            append("Bem-vindo ao Econo")
                        }
                        withStyle(style = SpanStyle(color = Primary)) {
                            append("Med")
                        }
                        withStyle(
                            style = SpanStyle(
                                color = Primary,
                                baselineShift = BaselineShift.Superscript
                            )
                        ) {
                            append("+")
                        }
//                        withStyle(style = SpanStyle(color = Normal)) {
//                            append("!")
//                        }
                    },
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.W600,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Seu Sistema de Saúde Inteligente",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Normal
                )
            }

            Spacer(modifier = Modifier.height(48.dp))

            Button(
                onClick = { navController.navigate("registerOne") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Primary),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("REGISTRE-SE", style = MaterialTheme.typography.labelLarge, color = White)
            }

            Spacer(modifier = Modifier.height(24.dp))

            OutlinedButton(
                onClick = { navController.navigate("login") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                border = BorderStroke(1.dp, Primary),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("LOGIN", style = MaterialTheme.typography.labelLarge, color = Black)
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = SubtleDark)) {
                        append("Ao criar sua conta, você concorda com nossos ")
                    }
                    withStyle(style = SpanStyle(color = Primary)) {
                        append("Termos de Uso")
                    }
                    withStyle(style = SpanStyle(color = SubtleDark)) {
                        append(" e ")
                    }
                    withStyle(style = SpanStyle(color = Primary)) {
                        append("Política de Privacidade")
                    }
                },
                style = MaterialTheme.typography.labelSmall.copy(fontSize = 13.sp),
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            painter = painterResource(R.drawable.doctors_silhouette_clip_art),
            contentDescription = "Silhoutte of doctors",
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}