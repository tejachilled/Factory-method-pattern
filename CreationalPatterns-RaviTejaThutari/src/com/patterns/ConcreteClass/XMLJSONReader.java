package com.patterns.ConcreteClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.patterns.Abstarct.IReader;
import com.patterns.Data.Marks;
import com.patterns.Data.Grade;

public class XMLJSONReader extends IReader {

	private static Grade grade = new Grade();

	@Override
	public Grade getReader(String studentType) throws Exception {
		// TODO Auto-generated method stub

		if(studentType.equalsIgnoreCase("UG")){
			readJSON();
		}else if(studentType.equalsIgnoreCase("GR")){
			readXML();
		}
		return grade;
	}

	private void readJSON() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader fReader = new FileReader("InputFiles/UndergradData.json");
		JSONObject obj  = (JSONObject) parser.parse(fReader);
		JSONObject gardeBook  = (JSONObject) obj.get("GradeBook"); 
		JSONObject GradingSchema  = (JSONObject) gardeBook.get("GradingSchema"); 
		System.out.println(gardeBook.toJSONString());
		System.out.println(GradingSchema.toJSONString());
		JSONArray GradeItem1  = (JSONArray) GradingSchema.get("GradeItem"); 
		Iterator<JSONObject> itr = GradeItem1.iterator();
		while(itr.hasNext()){
			JSONObject scheme = (JSONObject) itr.next();
			String category = (String) scheme.get("Category");
			String percentage = (String) scheme.get("Percentage");
			if(category.equalsIgnoreCase("Exams")){
				grade.setExams(Integer.parseInt(percentage));
			}else if(category.equalsIgnoreCase("Assignments")){
				grade.setAssignments(Integer.parseInt(percentage));
			}else if(category.equalsIgnoreCase("Programming Assignments")){
				grade.setProgramming(Integer.parseInt(percentage));
			}else if(category.equalsIgnoreCase("Term Paper")){
				grade.setTermPaper(Integer.parseInt(percentage));
			}	
		}

