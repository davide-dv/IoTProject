#include "Motor.hpp"

Motor::Motor(int pin0, int pin1, int pin2, int pin3) {
	_stepper = new StepperMotor(pin0, pin1, pin2, pin3);
	_state = 1;
	_count = 0;
}

Motor::~Motor(){}

void Motor::nextDegree(){
	_stepper->steps(10);
}

void Motor::reverse() {
	_stepper->reverse();
}

void Motor::reset() {
	_stepper->setOFF();
}
