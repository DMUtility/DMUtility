import 'package:flutter/material.dart';

import 'app.dart';
import 'capsule_placeholder.dart';
import 'platform_image.dart';

void main() => runApp(const DMUtilityApp());



class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key});

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('EcosedKit'),
        actions: const [CapsulePlaceholder()],
      ),
      body: Column(
        children: [
          Header(),
          StateCard(),

          FreeFEOSLogo(),
          EcosedKitLogo(),
          EbKitLogo(),
        ],
      ),
    );
  }
}

class Header extends StatelessWidget {
  const Header({super.key});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.symmetric(vertical: 16, horizontal: 30),
      child: Row(
        children: [
          EcosedKitLogo(),
          Padding(
            padding: EdgeInsets.only(left: 16),
            child: Text(
              'EcosedKit',
              style: Theme.of(context).textTheme.titleLarge,
            ),
          ),
        ],
      ),
    );
  }
}

class StateCard extends StatelessWidget {
  const StateCard({super.key});

  @override
  Widget build(BuildContext context) {
    return Card(
      margin: EdgeInsets.all(16),
      color: Theme.of(context).colorScheme.primaryContainer,
      child: ListTile(
        leading: Icon(Icons.check_circle_outline),
        title: Text('一切正常'),
        subtitle: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [Text('AppID: xxxxxx'), Text('版本: 1.0.0')],
        ),
        contentPadding: EdgeInsets.symmetric(vertical: 16, horizontal: 20),
      ),
    );
  }
}
