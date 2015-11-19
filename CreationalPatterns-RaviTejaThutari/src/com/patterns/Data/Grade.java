package com.patterns.Data;

import java.util.ArrayList;

public class Grade {
	
	public int exams ;
	public int assignments;
	public int programming;
	public int termPaper;
	public ArrayList<Marks> marks;

	public Grade(int exams,int assig,int prog)
	{
		this.exams=exams;
		this.assignments=assig;
		this.programming=prog;
	}

	public Grade() {
		// TODO Auto-generated constructor stub
	}

	public int getExams() {
		return exams;
	}

	public int getAssignments(){
		return assignments;
	}

	public int getProgramming() {
		return programming;
	}

	public ArrayList<Marks> getMarks() {
		return marks;
	}

	public void setExams(int exams) {
		this.exams = exams;
	}

	public void setAssignments(int assignments) {
		this.assignments = assignments;
	}

	public void setProgramming(int programming) {
		this.programming = programming;
	}

	public void setMarks(ArrayList<Marks> marks) {
		this.marks = marks;
	}
	
	public int getTermPaper() {
		return termPaper;
	}

	public void setTermPaper(int termPaper) {
		this.termPaper = termPaper;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Xml [exams=").append(exams).append(", assignments=").append(assignments)
				.append(", programming=").append(programming).append(", termPaper=").append(termPaper)
				.append(", marks=").append(marks).append("]");
		return builder.toString();
	}


	
}
