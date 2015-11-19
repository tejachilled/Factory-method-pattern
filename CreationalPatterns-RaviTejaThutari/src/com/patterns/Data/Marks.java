package com.patterns.Data;

public class Marks {

	public String name;
	public String id;
	public int midTerm;
	public int finalExam;
	public int a1;
	public int a2;
	public int pa1;
	public String grade;
	
	public Marks(String name, String id, int midTerm, int finalExam, int a1, int a2, int pa1, String grade) {
		this.name = name;
		this.id = id;
		this.midTerm = midTerm;
		this.finalExam = finalExam;
		this.a1 = a1;
		this.a2 = a2;
		this.pa1 = pa1;
		this.grade = grade;
	}

	public Marks() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public int getMidTerm() {
		return midTerm;
	}

	public int getFinalExam() {
		return finalExam;
	}

	public int getA1() {
		return a1;
	}

	public int getA2() {
		return a2;
	}

	public int getPa1() {
		return pa1;
	}

	public String getGrade() {
		return grade;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setMidTerm(int midTerm) {
		this.midTerm = midTerm;
	}

	public void setFinalExam(int finalExam) {
		this.finalExam = finalExam;
	}

	public void setA1(int a1) {
		this.a1 = a1;
	}

	public void setA2(int a2) {
		this.a2 = a2;
	}

	public void setPa1(int pa1) {
		this.pa1 = pa1;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Marks [name=").append(name).append(", id=").append(id).append(", midTerm=").append(midTerm)
				.append(", finalExam=").append(finalExam).append(", a1=").append(a1).append(", a2=").append(a2)
				.append(", pa1=").append(pa1).append(", grade=").append(grade).append("]");
		return builder.toString();
	}
	
	
}
