// ignore_for_file: library_private_types_in_public_api

import 'package:flutter/foundation.dart';
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class FlashlightPage extends StatefulWidget {
  const FlashlightPage({super.key});

  @override
  _FlashlightPageState createState() => _FlashlightPageState();
}

class _FlashlightPageState extends State<FlashlightPage> {
  static const platform =
      MethodChannel('com.example.flashlight_app/flashlight');
  bool isFlashlightOn = false;

  Future<void> _toggleFlashlight() async {
    try {
      if (isFlashlightOn) {
        await platform.invokeMethod('turnOffFlashlight');
      } else {
        await platform.invokeMethod('turnOnFlashlight');
      }
      setState(() {
        isFlashlightOn = !isFlashlightOn;
      });
    } on PlatformException catch (e) {
      if (kDebugMode) {
        print("Failed to toggle flashlight: ${e.message}");
      }
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: [
          const SizedBox(
            height: 30,
          ),
          SwitchListTile(
            value: isFlashlightOn,
            onChanged: (value) {
              _toggleFlashlight();
            },
            title: const Text('Flashlight'),
          ),
        ],
      ),
    );
  }
}
