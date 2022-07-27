package io.github.pseudodistant.hardwarespoof.gpus;

import net.minecraft.text.Text;

public enum IntelGPUs {
	HD4000("Build 10.18.10.4653", "Intel HD Graphics 4000", "4.0.0", "4.5"),
	HD4600("Build 10.18.14.4222", "Intel HD Graphics 4600", "4.3.0", "4.6"),
	PHDGD("Build 8.15.10.2993", "PHDGD Quantic C3", "2.1.0", "3.3.0"),

	;

	final String driver;
	final String debugInfo;
	final String oglVersion;
	final String mesaglVersion;

	IntelGPUs(String windowsDriverInfo, String debugInfo, String openGLVersion, String mesaGLVersion) {
		this.driver = windowsDriverInfo;
		this.debugInfo = debugInfo;
		this.oglVersion = openGLVersion;
		this.mesaglVersion = mesaGLVersion;
	}

	IntelGPUs(String windowsDriverInfo, String debugInfo) {
		this.driver = windowsDriverInfo;
		this.debugInfo = debugInfo;
		this.oglVersion = null;
		this.mesaglVersion = null;
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

	public String getMesaglVersion() {
		return mesaglVersion;
	}
}
