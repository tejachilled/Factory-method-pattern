package com.patterns.Abstarct;

import com.patterns.Data.Grade;

public abstract class IReader {
	
		abstract public Grade getReader(String string) throws Exception;
}
