#ifndef __SERIALCONTROLLER_HPP__
#define __SERIALCONTROLLER_HPP__

#include "Task.hpp"
#include "JarvisHW.hpp"
#include "MessageService.hpp"
#include <SoftwareSerial.h>
#include "PinConfig.hpp"

class SerialController : public Task
{
public:
  SerialController(JarvisHW* js);
  ~SerialController();
  void init(int period);
  void tick();
private:
  int bt[2] = PIN_BT;
  SoftwareSerial* _bluetooth;
  MessageService* _btMsg;
  JarvisHW* _js;
  long _start;
  float _temp;
  enum InternalState {A_WAIT, BT_ADV} _internalState;
};

#endif
