#include "SerialController.hpp"
#define TEMP_REFRESH 3000

SerialController::SerialController(JarvisHW* js) {
	_js = js;
	_start = 0;
	_btMsg = new MessageService();
	Serial.begin(9600);
}

SerialController::~SerialController(){}

void SerialController::init(int period)
{
	Task::init(period);
}

void SerialController::tick()
{
	if ((millis() - _start) > TEMP_REFRESH) {
		Serial.print("T");
		Serial.println(_js->getTempSens()->getValue());
		_start = millis();
	} 

	if(_js->getState() == TRIGGERED) {
		_btMsg->sendMessage("TRIGGERED");
		Serial.println("P");
		String msg = _btMsg->getMessage();
		if(msg == "ACTIVATE") {
			_js->setState(ALARM);
			Serial.println("A");
		} else if (msg == "GUARANTEED") {
			_js->setState(WAIT);
		}
	} else if (_js->getState() == ALARM) {
		if (Serial.available()) {
			if (Serial.readString() == "A_N") {
				_js->setState(WAIT);
			}
		}
	}

}

