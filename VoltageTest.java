public class VoltageTest {

	public static void main(String[] args) {
		AdafruitMCP4725 dac = new AdafruitMCP4725(SensorPort.S1);
		// This will set the DAC to (5/4096) * 2000 = ~2V
		dac.setVoltageScale(2000);
	}

}
