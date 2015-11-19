package com.patterns.ConcreteClass;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.patterns.Abstarct.IGradeFormator;
import com.patterns.Data.Grade;
import com.patterns.Data.Marks;

public class GradeFormator extends IGradeFormator{

	public String path() {
		String filepath = String.format("%s", System.getProperty("user.dir"), System.getProperty("user.dir").replace(".", "/"));
		System.out.println(filepath+"\\OutputFiles");
		return filepath+"\\OutputFiles";
	}

	@Override
	public void formatGrade(Grade grade, String sType, String format) {
		// TODO Auto-generated method stub
		try {
			if(format.equalsIgnoreCase("XML")){
				generateXML(grade,sType);
			}else if(format.equalsIgnoreCase("HTML")){
				generateHTML(grade,sType);
			}else if(format.equalsIgnoreCase("CSV")){
				generateCSV(grade,sType);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void generateCSV(Grade grade, String sType) {
		 final String COMMA_DELIMITER = ",";
		 final String NEW_LINE_SEPARATOR = "\n";
		 final String FILE_HEADER = "Name,ID,Mid Term,Final,Abbott,CRC,Creational Patterns,Grade";
			FileWriter fileWriter = null;
			
			try {
				fileWriter = new FileWriter(path()+"\\"+sType+"GradeHTML.csv");

				//Write the CSV file header
				fileWriter.append(FILE_HEADER.toString());
				
				//Add a new line separator after the header
				fileWriter.append(NEW_LINE_SEPARATOR);
				
				//Write a new student object list to the CSV file
				for (Marks student : grade.getMarks()) {
					fileWriter.append(String.valueOf(student.getName()));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(student.getId());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(""+student.getMidTerm());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(""+student.getFinalExam());
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(student.getA1()));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(student.getA2()));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(student.getPa1()));
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(String.valueOf(student.getGrade()));
					fileWriter.append(NEW_LINE_SEPARATOR);
				}				
				
				System.out.println("CSV file was created successfully !!!");
				
			} catch (Exception e) {
				System.out.println("Error in CsvFileWriter !!!");
				e.printStackTrace();
			} finally {
				
				try {
					fileWriter.flush();
					fileWriter.close();
				} catch (IOException e) {
					System.out.println("Error while flushing/closing fileWriter !!!");
	                e.printStackTrace();
				}
				
			}

		
	}

	private void generateHTML(Grade grade, String sType) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("HTML file created");
		Writer output = null;
		File file = new File(path()+"\\"+sType+"GradeHTML.html");
		output = new BufferedWriter(new FileWriter(file));
		output.write("<HTML><HEAD>	<TITLE>Grades</TITLE></HEAD><BODY>	");
		output.write("<table border='1' style='width:100%'> <tr>");
		output.write(" <td> Name </td> ");
		output.write("<td> ID </td>");
		output.write("<td> Mid Term </td>");
		output.write("<td> Final </td>");
		output.write("<td> Abbott </td>");
		output.write("<td> CRC </td>");
		output.write("<td> Creational Patterns </td>");
		output.write("<td> Grade </td> </tr>");

		for(Marks marks : grade.getMarks()){
			output.write("<tr>");
			output.write(" <td> "+marks.getName()+" </td> ");
			output.write("<td> "+marks.getId()+" </td>");
			output.write("<td> "+marks.getMidTerm()+" </td>");
			output.write("<td> "+marks.getFinalExam()+" </td>");
			output.write("<td> "+marks.getA1()+" </td>");
			output.write("<td> "+marks.getA2()+" </td>");
			output.write("<td> "+marks.getPa1()+" </td>");
			output.write("<td> "+marks.getGrade()+" </td> </tr>");

		}

		output.write("</table></BODY></HTML>");
		output.close();
	}

	private void generateXML(Grade grade, String sType) {
		// TODO Auto-generated method stub

		System.out.println("XML file created");			

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = null;
		try {
			docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("GradeBook");
			doc.appendChild(rootElement);
			Attr attr = doc.createAttribute("class");
			attr.setValue("CSE 598");
			rootElement.setAttributeNode(attr);

			Element Grades = doc.createElement("Grades");
			rootElement.appendChild(Grades);

			for(Marks marks : grade.getMarks()){
				Element student = doc.createElement("Student");
				Grades.appendChild(student);

				Element Name = doc.createElement("Name");
				Name.appendChild(doc.createTextNode(marks.getName()));
				student.appendChild(Name);

				Element ID = doc.createElement("ID");
				ID.appendChild(doc.createTextNode(marks.getId()));
				student.appendChild(ID);

				for(int i=0;i<3;i++){
					Element AssignedWork = doc.createElement("AssignedWork");
					attr = doc.createAttribute("category");
					if(i==0){							
						attr.setValue("Exams");
						AssignedWork.setAttributeNode(attr);
						student.appendChild(AssignedWork);
						for(int j=0;j<2;j++){
							Element GradedWork = doc.createElement("GradedWork");
							AssignedWork.appendChild(GradedWork);
							if(j==0){
								Element assignName = doc.createElement("Name");
								assignName.appendChild(doc.createTextNode("Mid Term"));
								GradedWork.appendChild(assignName);

								Element Grade = doc.createElement("Grade");
								Grade.appendChild(doc.createTextNode(""+marks.getMidTerm()));
								GradedWork.appendChild(Grade);
							}else if(j==1){
								Element assignName = doc.createElement("Name");
								assignName.appendChild(doc.createTextNode("Final"));
								GradedWork.appendChild(assignName);

								Element Grade = doc.createElement("Grade");
								Grade.appendChild(doc.createTextNode(""+marks.getFinalExam()));
								GradedWork.appendChild(Grade);
							}

						}

					}else if(i==1){
						attr.setValue("Assignments");
						AssignedWork.setAttributeNode(attr);
						student.appendChild(AssignedWork);
						for(int j=0;j<2;j++){
							Element GradedWork = doc.createElement("GradedWork");
							AssignedWork.appendChild(GradedWork);
							if(j==0){
								Element assignName = doc.createElement("Name");
								assignName.appendChild(doc.createTextNode("Abbott"));
								GradedWork.appendChild(assignName);

								Element Grade = doc.createElement("Grade");
								Grade.appendChild(doc.createTextNode(""+marks.getA1()));
								GradedWork.appendChild(Grade);
							}else if(j==1){
								Element assignName = doc.createElement("Name");
								assignName.appendChild(doc.createTextNode("CRC"));
								GradedWork.appendChild(assignName);

								Element Grade = doc.createElement("Grade");
								Grade.appendChild(doc.createTextNode(""+marks.getA2()));
								GradedWork.appendChild(Grade);
							}

						}
					}
					else if(i==2){
						attr.setValue("Programming Assignments");
						AssignedWork.setAttributeNode(attr);
						student.appendChild(AssignedWork);

						Element GradedWork = doc.createElement("GradedWork");
						AssignedWork.appendChild(GradedWork);

						Element assignName = doc.createElement("Name");
						assignName.appendChild(doc.createTextNode("Creational Patterns"));
						GradedWork.appendChild(assignName);

						Element Grade = doc.createElement("Grade");
						Grade.appendChild(doc.createTextNode(""+marks.getPa1()));
						GradedWork.appendChild(Grade);
					}
				}

				Element LetterGrade = doc.createElement("LetterGrade");
				LetterGrade.appendChild(doc.createTextNode(marks.getGrade()));
				student.appendChild(LetterGrade);

			}
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(path()+"\\"+sType+"GradeXML.xml"));

			transformer.transform(source, result);


		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
