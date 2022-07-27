package io.github.pseudodistant.hardwarespoof;

import io.github.pseudodistant.hardwarespoof.cpus.AMDCPUs;
import io.github.pseudodistant.hardwarespoof.cpus.CPUVendors;
import io.github.pseudodistant.hardwarespoof.cpus.IntelCPUs;
import io.github.pseudodistant.hardwarespoof.gpus.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.client.screen.v1.Screens;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.TranslatableText;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HardwareSpoof implements ModInitializer {
	public static String currentAMDAdrenaline = "Compatibility Profile Context 22.6.1 27.20.12033.2007";
	public static String currentNvidiaDriver = "NVIDIA 516.59";
	public static String currentMesa = "(Core Profile) Mesa 22.1.1";
	public static final Logger LOGGER = LoggerFactory.getLogger("hardwarespoof");
	public static Platforms FAKEPLATFORM;
	public static AMDCPUs FAKEAMDCPU;
	public static IntelCPUs FAKEINTELCPU;
	public static CPUVendors FAKECPUVENDOR;
	public static AMDGPUs FAKEAMDGPU;
	public static NVIDIAGPUs FAKENVGPU;
	public static IntelGPUs FAKEINTELGPU;
	public static ATIGPUs FAKEATIGPU;
	public static GPUVendors FAKEGPUVENDOR;

	public static void setFAKEGPUVENDOR(GPUVendors a) {
		FAKEGPUVENDOR = a;
	}
	public static void setFAKECPUVENDOR(CPUVendors a) {
		FAKECPUVENDOR = a;
	}

	public static void setFAKEAMDGPU(AMDGPUs FAKEGPU) {
		HardwareSpoof.FAKEAMDGPU = FAKEGPU;
	}

	public static void setFAKENVGPU(NVIDIAGPUs FAKEGPU) {
		HardwareSpoof.FAKENVGPU = FAKEGPU;
	}
	public static void setFAKEINTELGPU(IntelGPUs FAKEINTELGPU) {
		HardwareSpoof.FAKEINTELGPU = FAKEINTELGPU;
	}
	public static void setFAKEATIGPU(ATIGPUs FAKEATIGPU) {
		HardwareSpoof.FAKEATIGPU = FAKEATIGPU;
	}
	public static void setFAKEPLATFORM(Platforms FAKEPLATFORM) {
		HardwareSpoof.FAKEPLATFORM = FAKEPLATFORM;
	}
	public static void setFAKEAMDCPU(AMDCPUs CPU) {
		HardwareSpoof.FAKEAMDCPU = CPU;
	}
	public static void setFAKEINTELCPU(IntelCPUs CPU) {
		HardwareSpoof.FAKEINTELCPU = CPU;
	}

	@Override
	public void onInitialize() {

		ScreenEvents.AFTER_INIT.register((client, screen, scaledWidth, scaledHeight) -> {
			if (screen instanceof OptionsScreen) {
				Screens.getButtons(screen).add(new ButtonWidget(scaledWidth / 2 - 102, scaledHeight / 6 + 216, 204, 20, new TranslatableText("hardwarespoof.mainscreen"), (button) -> {
					client.setScreen(new HSScreen());
				}));
			}
		});

		FAKEPLATFORM = Platforms.WINDOWS;
		FAKEAMDCPU = AMDCPUs.A86410;
		FAKEINTELCPU = IntelCPUs.C2DT8100;
		FAKEGPUVENDOR = GPUVendors.NVIDIA;
		FAKECPUVENDOR = CPUVendors.INTEL;
		FAKEAMDGPU = AMDGPUs.RX450;
		FAKENVGPU = NVIDIAGPUs.GF8400MGT;
		FAKEINTELGPU = IntelGPUs.HD4000;
		FAKEATIGPU = ATIGPUs.A4500;
	}

	public static String maybeFakeLinuxKernel() {
		if (!(System.getProperty("os.name").contains("win") || System.getProperty("os.name").contains("mac"))) {
			return System.getProperty("os.version");
		}
		return "5.15.54";
	}
}
