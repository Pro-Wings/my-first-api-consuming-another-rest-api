package com.prowings.model;

public class Subject {
	
	int id;
	String name;
	String teacher;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", teacher=" + teacher + "]";
	}

}
