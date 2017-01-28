#ifndef __SERIALCONTROLLER_HPP__
#define __SERIALCONTROLLER_HPP__

#include "Task.hpp"
#include "JarvisHW.hpp"
#include "MessageService.hpp"

class SerialController : public Task
{
public:
  SerialController(JarvisHW* js);
  ~SerialController();
  void init(int period);
  void tick();
private:
  MessageService* _btMsg;
  JarvisHW* _js;
  long _start;
};

#endif
