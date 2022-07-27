package io.github.pseudodistant.hardwarespoof.gpus;

import net.minecraft.text.Text;

public enum GPUVendors {
	OFF("DISABLED"),
	ATI("ATI Technologies Inc"),
	NVIDIA("NVIDIA Corporation"),
	AMD("AMD"),
	INTEL("Intel");

	public final String brand;
	GPUVendors(String brand) {
		this.brand = brand;
	}

	public Text getBrand() {
		return Text.of(brand);
	}
}
