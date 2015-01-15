Traffic-Monitor
===============

Workshop project for OOP training.

System : A product to collect traffic data, and allow analysis by producing desirable reports.
Problem 1 : Create an interpreter that reads "stream of signals" generated by sensors, which looks like following :
    A268981
    A269123
    A604957
    B604960
    A605128
    B605132
    A1089807
    B1089810
    A1089948
    B1089951

    - here first letter denotes the sensor that sends the signals(sensor A or B).
    - the numeric part is the time of day the signal was generated( in milliseconds).
    
Signals are generated by the following scheme of sensors : 
![alt tag](https://raw.githubusercontent.com/nishants/Traffic-Monitor/master/info/sensors.png)
