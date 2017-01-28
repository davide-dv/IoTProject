#include "Arduino.h"
#include "PresenceSensor.hpp"

PresenceSensor::PresenceSensor(int digitalPin)
{
  _pin = digitalPin;
  pinMode(_pin, INPUT);
}

bool PresenceSensor::isPresent()
{
  return digitalRead(_pin)==HIGH?true:false;
}
