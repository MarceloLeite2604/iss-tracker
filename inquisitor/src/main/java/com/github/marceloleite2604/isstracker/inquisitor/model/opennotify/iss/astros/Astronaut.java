package com.github.marceloleite2604.isstracker.inquisitor.model.opennotify.iss.astros;

public class Astronaut {

	private String name;
	
	private String craft;

	public String getName() {
		return name;
	}

	public String getCraft() {
		return craft;
	}

	@Override
	public String toString() {
		return "Astronaut [name=" + name + ", craft=" + craft + "]";
	}
}
