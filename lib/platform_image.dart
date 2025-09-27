import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class PlatformResources {
  const PlatformResources();

  static const MethodChannel _channel = MethodChannel("platform_resources");

  Future<Uint8List?> drawableMipmap(String name, bool isDrawable) async {
    return await _channel.invokeMethod<Uint8List>("drawableMipmap", {
      "name": name,
      "is_drawable": isDrawable,
    });
  }
}

class BasePlatformImage extends StatefulWidget {
  const BasePlatformImage({
    super.key,
    required this.name,
    required this.isDrawable,
  });

  final String name;
  final bool isDrawable;

  @override
  State<BasePlatformImage> createState() => _BasePlatformImageState();
}

class _BasePlatformImageState extends State<BasePlatformImage> {
  _BasePlatformImageState();

  final PlatformResources platformResources = const PlatformResources();

  // 图片数据
  Uint8List? image;

  @override
  void initState() {
    super.initState();
    platformResources
        .drawableMipmap(widget.name, widget.isDrawable)
        .then((value) => setState(() => image = value))
        .catchError((error) => debugPrint(error));
  }

  @override
  Widget build(BuildContext context) {
    return image != null ? Image.memory(image!) : const Placeholder();
  }
}

class DrawableImage extends StatelessWidget {
  const DrawableImage({super.key, required this.name});

  final String name;

  @override
  Widget build(BuildContext context) {
    return BasePlatformImage(name: name, isDrawable: true);
  }
}

class MipmapImage extends StatelessWidget {
  const MipmapImage({super.key, required this.name});

  final String name;

  @override
  Widget build(BuildContext context) {
    return BasePlatformImage(name: name, isDrawable: false);
  }
}

class EcosedKitLogo extends StatelessWidget {
  const EcosedKitLogo({super.key});

  @override
  Widget build(BuildContext context) {
    return const Placeholder();
  }
}
