package com.example.myapplication.ui.login

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.myapplication.R
import com.example.myapplication.ui.components.M3TextField
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.imePadding
import com.google.accompanist.insets.systemBarsPadding
import androidx.compose.material.Text as M2Text

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MyApplicationTheme {
                ProvideWindowInsets {
                    LoginContent()
                }
            }
        }
    }
}

@Composable
fun LoginContent() {
    val scrollState = rememberScrollState()

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .systemBarsPadding()
                .imePadding()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(R.string.welcome_msg, stringResource(R.string.app_name)),
                style = MaterialTheme.typography.headlineMedium,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = stringResource(R.string.login_to_continue),
                style = MaterialTheme.typography.titleSmall
            )

            Spacer(modifier = Modifier.height(24.dp))

            M3TextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                label = { M2Text(text = stringResource(R.string.email)) },
                onValueChange = {}
            )

            Spacer(modifier = Modifier.height(16.dp))

            M3TextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                label = { M2Text(text = stringResource(R.string.password)) },
                onValueChange = {}
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(modifier = Modifier.fillMaxWidth(), onClick = { /*TODO*/ }) {
                Text(text = stringResource(R.string.log_in))
            }

            Spacer(modifier = Modifier.height(8.dp))

            TextButton(onClick = { /*TODO*/ }) {
                Text(text = stringResource(R.string.forgot_my_password))
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun LoginPreview() {
    MyApplicationTheme {
        LoginContent()
    }
}

@Composable
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
fun LoginPreviewNight() {
    MyApplicationTheme {
        LoginContent()
    }
}