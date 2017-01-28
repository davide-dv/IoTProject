#include "StepperMotor.hpp"

/*
 * Author Antonio Tagliente 
 * Motor 28BYJ-48
 * FULL-STEP Two phases at a time <-Strongest Torque->
 */

StepperMotor::StepperMotor(int pin0, int pin1, int pin2, int pin3) {
	pinMode(pin0, OUTPUT);
	pinMode(pin1, OUTPUT);
	pinMode(pin2, OUTPUT);
	pinMode(pin3, OUTPUT);
	_pin0 = pin0;
	_pin1 = pin1;
	_pin2 = pin2;
	_pin3 = pin3;
	_actStep = 0;
	_direction = 1;
	_isRun = false;
}

StepperMotor::~StepperMotor() {}

void StepperMotor::steps(int numberOfSteps) {
	for (int i=0; i<numberOfSteps; i++) {
		step(_actStep);
		nextStep();
		delay(5);
	}
	_isRun = false;
}

void StepperMotor::reverse() {
	if(!isRun()) {
		if (_direction == 1) _direction = -1;
		else _direction = 1;
	}
}

bool StepperMotor::getActualDirection() {
	return _direction==1?1:0;
}

void StepperMotor::step(int step) {
	_isRun = true;
	switch(step){
		case 0:
	 		digitalWrite(_pin0, HIGH); 
			digitalWrite(_pin1, HIGH);
			digitalWrite(_pin2, LOW);
			digitalWrite(_pin3, LOW);
		break; 
		case 1:
			digitalWrite(_pin0, LOW); 
			digitalWrite(_pin1, HIGH);
			digitalWrite(_pin2, HIGH);
			digitalWrite(_pin3, LOW);
		break; 
		case 2:
			digitalWrite(_pin0, LOW); 
			digitalWrite(_pin1, LOW);
			digitalWrite(_pin2, HIGH);
			digitalWrite(_pin3, HIGH);
		break; 
		case 3:
			digitalWrite(_pin0, HIGH); 
			digitalWrite(_pin1, LOW);
			digitalWrite(_pin2, LOW);
			digitalWrite(_pin3, HIGH);
		break; 
		case 4:
			digitalWrite(_pin0, HIGH); 
			digitalWrite(_pin1, HIGH);
			digitalWrite(_pin2, LOW);
			digitalWrite(_pin3, LOW);
		break; 
		case 5:
			digitalWrite(_pin0, LOW); 
			digitalWrite(_pin1, HIGH);
			digitalWrite(_pin2, HIGH);
			digitalWrite(_pin3, LOW);
		break; 
		case 6:
			digitalWrite(_pin0, LOW); 
			digitalWrite(_pin1, LOW);
			digitalWrite(_pin2, HIGH);
			digitalWrite(_pin3, HIGH);
		break; 
		case 7:
			digitalWrite(_pin0, HIGH); 
			digitalWrite(_pin1, LOW);
			digitalWrite(_pin2, LOW);
			digitalWrite(_pin3, HIGH);
		break; 
		default:
			digitalWrite(_pin0, LOW); 
			digitalWrite(_pin1, LOW);
			digitalWrite(_pin2, LOW);
			digitalWrite(_pin3, LOW);
		break; 
	}
}

void StepperMotor::nextStep() {
	_actStep += _direction;
	if (_actStep > 7) _actStep = 0;
	else if (_actStep < 0) _actStep = 7;
}

bool StepperMotor::isRun() {
	return _isRun;
}

void StepperMotor::setOFF() {
	step(-1);
}