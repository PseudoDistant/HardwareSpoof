package io.github.pseudodistant.hardwarespoof.cpus;

import net.minecraft.text.Text;

public enum AMDCPUs {
	/* A-Series */
	A86410("4x AMD A8-6410 APU with AMD Radeon R5 Graphics", "A8-6410"),

	/* Ryzen */
	R1400("8x AMD Ryzen 5 1400 with AMD Radeon Vega Graphics", "Ryzen 5 1400"),
	R1600("12x AMD Ryzen 5 1600 Six-core Processor", "Ryzen 5 1600"),
	R1700("16x AMD Ryzen 7 1700 Eight-core Processor", "Ryzen 7 1700"),
	R1700X("16x AMD Ryzen 7 1700X Eight-core Processor", "Ryzen 7 1700X");

	public final String name;
	public final String shortName;
	AMDCPUs(String name, String shortName) {
		this.name = name;
		this.shortName = shortName;
	}

	public String getName() {
		return name;
	}

	public Text getShortName() {
		return Text.of(shortName);
	}
}
