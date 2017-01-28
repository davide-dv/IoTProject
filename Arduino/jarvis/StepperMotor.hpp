#ifndef __STEPPER_MOTOR_HPP__
#define __STEPPER_MOTOR_HPP__

/*
 * Author Antonio Tagliente 
 * Motor 28BYJ-48
 * FULL-STEP Two phases at a time <-Strongest Torque->
 */

#include <Arduino.h>

class StepperMotor
{
public:
	StepperMotor(int pin0, int pin1, int pin2, int pin3);
	~StepperMotor();
	void steps(int numberOfSteps);
	void reverse();
	bool getActualDirection(); // 1 = CW , 0 = CCW
	bool isRun();
	void setOFF();
private:
	void step(int step);
	void nextStep();
	int _direction; // 1 = CW , -1 = CCW
	int _actStep;
	bool _isRun;
	int _pin0;
	int _pin1;
	int _pin2;
	int _pin3;
};

#endif