package com.herbivore.demo.myapp.util;

import com.herbivore.demo.myapp.entity.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Trivial {
	public static final List<Employee> DACREW;
	public static final String EMAIL_REGEX =
			// Local part
			"^[a-zA-Z0-9](?:[a-zA-Z0-9._-]{0,18}[a-zA-Z0-9])?" +
			// @
			"@" +
			// Domain name
			"[a-z](?:[a-z-]*[a-z])?" +
			// Subdomains
			"(?:\\.[a-z](?:[a-z-]*[a-z])?)*" +
			// TLD (top-level domain)
			"\\.(?:com|edu|org|co|io|net|io|gov)" +
			// Country code (optional)
			"(?:\\.(?:tw|jp|au|de|id|uk))?$";;
	static {
		{
			DACREW = new ArrayList<>(List.of(
					// Ark: Survival Evolved
					new Employee("Rockwell", "Edmund", "elixirlover@ark.com"),
					new Employee("Helena", "Walker", "dossiers@ark.com"),
					new Employee("Mei-Yin", "Li", "beastqueen@ark.com"),
					new Employee("Gaius", "Nerva", "romanking@ark.com"),
					new Employee("John", "Dahkeya", "shadowhawk@ark.com"),
					new Employee("Diana", "Altaras", "tekqueen@ark.com"),
					new Employee("Santiago", "Cabrera", "mechgenius@ark.com"),
					new Employee("Raia", "Vann", "wyvernrider@ark.com"),
					new Employee("Sir", "Edmund", "aberrantmadman@ark.com"),
					new Employee("HLN-A", "AI", "notasussybot@ark.com"),

					// Minecraft
					new Employee("Steve", "Crafter", "blockyboi@minecraft.net"),
					new Employee("Alex", "Builder", "redstonelover@minecraft.net"),
					new Employee("Herobrine", "Unknown", "👀@minecraft.net"),
					new Employee("Villager", "Hmmm", "hmmm@minecraft.net"),
					new Employee("Creeper", "Boom", "sssss@minecraft.net"),
					new Employee("Ender", "Dragon", "finalboss@minecraft.net"),
					new Employee("Wither", "Boss", "spooky@minecraft.net"),
					new Employee("Notch", "Persson", "goldenapple@mojang.com"),
					new Employee("Jeb", "Bergensten", "rainbowsheep@mojang.com"),
					new Employee("Piglin", "Barter", "goldaddict@minecraft.net"),

					// Harry Potter
					new Employee("Harry", "Potter", "thechosenone@hogwarts.edu"),
					new Employee("Hermione", "Granger", "booksbeforelooks@hogwarts.edu"),
					new Employee("Ron", "Weasley", "poorbutproud@hogwarts.edu"),
					new Employee("Draco", "Malfoy", "myfatherwillhear@hogwarts.edu"),
					new Employee("Severus", "Snape", "always@hogwarts.edu"),
					new Employee("Albus", "Dumbledore", "lemon_sherbet@hogwarts.edu"),
					new Employee("Sirius", "Black", "notadeathmuncher@hogwarts.edu"),
					new Employee("Voldemort", "Riddle", "noseless@darklord.com"),
					new Employee("Dobby", "Elf", "freedom@houseelf.com"),
					new Employee("Hagrid", "Rubeus", "bigfriendlyhalfgiant@hogwarts.edu")
			));
			Collections.shuffle(DACREW);
		}
	}

}
