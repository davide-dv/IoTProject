package devices;

import com.pi4j.io.gpio.*;

import devices.p4j_impl.Config;

public class Button implements Buttons {

	private GpioPinDigitalInput pin;
	
	public Button(int pinNum){
		super();
		try {
		    GpioController gpio = GpioFactory.getInstance();
		    pin = gpio.provisionDigitalInputPin(Config.pinMap[pinNum],PinPullResistance.PULL_DOWN);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public synchronized boolean isPressed() {
		return pin.isHigh();
	}
		
}
