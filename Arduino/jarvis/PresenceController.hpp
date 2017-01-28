#ifndef __PRESENCE_CONTROLLER_HPP__
#define __PRESENCE_CONTROLLER_HPP__

#include "JarvisHW.hpp"
#include "PinConfig.hpp"
#include "PresenceSensor.hpp"
#include "Task.hpp"

class PresenceController : public Task
{
public:
	PresenceController(JarvisHW* js);
	~PresenceController();
	void init(int period);
	void tick();
private:
	JarvisHW* _js;
	PresenceSensor* _ps;
	long _countdown;
};

#endif