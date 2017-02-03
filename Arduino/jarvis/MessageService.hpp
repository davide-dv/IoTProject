#ifndef __MESSAGE_SERVICE_HPP__
#define __MESSAGE_SERVICE_HPP__

#include <Arduino.h>
#include "States.hpp"
#include "PinConfig.hpp"
#include <SoftwareSerial.h>

class MessageService
{
public:
	MessageService(SoftwareSerial* serial);
	~MessageService();
	bool messageIsPresent();
	String getMessage(); 
	void sendMessage(String msg);
private:
	SoftwareSerial* _serial;
};

#endif
