package io.github.pseudodistant.hardwarespoof.gpus;

import io.github.pseudodistant.hardwarespoof.HardwareSpoof;
import net.minecraft.text.Text;

public enum NVIDIAGPUs {
	/* Legacy Nvidia */
	GF8400MGT("NVIDIA 441.98", "GeForce 8400M GT/PCIE/SSE2", "3.3"),

	/* Pre-RTX Modern Nvidia */
	GT710(HardwareSpoof.currentNvidiaDriver, "GeForce GT 710/PCIE/SSE2");

	final String driver;
	final String debugInfo;
	final String oglVersion;

	NVIDIAGPUs(String windowsDriverInfo, String debugInfo, String openGLVersion) {
		this.driver = windowsDriverInfo;
		this.debugInfo = debugInfo;
		this.oglVersion = openGLVersion;
	}

	NVIDIAGPUs(String windowsDriverInfo, String debugInfo) {
		this.driver = windowsDriverInfo;
		this.debugInfo = debugInfo;
		this.oglVersion = null;
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
