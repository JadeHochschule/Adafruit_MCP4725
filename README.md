# Adafruit MCP4725 leJOS Implmentation

This is a java implementation to control an [Adafruit MCP475](https://github.com/adafruit/Adafruit_MCP4725) from an LEGO EV3 sensor port, running
[leJOS](http://www.lejos.org/)

## Wiring

You need to connect the DAC like this to an EV3 sensor port:

Blue Wire 	  = SDA
Yellow Wire 	= SCL
Red Wire		  = GND
Green Wire  	= VDD

## Example

This example assumes that you have the sensor correctly wired to sensor port S1

```java
public class VoltageTest {

	public static void main(String[] args) {
		final AdafruitMCP4725 dac = new AdafruitMCP4725(SensorPort.S3);

		// This will set the DAC to (5/4096) * 2000 = ~1.9V
		dac.setVoltageScale(2000);
	}
}
```
