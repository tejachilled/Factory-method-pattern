package com.patterns.Abstarct;

public abstract class SelectReader {
	
	protected abstract void ReaderMethod() throws Exception;
	
	public void performRead() throws Exception 
	{
		ReaderMethod();
	}
}
