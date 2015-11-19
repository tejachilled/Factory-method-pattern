package com.patterns.ConcreteClass;

import java.util.ArrayList;

import com.patterns.Abstarct.IGradeGenerator;
import com.patterns.Data.Grade;
import com.patterns.Data.Marks;

public class GradeGenerator extends IGradeGenerator{

	@Override
	public Grade getGrade(Grade data, String type) {

		if(type.equalsIgnoreCase("UG")){
			ArrayList<Marks> newMarks = data.marks;
			for(Marks marks : newMarks){
				int examsP = (data.getExams()*((marks.getMidTerm()+marks.getFinalExam())))/200;
				int assP = (marks.getA1()+marks.getA2())/10;
				int pAss = (2*marks.getPa1())/10;
				int total  = examsP+assP+pAss;
				String grade = getOrginalGrade(total);
				marks.setGrade(grade);
			}
		}else{
			ArrayList<Marks> newMarks = data.marks;
			for(Marks marks : newMarks){
				int examsP = (data.getExams()*(marks.getMidTerm()+marks.getFinalExam()))/200;
				int assP = (marks.getA1()+marks.getA2())/10;
				int pAss = (2*marks.getPa1())/10;
				int total  = examsP+assP+pAss;
				String grade = getOrginalGrade(total);
				marks.setGrade(grade);
			}
		}

		return data;
	}

	private String getOrginalGrade(int total) {
		if(total >=99){
			return "A+";
		}else if(total >=95){
			return "A";
		}else if(total >=90){
			return "A-";
		}else if(total >=87){
			return "B+";
		}else if(total >=84){
			return "B";
		}else if(total >=80){
			return "B-";
		}else if(total >=75){
			return "C+";
		}else if(total >=70){
			return "C";
		}else if(total >=60){
			return "D";
		}else{
			return "E";
		}
	}

}
