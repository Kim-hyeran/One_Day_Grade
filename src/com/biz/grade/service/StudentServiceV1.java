package com.biz.grade.service;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.biz.grade.vo.StudentVO;

public class StudentServiceV1 implements StudentService {
	
	protected List<StudentVO> stuList;
	protected Scanner scan;
	
	public StudentServiceV1() {
		stuList=new ArrayList<StudentVO>();
		scan=new Scanner(System.in);
	}

	@Override
	public boolean inputStudent() {
		System.out.print("학번(입력 종료 : END)>> ");
		String strNum=scan.nextLine();
		if(strNum.equals("END")) {
			return false;
		}
		try {
			strNum=String.format("%05d",Integer.valueOf(strNum));
		} catch (Exception e) {
			System.out.println("학번은 정수 5자리 값만 입력할 수 있습니다.");
			return false;
		}
		
		System.out.print("이름>> ");
		String strName=scan.nextLine();
		
		System.out.print("학과>> ");
		String strDept=scan.nextLine();
		
		System.out.print("학년>> ");
		String strGrade=scan.nextLine();
		int intGrade=0;
		try {
			intGrade=Integer.valueOf(strGrade);
		} catch (Exception e) {
			System.out.println("학년은 숫자로만 입력할 수 있습니다.");
		}
		
		System.out.print("전화번호>> ");
		String strTel=scan.nextLine();
		
		StudentVO stuVO=new StudentVO();
		
		stuVO.setNum(strNum);
		stuVO.setName(strName);
		stuVO.setDept(strDept);
		stuVO.setGrade(intGrade);
		stuVO.setTel(strTel);
		
		stuList.add(stuVO);
		
		this.studentSave();
		
		return true;
	}

	@Override
	public void studentSave() {
		String stuFile="src/com/biz/grade/exec/data/student.txt";
		
		PrintStream outPut=null;
		
		try {
			outPut=new PrintStream(stuFile);
			
			for (StudentVO stuVO : stuList) {
				outPut.printf("%s:%s:%s:%d:%s\n",
						stuVO.getNum(),
						stuVO.getName(),
						stuVO.getDept(),
						stuVO.getGrade(),
						stuVO.getTel());
			}
			outPut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("파일을 찾을 수 없습니다.");
		}
		
	}

}