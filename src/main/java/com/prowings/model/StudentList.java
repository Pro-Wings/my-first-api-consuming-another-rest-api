package com.prowings.model;

import java.util.List;

public class StudentList {
	
	private List<Student> students;

	public StudentList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StudentList(List<Student> students) {
		super();
		this.students = students;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "StudentList [students=" + students + "]";
	}
	
}
