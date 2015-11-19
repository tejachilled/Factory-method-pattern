package com.patterns.Abstarct;

import com.patterns.Data.Grade;
public abstract class IGradeGenerator {
	
	abstract public Grade getGrade(Grade data, String type);

}
