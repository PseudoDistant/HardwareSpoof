package io.github.pseudodistant.hardwarespoof.cpus;

import net.minecraft.text.Text;

public enum CPUVendors {
	OFF("DISABLED"),
	AMD("AMD"),
	INTEL("Intel");

	public String name;

	CPUVendors(String name) {
		this.name = name;
	}

	public Text getName() {
		return Text.of(name);
	}
}
