#include "JarvisHW.hpp"
#include "Arduino.h"

JarvisHW::JarvisHW() {
	int pin[4] = PIN_MOTOR;
	_motor = new Motor(pin[0], pin[1], pin[2], pin[3]);
	_temp = new TemperatureSensor(PIN_TEMP);
	_pir = new PresenceSensor(PIN_PIR);
	_state = WAIT;
}

JarvisHW::~JarvisHW() {}

Motor* JarvisHW::getMotor() {
	return _motor;
}

TemperatureSensor* JarvisHW::getTempSens() {
	return _temp;
}

PresenceSensor* JarvisHW::getPir() {
	return _pir;
}

State JarvisHW::getState() {
	return _state;
}

void JarvisHW::setState(State state) {
	_state = state;
}