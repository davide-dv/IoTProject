#ifndef __MESSAGE_SERVICE_HPP__
#define __MESSAGE_SERVICE_HPP__

#include <Arduino.h>
#include <SoftwareSerial.h>
#include "States.hpp"
#include "PinConfig.hpp"

class MessageService
{
public:
	MessageService();
	~MessageService();
	bool messageIsPresent();
	String getMessage(); 
	void sendMessage(String msg);
private:
	SoftwareSerial* _serial;
};

#endif