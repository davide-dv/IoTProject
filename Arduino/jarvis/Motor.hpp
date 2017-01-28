#ifndef __MOTOR_HPP__
#define __MOTOR_HPP__

#include <Arduino.h>
#include "StepperMotor.hpp"

class Motor
{
public:
	Motor(int pin0, int pin1, int pin2, int pin3);
	~Motor();
	void nextDegree();
	void reverse();
	void reset();
private:
	StepperMotor* _stepper;
	int _count;
	bool _state;
	int _pin0;
	int _pin1;
	int _pin2;
	int _pin3;
};

#endif