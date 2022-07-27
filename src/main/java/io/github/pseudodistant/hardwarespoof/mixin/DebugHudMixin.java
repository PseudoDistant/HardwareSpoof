package io.github.pseudodistant.hardwarespoof.mixin;

import com.mojang.blaze3d.platform.GlDebugInfo;
import io.github.pseudodistant.hardwarespoof.HardwareSpoof;
import io.github.pseudodistant.hardwarespoof.Platforms;
import io.github.pseudodistant.hardwarespoof.gpus.GPUVendors;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.DebugHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DebugHud.class)
public class DebugHudMixin {
	@Redirect(method = "getRightText", at = @At(value = "INVOKE", target = "Ljava/lang/String;format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", ordinal = 3))
	public String fakeCPUInfo(String format, Object[] args) {
		return switch (HardwareSpoof.FAKECPUVENDOR) {
			case AMD -> String.format("CPU: %s", HardwareSpoof.FAKEAMDCPU.getName());
			case INTEL -> String.format("CPU: %s", HardwareSpoof.FAKEINTELCPU.getName());
			case OFF -> String.format("CPU: %s", GlDebugInfo.getCpuInfo());
		};
	}

	@Redirect(method = "getRightText", at = @At(value = "INVOKE", target = "Ljava/lang/String;format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", ordinal = 4))
	public String fakeGPUVendor(String format, Object[] args) {
		return switch (HardwareSpoof.FAKEGPUVENDOR) {
			case OFF -> String.format("Display: %dx%d (%s)", MinecraftClient.getInstance().getWindow().getFramebufferWidth(), MinecraftClient.getInstance().getWindow().getFramebufferHeight(), GlDebugInfo.getVendor());
			case NVIDIA -> String.format("Display: %dx%d (%s)", MinecraftClient.getInstance().getWindow().getFramebufferWidth(), MinecraftClient.getInstance().getWindow().getFramebufferHeight(), HardwareSpoof.FAKEPLATFORM != Platforms.LINUX ? HardwareSpoof.FAKEGPUVENDOR.brand : "noveau");
			default -> String.format("Display: %dx%d (%s)", MinecraftClient.getInstance().getWindow().getFramebufferWidth(), MinecraftClient.getInstance().getWindow().getFramebufferHeight(), HardwareSpoof.FAKEGPUVENDOR.brand);
		};
	}

	@Redirect(method = "getRightText", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/GlDebugInfo;getRenderer()Ljava/lang/String;"))
	public String fakeGPUInfo() {
		return switch (HardwareSpoof.FAKEGPUVENDOR) {
			case AMD -> HardwareSpoof.FAKEPLATFORM != Platforms.LINUX ? HardwareSpoof.FAKEAMDGPU.getDebugInfo().asString() : HardwareSpoof.FAKEAMDGPU.getMesaDebugInfo();
			case NVIDIA -> HardwareSpoof.FAKENVGPU.getDebugInfo().asString();
			case INTEL -> HardwareSpoof.FAKEINTELGPU.getDebugInfo().asString();
			case ATI -> HardwareSpoof.FAKEATIGPU.getDebugInfo().asString();
			case OFF -> GlDebugInfo.getRenderer();
		};
	}

	@Redirect(method = "getRightText", at = @At(value = "INVOKE", target = "Lcom/mojang/blaze3d/platform/GlDebugInfo;getVersion()Ljava/lang/String;"))
	public String fakeGPUInfo2() {
		return switch (HardwareSpoof.FAKEGPUVENDOR) {
			case AMD -> GlDebugInfo.getVersion().split("\\s+")[0] + ' ' + (HardwareSpoof.FAKEPLATFORM == Platforms.WINDOWS ? HardwareSpoof.FAKEAMDGPU.getDriver() : HardwareSpoof.currentMesa);
			case NVIDIA -> (HardwareSpoof.FAKENVGPU.getOglVersion() == null ? GlDebugInfo.getVersion().split("\\s+")[0] : HardwareSpoof.FAKENVGPU.getOglVersion()) + ' ' + (HardwareSpoof.FAKEPLATFORM == Platforms.WINDOWS ? HardwareSpoof.FAKENVGPU.getDriver() : HardwareSpoof.currentMesa);
			case INTEL -> (HardwareSpoof.FAKEINTELGPU.getOglVersion() == null ? GlDebugInfo.getVersion().split("\\s+")[0] : (HardwareSpoof.FAKEPLATFORM != Platforms.LINUX ? HardwareSpoof.FAKEINTELGPU.getOglVersion() : HardwareSpoof.FAKEINTELGPU.getMesaglVersion())) + ' ' + (HardwareSpoof.FAKEPLATFORM == Platforms.WINDOWS ? HardwareSpoof.FAKEINTELGPU.getDriver() : HardwareSpoof.currentMesa);
			case ATI -> (HardwareSpoof.FAKEATIGPU.getOglVersion() == null ? GlDebugInfo.getVersion().split("\\s+")[0] : HardwareSpoof.FAKEATIGPU.getOglVersion()) + ' ' + (HardwareSpoof.FAKEPLATFORM == Platforms.WINDOWS ? HardwareSpoof.FAKEATIGPU.getDriver() : HardwareSpoof.currentMesa);
			case OFF -> GlDebugInfo.getVersion();
		};
	}
}
