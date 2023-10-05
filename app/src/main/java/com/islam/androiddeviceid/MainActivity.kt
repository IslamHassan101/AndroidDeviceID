package com.islam.androiddeviceid

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.islam.androiddeviceid.ui.theme.AndroidDeviceIDTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SetUI()
        }
    }
}


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun SetUI(viewModel: MainViewModel = hiltViewModel()) {
    viewModel.deviceInfo?.let {
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "brand :- ${it.deviceBrand}")
            Text(text = "name :- ${it.deviceName}")
            Text(text = "model :- ${it.deviceModel}")
            Text(text = "SoftwareId :- ${it.softwareId}")
        }
    }

}
