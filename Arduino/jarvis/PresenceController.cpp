#include "PresenceController.hpp"
#define COUNTDOWN 10000

PresenceController::PresenceController(JarvisHW* js) {
	_js = js;
	_ps = new PresenceSensor(PIN_PIR);
	_countdown = 0;
}

PresenceController::~PresenceController() {}

void PresenceController::init(int period)
{
	Task::init(period);
}

void PresenceController::tick() {
	if (_ps->isPresent() && _js->getState() == WAIT) {
		_js->setState(TRIGGERED);
		_countdown = millis();
	}
	if (_js->getState() == TRIGGERED && (millis() - _countdown) > COUNTDOWN) _js->setState(ALARM);
} 