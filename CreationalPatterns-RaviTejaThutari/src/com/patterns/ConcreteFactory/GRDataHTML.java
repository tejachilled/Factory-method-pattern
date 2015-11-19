package com.patterns.ConcreteFactory;

import com.patterns.Abstarct.IGradeFormator;
import com.patterns.Abstarct.IGradeGenerator;
import com.patterns.Abstarct.IReader;
import com.patterns.Abstarct.SelectReader;
import com.patterns.ConcreteClass.GradeFormator;
import com.patterns.ConcreteClass.GradeGenerator;
import com.patterns.ConcreteClass.XMLJSONReader;
import com.patterns.Data.Grade;

public class GRDataHTML extends SelectReader{

	IReader reader=new XMLJSONReader();
	IGradeGenerator gradeGenerator=new GradeGenerator();
	IGradeFormator gradeFormat = new GradeFormator();
	private final String TYPE = "GR";
	private final String FORMAT = "HTML";
 	@Override
	protected void ReaderMethod() throws Exception {
		Grade data=reader.getReader(TYPE);
		Grade newGrade = gradeGenerator.getGrade(data, TYPE);
		gradeFormat.formatGrade(newGrade, TYPE, FORMAT);
	}
}
