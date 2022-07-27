package io.github.pseudodistant.hardwarespoof.gpus;

import net.minecraft.text.Text;

public enum ATIGPUs {
	A4500("Compatibility Profile Context 14.100.0.0", "ATI Radeon HD 4300/4500 Series", "3.3.11672");

	final String driver;
	final String debugInfo;
	final String oglVersion;

	ATIGPUs(String windowsDriverInfo, String debugInfo, String openGLVersion) {
		this.driver = windowsDriverInfo;
		this.debugInfo = debugInfo;
		this.oglVersion = openGLVersion;
	}

	public Text getDebugInfo() {
		return Text.of(debugInfo);
	}

	public String getDriver() {
		return driver;
	}

	public String getOglVersion() {
		return oglVersion;
	}
}
