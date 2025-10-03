import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

enum ImageDrawable {
  freefeos(method: 'freefeos'),
  ecosedkit(method: 'ecosedkit'),
  ebkit(method: 'ebkit');

  final String method;

  const ImageDrawable({required this.method});
}

abstract class _PlatformImage extends StatefulWidget {
  const _PlatformImage({super.key, this.size = 56, this.radius = 12});

  final double size;
  final double radius;

  ImageDrawable get image;

  @override
  State<_PlatformImage> createState() => _PlatformImageState();
}

class _PlatformImageState extends State<_PlatformImage> {
  _PlatformImageState();

  Uint8List? image;

  @override
  void initState() {
    super.initState();
    const MethodChannel("platform_resources")
        .invokeMethod<Uint8List>(widget.image.method)
        .then((value) => setState(() => image = value))
        .catchError((error) => debugPrint(error));
  }

  @override
  Widget build(BuildContext context) {
    return ClipRRect(
      borderRadius: BorderRadius.circular(widget.radius),
      child: SizedBox(
        width: widget.size,
        height: widget.size,
        child: image != null
            ? Image.memory(image!)
            : const Placeholder(color: Colors.redAccent),
      ),
    );
  }
}

class FreeFEOSLogo extends _PlatformImage {
  const FreeFEOSLogo({super.key, super.size, super.radius});

  @override
  ImageDrawable get image => ImageDrawable.freefeos;
}

class EcosedKitLogo extends _PlatformImage {
  const EcosedKitLogo({super.key, super.size, super.radius});

  @override
  ImageDrawable get image => ImageDrawable.ecosedkit;
}

class EbKitLogo extends _PlatformImage {
  const EbKitLogo({super.key, super.size, super.radius});

  @override
  ImageDrawable get image => ImageDrawable.ebkit;
}
