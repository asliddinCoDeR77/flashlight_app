package com.example.flashlight_app

import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import android.hardware.camera2.CameraManager
import android.content.Context

class MainActivity: FlutterActivity() {
    private val CHANNEL = "com.example.flashlight_app/flashlight"

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, CHANNEL).setMethodCallHandler { call, result ->
            if (call.method == "turnOnFlashlight") {
                turnOnFlashlight()
                result.success(null)
            } else if (call.method == "turnOffFlashlight") {
                turnOffFlashlight()
                result.success(null)
            } else {
                result.notImplemented()
            }
        }
    }

    private fun turnOnFlashlight() {
        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val cameraId = cameraManager.cameraIdList[0] 
        cameraManager.setTorchMode(cameraId, true)
    }

    private fun turnOffFlashlight() {
        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        val cameraId = cameraManager.cameraIdList[0]
        cameraManager.setTorchMode(cameraId, false)
    }
}