package com.github.marceloleite2604.isstracker.inquisitor.model.opennotify.iss.astros;

import java.util.List;

public class AstrosResponse {

	private String message;
	
	private int number;
	
	private List<Astronaut> people;

	public String getMessage() {
		return message;
	}

	public int getNumber() {
		return number;
	}

	public List<Astronaut> getPeople() {
		return people;
	}

	@Override
	public String toString() {
		return "AstrosResponse [message=" + message + ", number=" + number + ", people=" + people
				+ "]";
	}
}
