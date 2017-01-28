#ifndef __JARVIS_HW_HPP__
#define __JARVIS_HW_HPP__

#include "PresenceSensor.hpp"
#include "TemperatureSensor.hpp"
#include "Motor.hpp"
#include "States.hpp"
#include "PinConfig.hpp"

class JarvisHW {
public:
	JarvisHW();
	~JarvisHW();
	Motor* getMotor();
	PresenceSensor* getPir();
	TemperatureSensor* getTempSens();
	void setState(State state);
	State getState();
private:
	Motor* _motor;
	PresenceSensor* _pir;
	TemperatureSensor* _temp;
	State _state;
};

#endif