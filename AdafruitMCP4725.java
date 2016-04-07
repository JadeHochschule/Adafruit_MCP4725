import lejos.hardware.port.Port;
import lejos.hardware.sensor.I2CSensor;

/**
 * Using an Adafruit MPC4725 DAC
 *
 * The module needs to be wired like this
 * EV3 Sensor Port: (S1 - S4)
 *
 * Blue Wire 	  = SDA
 * Yellow Wire 	= SCL
 * Red Wire		  = GND
 * Green Wire  	= VDD
 *
 * You can scale the voltage between 0V and 5V in 4096 Steps.
 *
 * @author Fabrice Weinberg
 */
public class AdafruitMCP4725 extends I2CSensor {

	//leJOS saves 7 Bit I2C_ADRESS as 8Bit (padding right with 0)
	// This is a default I2C adress used by Adafruit (A0 not connected)
	public static final int I2C_ADDRESS = 0x62 << 1;

	public static final int CMD_WRITEDAC	   = 0x40;
	public static final int CMD_WRITEDACEEPROM = 0x60;

	// This will store the scaleFactor
	public byte data[] = new byte[2];


	public AdafruitMCP4725(Port port) {
		this(port, I2C_ADDRESS);
	}

	public AdafruitMCP4725(Port port, int address) {
		super(port, address, TYPE_HIGHSPEED);
	}

	/**
	 * This will change the Voltage on VOU to (5 / 4096) * scaleFactor
	 *
	 * @param scaleFactor between 0 and 4095
	 */
	public void setVoltageScale(int scaleFactor) {
		setVoltageScale(scaleFactor, false);
	}
	/**
	 * This will change the Voltage on VOU to (5 / 4096) * scaleFactor and optionally
	 * save the scaleFactor in the EEPROM of the DAC.
	 *
	 * @param scaleFactor value between 0 and 4095
	 * @param save true scaleFactor will be saved on the DAC, false scaleFactor will not be saved
	 */
	public void setVoltageScale(int scaleFactor, boolean save) {
		if (scaleFactor < 0) return;

		scaleFactor = scaleFactor >= 4095 ? 4095 : scaleFactor;

		data[0] = (byte)(scaleFactor / 16);
		data[1] = (byte)((scaleFactor % 16) << 4);

		sendData(save ? CMD_WRITEDACEEPROM : CMD_WRITEDAC, data, 2);
	}

	@Override
	public void close() {
		data[0] = 0;
		data[1] = 0;
		sendData(CMD_WRITEDAC, data, 2);
		super.close();
	}

	@Override
	public String getName() {
		return "Adafruit MCP4725";
	}

	@Override
	public String getVendorID() {
		return "Adafruit";
	}

	@Override
	public String getProductID() {
		return "MCP4725";
	}

	@Override
	public String getVersion() {
		return "1.0.0";
	}
}
