#ifndef __PresenceSensors_hpp__
#define __PresenceSensors_hpp__

#include "Arduino.h"

class PresenceSensor
{
public:
  PresenceSensor(int digitalPin);
  bool isPresent(); //true = element is present
private:
  int _pin;
};

#endif
