package com.islam.androiddeviceid.data

import android.annotation.SuppressLint
import android.app.admin.DevicePolicyManager
import android.content.Context
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import com.islam.androiddeviceid.DeviceInfo
import com.islam.androiddeviceid.Response
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class DeviceInfoModule @Inject constructor(@ApplicationContext private val context: Context) {


    @SuppressLint("HardwareIds")
    @RequiresApi(Build.VERSION_CODES.S)
    fun getDeviceInfo(): Flow<Response<DeviceInfo>> = flow {

        val response = try {
            val deviceModel = Build.MODEL
            val deviceBrand = Build.MANUFACTURER
            val deviceName = Build.DEVICE
            val softwareId: String =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
                } else {
                    val manager =
                        context.getSystemService(Context.DEVICE_POLICY_SERVICE) as DevicePolicyManager
                    manager.enrollmentSpecificId
                }

            val info = DeviceInfo(
                deviceModel = deviceModel,
                deviceBrand = deviceBrand,
                softwareId = softwareId,
                deviceName = deviceName
            )

            Response.Success(info)
        } catch (e: Exception) {
            Response.Error(e.message ?: "Unexpected error occurred")
        }

        emit(response)
    }


}