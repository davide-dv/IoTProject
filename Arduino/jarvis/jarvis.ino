#include "Scheduler.hpp"
#include "Arduino.h"
#include "JarvisHW.hpp"
#include "MotorController.hpp"
#include "PresenceController.hpp"
#include "SerialController.hpp"

Scheduler sh;
JarvisHW* js;
Task *mC, *pC, *sC;

void setup()
{
	sh.init(5);
	js = new JarvisHW();
	sC = new SerialController(js);
	sC->init(10);
	mC = new MotorController(js);
	mC->init(20);
	pC = new PresenceController(js);
	pC->init(500);
	sh.addTask(sC);
	sh.addTask(mC);
	sh.addTask(pC);
}

void loop()
{
  sh.schedule();
}
