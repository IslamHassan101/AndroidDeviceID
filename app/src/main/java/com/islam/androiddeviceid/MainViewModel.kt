package com.islam.androiddeviceid

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.islam.androiddeviceid.data.DeviceInfoModule
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@RequiresApi(Build.VERSION_CODES.S)
@HiltViewModel
class MainViewModel @Inject constructor(deviceInfoModule: DeviceInfoModule) : ViewModel() {

    var deviceInfo by mutableStateOf<DeviceInfo?>(null)

    init {
        viewModelScope.launch {
            deviceInfoModule.getDeviceInfo().collect {
                when (it) {
                    is Response.Error -> Log.d("home", "Error:${it.error}")
                    is Response.Success -> {
                        deviceInfo = it.data
                    }
                }
            }
        }
    }

}