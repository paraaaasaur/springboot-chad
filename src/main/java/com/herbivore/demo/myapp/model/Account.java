package com.herbivore.demo.myapp.model;

public class Account implements Cloneable{

	private String name;

	private String level;

	public Account() {}

	public Account(String name, String level) {
		this.name = name;
		this.level = level;
	}

	public String getName() {return name;}
	public void setName(String name) {this.name = name;}

	public String getLevel() {return level;}
	public void setLevel(String level) {this.level = level;}

	@Override
	public String toString() {
		return "Account{" +
			   "name='" + name + '\'' +
			   ", level='" + level + '\'' +
			   '}';
	}

	@Override
	public Account clone() {
		try {
			Account clone = (Account) super.clone();
			// TODO: copy mutable state here, so the clone can't change the internals of the original
			return clone;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
}
