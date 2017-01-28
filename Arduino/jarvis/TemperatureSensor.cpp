#include "Arduino.h"
#include "TemperatureSensor.hpp"

TemperatureSensor::TemperatureSensor(int analogPin)
{
  _pin = analogPin;
  pinMode(_pin, INPUT);
}

float TemperatureSensor::getValue()
{
	int reading = analogRead(_pin);  
 
	// converting that reading to voltage, for 3.3v arduino use 3.3
	float voltage = reading * 5.0;
	voltage /= 1024.0; 
	 	 
	// now print out the temperature
	float temperatureC = (voltage - 0.5) * 100 ;  //converting from 10 mv per degree wit 500 mV offset
	                                               //to degrees ((voltage - 500mV) times 100)
  return temperatureC;
}
