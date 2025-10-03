import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:freefeos/freefeos.dart';
import 'package:multi_builder/multi_builder.dart';

import 'main.dart';
import 'theme.dart';

class DMUtilityApp extends StatelessWidget {
  const DMUtilityApp({super.key});

  /// 主题
  final DMUtilityTheme theme = const DMUtilityTheme();

  /// 获取系统栏图标深色/浅色
  Brightness getSystemBarBrightness(BuildContext context) {
    if (MediaQuery.platformBrightnessOf(context) == Brightness.light) {
      return Brightness.dark; // 浅色主题深色系统栏图标
    } else {
      return Brightness.light; // 深色主题浅色系统栏图标
    }
  }

  /// 主题
  ThemeData customTheme(BuildContext context, ThemeData origin) {
    return origin.copyWith(
      appBarTheme: AppBarTheme(
        systemOverlayStyle: SystemUiOverlayStyle(
          systemNavigationBarColor: Colors.transparent,
          systemNavigationBarDividerColor: Colors.transparent,
          systemNavigationBarIconBrightness: getSystemBarBrightness(context),
          systemNavigationBarContrastEnforced: false,
          statusBarColor: Colors.transparent,
          statusBarBrightness: getSystemBarBrightness(context),
          statusBarIconBrightness: getSystemBarBrightness(context),
          systemStatusBarContrastEnforced: false,
        ),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'TrebleKit',
      theme: customTheme(context, theme.light()),
      darkTheme: customTheme(context, theme.dark()),
      builder: <TransitionBuilder>[FreeFEOS.builder].toBuilder,
      home: const MyHomePage(),
      debugShowCheckedModeBanner: false,
    );
  }
}