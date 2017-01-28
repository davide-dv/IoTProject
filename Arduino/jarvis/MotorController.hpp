#ifndef __MOTOR_CONTROLLER_HPP__
#define __MOTOR_CONTROLLER_HPP__

#include "Arduino.h"
#include "JarvisHW.hpp"
#include "Task.hpp"

#define ANGLE_COEFF_CONVERTER 0.58

class  MotorController : public Task
{
public:
	MotorController(JarvisHW* system);
	~MotorController();
	void init(int period);
	void tick();
private:
	JarvisHW* _js;
	void activateDefence();
	int angleConverter(int value);
	int _count;
	int _attackValue;
	enum InternalState {NONE, ATTACK} _internalState;
};

#endif