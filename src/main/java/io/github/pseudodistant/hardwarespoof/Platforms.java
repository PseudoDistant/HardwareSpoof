package io.github.pseudodistant.hardwarespoof;

import net.minecraft.text.Text;

public enum Platforms {
	WINDOWS("Windows"),
	LINUX("Linux (Mesa)"),
	LINUXNONFREE("Linux (nonfree)");

	public final String name;
	Platforms(String name) {
		this.name = name;
	}

	public Text getName() {
		return Text.of(name);
	}
}
