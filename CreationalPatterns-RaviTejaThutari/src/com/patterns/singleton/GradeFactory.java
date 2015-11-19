package com.patterns.singleton;

import com.patterns.Abstarct.SelectReader;
import com.patterns.ConcreteFactory.GRDataCSV;
import com.patterns.ConcreteFactory.GRDataHTML;
import com.patterns.ConcreteFactory.GRDataXML;
import com.patterns.ConcreteFactory.UGDataCSV;
import com.patterns.ConcreteFactory.UGDataHTML;
import com.patterns.ConcreteFactory.UGDataXML;

public class GradeFactory {
	private static  GradeFactory object;

	private GradeFactory() {

	}

	public static GradeFactory getInstance() {
		if (object == null) {
			object = new GradeFactory(); // Create the object for the first and last time
		}
		return object;
	}
	public void grade(String sType,String format) throws Exception{
		SelectReader reader;
		if(sType.equalsIgnoreCase("UG")){
			if(format.equalsIgnoreCase("XML")){
				reader = new UGDataXML();
				reader.performRead();
			}else if(format.equalsIgnoreCase("CSV")){
				reader = new UGDataCSV();
				reader.performRead();
			}else if(format.equalsIgnoreCase("HTML")){
				reader = new UGDataHTML();
				reader.performRead();
			}
		}else if(sType.equalsIgnoreCase("GR")){
			if(format.equalsIgnoreCase("XML")){
				reader = new GRDataXML();
				reader.performRead();
			}else if(format.equalsIgnoreCase("CSV")){
				reader = new GRDataCSV();
				reader.performRead();
			}else if(format.equalsIgnoreCase("HTML")){
				reader = new GRDataHTML();
				reader.performRead();
			}
		}
	}
}
