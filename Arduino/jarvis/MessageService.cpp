#include "MessageService.hpp"

MessageService::MessageService() {
	Serial.begin(9600);
	int bt[2] = PIN_BT;
	_serial = new SoftwareSerial(bt[0], bt[1]);
}

MessageService::~MessageService() {}

bool MessageService::messageIsPresent() {
	return _serial->available();	
}

String MessageService::getMessage() {
	if (messageIsPresent()) {
		String msg = _serial->readString();
		if (msg.length()>1) return msg;
	}
	return "";
}

void MessageService::sendMessage(String msg) {
	if (msg.length()>0) {
		_serial->println(msg);
	}
}