package io.github.pseudodistant.hardwarespoof;


import io.github.pseudodistant.hardwarespoof.cpus.AMDCPUs;
import io.github.pseudodistant.hardwarespoof.cpus.CPUVendors;
import io.github.pseudodistant.hardwarespoof.cpus.IntelCPUs;
import io.github.pseudodistant.hardwarespoof.gpus.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.CyclingButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.TranslatableText;

@Environment(EnvType.CLIENT)
public class HSScreen extends Screen {
	public HSScreen() {
		super(new TranslatableText("hardwarespoof.mainscreen"));
	}
	public CyclingButtonWidget<AMDGPUs> amdGpuButton;
	public CyclingButtonWidget<NVIDIAGPUs> nvGpuButton;
	public CyclingButtonWidget<IntelGPUs> intelGpuButton;
	public CyclingButtonWidget<ATIGPUs> atiGpuButton;
	public CyclingButtonWidget<AMDCPUs> amdCpuButton;
	public CyclingButtonWidget<IntelCPUs> intelCpuButton;

	protected void init() {
		setButtons();
		setGPUButtonVisibility();
		setCPUButtonVisibility();
		this.addDrawableChild(CyclingButtonWidget.builder(GPUVendors::getBrand).values(GPUVendors.values()).initially(HardwareSpoof.FAKEGPUVENDOR)
			.build(this.width / 2 - 100, this.height / 4 - 10,
			200, 20, new TranslatableText("hardwarespoof.gpuvendor"), (button, vendor) -> {
				HardwareSpoof.setFAKEGPUVENDOR(GPUVendors.values()[vendor.ordinal()]);
					setGPUButtonVisibility();
				}
			));
		this.addDrawableChild(CyclingButtonWidget.builder(Platforms::getName).values(Platforms.values()).initially(HardwareSpoof.FAKEPLATFORM)
			.build(this.width / 2 - 100, this.height / 4 + 34,
				200, 20, new TranslatableText("hardwarespoof.driver"), (button, platform) -> {
					HardwareSpoof.setFAKEPLATFORM(Platforms.values()[platform.ordinal()]);
				}
		));
		this.addDrawableChild(CyclingButtonWidget.builder(CPUVendors::getName).values(CPUVendors.values()).initially(HardwareSpoof.FAKECPUVENDOR)
			.build(this.width / 2 - 100, this.height / 4 + 56,
			200, 20, new TranslatableText("hardwarespoof.cpuvendor"), (button, vendor) -> {
				HardwareSpoof.setFAKECPUVENDOR(CPUVendors.values()[vendor.ordinal()]);
					setCPUButtonVisibility();
				}
		));
		this.addDrawableChild(amdGpuButton);
		this.addDrawableChild(nvGpuButton);
		this.addDrawableChild(intelGpuButton);
		this.addDrawableChild(atiGpuButton);
		this.addDrawableChild(amdCpuButton);
		this.addDrawableChild(intelCpuButton);
	}

	private void setCPUButtonVisibility() {
		switch (HardwareSpoof.FAKECPUVENDOR) {
			case AMD -> {
				intelCpuButton.visible = false;
				amdCpuButton.visible = true;
			}
			case INTEL -> {
				intelCpuButton.visible = true;
				amdCpuButton.visible = false;
			}
		}
	}
	private void setGPUButtonVisibility() {
		switch (HardwareSpoof.FAKEGPUVENDOR) {
			case NVIDIA -> {
				amdGpuButton.visible = false;
				nvGpuButton.visible = true;
				intelGpuButton.visible = false;
				atiGpuButton.visible = false;
			}
			case AMD -> {
				amdGpuButton.visible = true;
				nvGpuButton.visible = false;
				intelGpuButton.visible = false;
				atiGpuButton.visible = false;
			}
			case INTEL -> {
				amdGpuButton.visible = false;
				nvGpuButton.visible = false;
				intelGpuButton.visible = true;
				atiGpuButton.visible = false;
			}
			case ATI -> {
				amdGpuButton.visible = false;
				nvGpuButton.visible = false;
				intelGpuButton.visible = false;
				atiGpuButton.visible = true;
			}
		}
	}

	protected void setButtons() {
		amdGpuButton = CyclingButtonWidget.builder(AMDGPUs::getDebugInfo).values(AMDGPUs.values()).initially(AMDGPUs.RX450)
			.build(this.width / 2 - 100, this.height / 4 + 12,
			200, 20, new TranslatableText("hardwarespoof.gpu"), (button, gpu) -> {
			HardwareSpoof.setFAKEAMDGPU(AMDGPUs.values()[gpu.ordinal()]);
		});
		nvGpuButton = CyclingButtonWidget.builder(NVIDIAGPUs::getDebugInfo).values(NVIDIAGPUs.values()).initially(NVIDIAGPUs.GF8400MGT)
			.build(this.width / 2 - 100, this.height / 4 + 12,
			200, 20, new TranslatableText("hardwarespoof.gpu"), (button, gpu) -> {
			HardwareSpoof.setFAKENVGPU(NVIDIAGPUs.values()[gpu.ordinal()]);
		});
		intelGpuButton = CyclingButtonWidget.builder(IntelGPUs::getDebugInfo).values(IntelGPUs.values()).initially(IntelGPUs.HD4000)
			.build(this.width / 2 - 100, this.height / 4 + 12,
			200, 20, new TranslatableText("hardwarespoof.gpu"), (button, gpu) -> {
			HardwareSpoof.setFAKEINTELGPU(IntelGPUs.values()[gpu.ordinal()]);
		});
		atiGpuButton = CyclingButtonWidget.builder(ATIGPUs::getDebugInfo).values(ATIGPUs.values()).initially(ATIGPUs.A4500)
			.build(this.width / 2 - 100, this.height / 4 + 12,
			200, 20, new TranslatableText("hardwarespoof.gpu"), (button, gpu) -> {
			HardwareSpoof.setFAKEATIGPU(ATIGPUs.values()[gpu.ordinal()]);
		});

		amdCpuButton = CyclingButtonWidget.builder(AMDCPUs::getShortName).values(AMDCPUs.values()).initially(AMDCPUs.A86410)
			.build(this.width / 2 - 100, this.height / 4 + 78,
			200, 20, new TranslatableText("hardwarespoof.cpu"), (button, cpu) -> {
			HardwareSpoof.setFAKEAMDCPU(AMDCPUs.values()[cpu.ordinal()]);
		});
		intelCpuButton = CyclingButtonWidget.builder(IntelCPUs::getShortName).values(IntelCPUs.values()).initially(IntelCPUs.C2DT8100)
			.build(this.width / 2 - 100, this.height / 4 + 78,
			200, 20, new TranslatableText("hardwarespoof.cpu"), (button, cpu) -> {
			HardwareSpoof.setFAKEINTELCPU(IntelCPUs.values()[cpu.ordinal()]);
		});
	}

	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		this.renderBackground(matrices);
		drawCenteredText(matrices, this.textRenderer, this.title, this.width / 2, 40, 16777215);
		super.render(matrices, mouseX, mouseY, delta);
	}
}