		JSONObject Grades  = (JSONObject) gardeBook.get("Grades"); 
		JSONArray students  = (JSONArray) Grades.get("Student");
		Iterator<JSONObject> iterator = students.iterator();
		ArrayList<Marks> allMarks = new ArrayList<Marks>();
		Marks marks = null;
		while(iterator.hasNext()){
			marks = new Marks();
			JSONObject info = (JSONObject) iterator.next();
			String name = (String) info.get("Name");
			String id = (String) info.get("ID");
			marks.setId(id);marks.setName(name);
			System.out.println("name: "+name+ " ID: "+id);
			JSONArray assignedWork  = (JSONArray) info.get("AssignedWork");
			Iterator<JSONObject> iterate = assignedWork.iterator();
			while(iterate.hasNext()){
				JSONObject grades = (JSONObject) iterate.next();
				String category = (String) grades.get("-category");
				if(category.equalsIgnoreCase("Exams") || category.equalsIgnoreCase("Assignments")){
					JSONArray gradedWork  = (JSONArray) grades.get("GradedWork");
					Iterator<JSONObject> ite = gradedWork.iterator();
					while(ite.hasNext()){
						JSONObject grd = (JSONObject) ite.next();
						String val = (String) grd.get("Grade");
						String markName = (String) grd.get("Name");
						System.out.println("grade: "+val+" name: "+markName);
						if(markName.equalsIgnoreCase("Mid Term")){
							marks.setMidTerm(Integer.parseInt(val));
						}else if(markName.equalsIgnoreCase("Final")){
							marks.setFinalExam(Integer.parseInt(val));
						}else if(markName.equalsIgnoreCase("Abbott")){
							marks.setA1(Integer.parseInt(val));
						}else if(markName.equalsIgnoreCase("CRC")){
							marks.setA2(Integer.parseInt(val));
						}
					}
				}else if(category.equalsIgnoreCase("Programming Assignments")){
					JSONObject grd = (JSONObject) grades.get("GradedWork");
					String percentage = (String) grd.get("Grade");
					System.out.println("creational patterns marks : "+percentage);
					marks.setPa1(Integer.parseInt(percentage));
				}
			}
			allMarks.add(marks);
		}
		grade.setMarks(allMarks);

	}

	private void readXML() {
		try {
			File XmlFile = new File("InputFiles/GradData.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(XmlFile);
			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("GradeItem");

			System.out.println("Grading Category");			
			grade = getGradingCategoryXML(grade,nList);

			nList = doc.getElementsByTagName("Student");
			ArrayList<Marks> studentMarks = getMarksFromXML(nList);
			grade.setMarks(studentMarks);
			System.out.println(grade.getMarks().get(0));
			System.out.println(grade.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ArrayList<Marks> getMarksFromXML(NodeList nList) {
		ArrayList<Marks> allMarks = new ArrayList<Marks>();
		Marks students = null;
		for (int temp = 0; temp < nList.getLength(); temp++) {
			students = new Marks();
			Node nNode = nList.item(temp);					
			System.out.println("\nCurrent Element :" + nNode.getNodeName());						
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				String name = eElement.getElementsByTagName("Name").item(0).getTextContent();
				String id = eElement.getElementsByTagName("ID").item(0).getTextContent();
				System.out.println("Name: "+name + " id: "+id);
				students.setId(id);students.setName(name);
				NodeList childList =  eElement.getElementsByTagName("AssignedWork");
				for (int i = 0; i < childList.getLength(); i++) {
					Node childNode = childList.item(i);
					if (childNode.getNodeType() == Node.ELEMENT_NODE) {
						Element ele = (Element) childNode;
						NodeList childList2 = ele.getElementsByTagName("GradedWork");
						for (int j = 0; j < childList2.getLength(); j++) {
							Node childNode2 = childList2.item(j);
							if (childNode2.getNodeType() == Node.ELEMENT_NODE) {
								Element el = (Element) childNode2;
								String markName = el.getElementsByTagName("Name").item(0).getTextContent();
								String val = el.getElementsByTagName("Grade").item(0).getTextContent();
								System.out.println("Name : "+markName);
								System.out.println("marks: "+val);
								if(markName.equalsIgnoreCase("Mid Term")){
									students.setMidTerm(Integer.parseInt(val));
								}else if(markName.equalsIgnoreCase("Final")){
									students.setFinalExam(Integer.parseInt(val));
								}else if(markName.equalsIgnoreCase("Abbott")){
									students.setA1(Integer.parseInt(val));
								}else if(markName.equalsIgnoreCase("CRC")){
									students.setA2(Integer.parseInt(val));
								}else if(markName.equalsIgnoreCase("Creational Patterns")){
									students.setPa1(Integer.parseInt(val));
									System.out.println("get pal : "+students.getPa1());
								}
							}
						}
					}
				}
			}
			System.out.println("Students: "+students.toString());
			allMarks.add(students);
		}

		return allMarks;
	}



	private Grade getGradingCategoryXML(Grade grade,NodeList nList){
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node nNode = nList.item(temp);						
			System.out.println("\nCurrent Element :" + nNode.getNodeName());						
			if (nNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nNode;
				String category = eElement.getElementsByTagName("Category").item(0).getTextContent();
				String percentage = eElement.getElementsByTagName("Percentage").item(0).getTextContent();
				System.out.println("Category : " + category);
				System.out.println("Percentage : " + percentage);
				if(category.equalsIgnoreCase("Exams")){
					grade.setExams(Integer.parseInt(percentage));
				}else if(category.equalsIgnoreCase("Assignments")){
					grade.setAssignments(Integer.parseInt(percentage));
				}else if(category.equalsIgnoreCase("Programming Assignments")){
					grade.setProgramming(Integer.parseInt(percentage));
				}else if(category.equalsIgnoreCase("Term Paper")){
					grade.setTermPaper(Integer.parseInt(percentage));
				}					
			}
		}
		return grade;
	}

	
}
