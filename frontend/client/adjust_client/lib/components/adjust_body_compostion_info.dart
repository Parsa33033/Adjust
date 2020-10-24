

import 'package:adjust_client/config/i18n.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:syncfusion_flutter_gauges/gauges.dart';

class AdjustBodyCompositionInfo extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Container(
      child: SingleChildScrollView(
        child: Column(
          children: <Widget>[
            Container(
              height: 200,
              padding: EdgeInsets.all(10),
              width: MediaQuery.of(context).size.width,
              child: SfRadialGauge(
                  axes: <RadialAxis>[
                    RadialAxis(minimum: 0, maximum: 3, startAngle: 180, endAngle: 0,
                        ranges: <GaugeRange>[
                          GaugeRange(startValue: 0, endValue: 1, color:Colors.green),
                          GaugeRange(startValue: 1,endValue: 2,color: Colors.orange),
                          GaugeRange(startValue: 2,endValue: 3,color: Colors.red)],
                        pointers: <GaugePointer>[
                          NeedlePointer(value: 2)],
                        annotations: <GaugeAnnotation>[
                          GaugeAnnotation(widget: Container(child:
                          Text(getNumber('90.0'),style: TextStyle(fontSize: 25,fontWeight: FontWeight.bold))),
                              angle: 90, positionFactor: 0.5
                          )]
                    )]),
            )
          ],
        ),
      ),
    );
  }

}