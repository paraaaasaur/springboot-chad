package com.herbivore.demo.myapp.model.enums;

public enum OS {
	MACOS("macOS"),
	LINUX("Linux"),
	MICROSOFT_WINDOWS("Microsoft Windows"),
	SOLARIS("Solaris"),
	ANDROID_OS("Android OS"),
	IOS("iOS");

	private final String label;

	OS(String name) {
		this.label = name;
	}

	public String getLabel() {
		return label;
	}
}
