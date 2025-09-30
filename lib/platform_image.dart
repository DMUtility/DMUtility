import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class PlatformImage extends StatefulWidget {
  const PlatformImage({super.key, required this.name});

  final String name;

  @override
  State<PlatformImage> createState() => _PlatformImageState();
}

class _PlatformImageState extends State<PlatformImage> {
  _PlatformImageState();

  static const MethodChannel _channel = MethodChannel("platform_resources");

  // 图片数据
  Uint8List? image;

  Future<Uint8List?> drawableMipmap(String name, bool isDrawable) async {
    return await _channel.invokeMethod<Uint8List>("drawableMipmap", {
      "name": name,
      "is_drawable": isDrawable,
    });
  }

  @override
  void initState() {
    super.initState();
    _channel
        .invokeMethod<Uint8List>(widget.name)
        .then((value) => setState(() => image = value))
        .catchError((error) => debugPrint(error));
  }

  @override
  Widget build(BuildContext context) {
    return image != null ? Image.memory(image!) : const Placeholder();
  }
}

abstract class BaseLogo extends StatelessWidget {
  const BaseLogo({super.key, this.size = 56, this.radius = 12});

  final double size;
  final double radius;

  String get name;

  @override
  Widget build(BuildContext context) {
    return ClipRRect(
      borderRadius: BorderRadius.circular(radius),
      child: SizedBox(
        width: size,
        height: size,
        child: PlatformImage(name: name),
      ),
    );
  }
}

class FreeFEOSLogo extends BaseLogo {
  const FreeFEOSLogo({super.key, super.size, super.radius});

  @override
  String get name => 'freefeos';
}

class EcosedKitLogo extends BaseLogo {
  const EcosedKitLogo({super.key, super.size, super.radius});

  @override
  String get name => 'ecosedkit';
}

class EbKitLogo extends BaseLogo {
  const EbKitLogo({super.key, super.size, super.radius});

  @override
  String get name => 'ebkit';
}
