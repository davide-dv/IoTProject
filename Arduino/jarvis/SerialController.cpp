#include "SerialController.hpp"
#define TEMP_REFRESH 3000

SerialController::SerialController(JarvisHW* js) {
	_js = js;
	_start = 0;
	_temp = 27;
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
		float temp;
		if ((temp = _js->getTempSens()->getValue())>0) {
			_temp = temp;
		}
    Serial.print("T");
    Serial.println(_temp);
		_start = millis();
	} 

	if (_js->getState() == ALARM) {
		if (Serial.available() && Serial.readString().equals("A_N")) {
			_js->setState(WAIT);
		}  
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
			if(msg.substring(1).equals("ACTIVATE")) {
				_js->setState(ALARM);
				_internalState = A_WAIT;
        Serial.println("A");
			} else if (msg.substring(1).equals("GUARANTEED")) {
				_js->setState(WAIT);
				Serial.println("P");
				_internalState = A_WAIT;
			}
		break;
	}

}

