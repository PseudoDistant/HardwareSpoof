package io.github.pseudodistant.hardwarespoof.cpus;

import io.github.pseudodistant.hardwarespoof.gpus.IntelGPUs;
import net.minecraft.text.Text;

public enum IntelCPUs {
	/* Core 2 */
	C2DT8100("2x Intel(R) Core(TM) 2 Duo CPU T8100 @ 2.10GHz", "Core 2 Duo T8100"),

	/* Core Modern */
	CI3550("4x Intel(R) Core(TM) i3 CPU 550 @3.20GHz", "Core i3 550"),
	CI5750("4x Intel(R) Core(TM) i5 CPU 750 @2.67GHz", "Core i5 750"),
	CI54210M("4x Intel(R) Core(TM) i5-4210M CPU @2.90GHz", "Core i5 4210M"),
	CI36100("4x Intel(R) Core(TM) i3-6100 CPU @3.70GHz", "Core i3 6100"),
	;

	public String name;
	public String shortName;
	IntelCPUs(String name, String shortName) {
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
