package com.biz.grade.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.biz.grade.config.Position;
import com.biz.grade.vo.StudentVO;

public class StudentServiceV2 extends StudentServiceV1 {
	
	String sFileName="";
	
	public StudentServiceV2() {
		sFileName="src/com/biz/grade/exec/data/student.txt";
		this.loadStudent();
	}
	
	public void loadStudent() {
		stuList.clear();
		
		FileReader fileReader=null;
		BufferedReader buffer=null;
		
		try {
			fileReader=new FileReader(sFileName);
			buffer=new BufferedReader(fileReader);
			
			String reader="";
			
			while(true) {
				reader=buffer.readLine();
				
				if(reader==null) {
					break;
				}
				
				String[] students=reader.split(":");
				
				StudentVO stuVO=new StudentVO();
				stuVO.setNum(students[Position.STU_NUM]);
				stuVO.setName(students[Position.STU_NAME]);
				stuVO.setDept(students[Position.STU_DEPT]);
				stuVO.setGrade(Integer.valueOf(students[Position.STU_GRADE]));
				stuVO.setTel(students[Position.STU_TEL]);
				
				stuList.add(stuVO);
			}
			buffer.close();
			fileReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("학생 정보가 존재하지 않습니다.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
