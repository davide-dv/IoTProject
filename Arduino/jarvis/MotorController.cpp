
#include "MotorController.hpp"
#define ATTACK_VALUE 180

MotorController::MotorController(JarvisHW* js) {
	_js = js;
	_count = 0;
	_internalState = NONE;
}

MotorController::~MotorController() {}

void MotorController::init(int period) {
	Task::init(period);
}

void MotorController::tick() {
	if (_js->getState() == ALARM) {
	_internalState = ATTACK;
	activateDefence();
	}
}

void MotorController::activateDefence() {
	switch(_internalState) {
		case NONE:
			_attackValue = angleConverter(ATTACK_VALUE);
		break;

		case ATTACK:
			if(_count < (_attackValue * 2)) {
				if (_count == _attackValue) _js->getMotor()->reverse();
				_count++;
				_js->getMotor()->nextDegree();
			} else {
				_internalState = NONE;
				_count = 0;
				_js->getMotor()->reverse();
				_js->getMotor()->reset();
			}
		break;
	}

}

int MotorController::angleConverter(int value) {
	float returnValue = value * ANGLE_COEFF_CONVERTER;
	return (int)returnValue;
}
