package io.github.pseudodistant.hardwarespoof.gpus;

import com.mojang.blaze3d.platform.GlDebugInfo;
import io.github.pseudodistant.hardwarespoof.HardwareSpoof;
import net.minecraft.text.Text;

public enum AMDGPUs {
	/* Pre-Polaris idk */
	R5("Compatibility Profile Context 18.9.3", "AMD Radeon(TM) R5 Graphics", "AMD MULLINS (DRM 2.50.0, " + HardwareSpoof.maybeFakeLinuxKernel() + ", LLVM 8.0.0)"),

	/* Polaris AMD */
	RX450(HardwareSpoof.currentAMDAdrenaline, "Radeon RX 450 Series", "AMD Radeon RX 450 Series (polaris10, LLVM 13.0.1, DRM 3.4.6, " + HardwareSpoof.maybeFakeLinuxKernel() + ')'),
	RX460(HardwareSpoof.currentAMDAdrenaline, "Radeon RX 460 Series", "AMD Radeon RX 460 Series (polaris10, LLVM 13.0.1, DRM 3.4.6, " + HardwareSpoof.maybeFakeLinuxKernel() + ')'),
	RX470(HardwareSpoof.currentAMDAdrenaline, "Radeon RX 470 Series", "AMD Radeon RX 470 Series (polaris10, LLVM 13.0.1, DRM 3.4.6, " + HardwareSpoof.maybeFakeLinuxKernel() + ')'),
	RX480(HardwareSpoof.currentAMDAdrenaline, "Radeon RX 480 Series", "AMD Radeon RX 480 Series (polaris10, LLVM 13.0.1, DRM 3.4.6, " + HardwareSpoof.maybeFakeLinuxKernel() + ')'),
	RX550(HardwareSpoof.currentAMDAdrenaline, "Radeon RX 550 Series", "AMD Radeon RX 550 Series (polaris10, LLVM 13.0.1, DRM 3.4.6, " + HardwareSpoof.maybeFakeLinuxKernel() + ')'),
	RX560(HardwareSpoof.currentAMDAdrenaline, "Radeon RX 560 Series", "AMD Radeon RX 560 Series (polaris10, LLVM 13.0.1, DRM 3.4.6, " + HardwareSpoof.maybeFakeLinuxKernel() + ')'),
	RX570(HardwareSpoof.currentAMDAdrenaline, "Radeon RX 570 Series", "AMD Radeon RX 570 Series (polaris10, LLVM 13.0.1, DRM 3.4.6, " + HardwareSpoof.maybeFakeLinuxKernel() + ')'),
	RX580(HardwareSpoof.currentAMDAdrenaline, "Radeon RX 580 Series", "AMD Radeon RX 580 Series (polaris10, LLVM 13.0.1, DRM 3.4.6, " + HardwareSpoof.maybeFakeLinuxKernel() + ')'),
	;

	final String driver;
	final String debugInfo;
	final String oglVersion;
	final String mesaDebugInfo;

	AMDGPUs(String windowsDriverInfo, String debugInfo, String mesaDebugInfo, String openGLVersion) {
		this.driver = windowsDriverInfo;
		this.debugInfo = debugInfo;
		this.oglVersion = openGLVersion;
		this.mesaDebugInfo = mesaDebugInfo;
	}

	AMDGPUs(String windowsDriverInfo, String debugInfo, String mesaDebugInfo) {
		this.driver = windowsDriverInfo;
		this.debugInfo = debugInfo;
		this.oglVersion = null;
		this.mesaDebugInfo = mesaDebugInfo;
	}

	AMDGPUs(String windowsDriverInfo, String debugInfo) {
		this.driver = windowsDriverInfo;
		this.debugInfo = debugInfo;
		this.oglVersion = null;
		this.mesaDebugInfo = null;
	}

	public String getDriver() {
		return driver;
	}

	public String getMesaDebugInfo() {
		return mesaDebugInfo;
	}

	public Text getDebugInfo() {
		return Text.of(debugInfo);
	}
}
