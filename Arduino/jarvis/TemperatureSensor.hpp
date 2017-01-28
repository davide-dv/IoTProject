#ifndef __TemperatureSensors_hpp__
#define __TemperatureSensors_hpp__

#include "Arduino.h"

class TemperatureSensor
{
public:
  TemperatureSensor(int analogPin);
  float getValue(); 
private:
  int _pin;
};

#endif