#include "MessageService.hpp"

MessageService::MessageService(SoftwareSerial* serial) {
	_serial = serial; 
}

MessageService::~MessageService() {}

String MessageService::getMessage() {
	String message = "";
    if (_serial->available()) message = _serial->readString();
    return message;
}

void MessageService::sendMessage(String msg) {
	if (msg.length()>0) {
		_serial->println(msg);
	}
}
