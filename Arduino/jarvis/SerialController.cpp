#include "SerialController.hpp"
#define TEMP_REFRESH 3000

SerialController::SerialController(JarvisHW* js) {
	_js = js;
	_start = 0;
	_temp = 0;
	_internalState = A_WAIT;
	_bluetooth = new SoftwareSerial(bt[0], bt[1]);
	Serial.begin(9600);
	_bluetooth->begin(9600);
	_btMsg = new MessageService(_bluetooth);
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
		float temp;
		if ((temp = _js->getTempSens()->getValue())>0) {
			_temp = temp;
			Serial.println(_temp);
		}
		_start = millis();
	} 

	switch(_internalState) {
		case A_WAIT:
			if (_js->getState() == TRIGGERED)
			{
				_btMsg->sendMessage("TRIGGERED");
				_internalState = BT_ADV;
			}
		break;

		case BT_ADV:
			String msg = _btMsg->getMessage();
			if(msg == "ACTIVATE") {
				_js->setState(ALARM);
				Serial.println("A");
				_internalState = A_WAIT;
			} else if (msg == "GUARANTEED") {
				_js->setState(WAIT);
				Serial.println("P");
				_internalState = A_WAIT;
			}
		break;
	}

}

