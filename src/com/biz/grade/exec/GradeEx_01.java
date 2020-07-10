package com.biz.grade.exec;

import java.util.Scanner;

import com.biz.grade.service.ScoreService;
import com.biz.grade.service.ScoreServiceV2;
import com.biz.grade.service.StudentService;
import com.biz.grade.service.StudentServiceV2;

public class GradeEx_01 {
	
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		
		StudentService stuService=new StudentServiceV2();
		ScoreService scoService=new ScoreServiceV2();
		
		scoService.scoreList();
		
		/*while(true) {
			System.out.println("\n<학생 성적 관리 시스템>");
			System.out.println("============================================");
			System.out.println("1.학생 정보 입력");
			System.out.println("2.성적 입력");
			System.out.println("3.성적일람표 출력");
			System.out.println("-1.업무종료");
			System.out.println("============================================");
			System.out.print("업무선택>> ");
			
			String strMenu=scan.nextLine();
			int intMenu=0;
			try {
				intMenu=Integer.valueOf(strMenu);
			} catch (Exception e) {
				System.out.println("메뉴 선택은 숫자로만 할 수 있습니다.");
				continue;
			}
			
			if(intMenu==-1) {
				break;
			} else if(intMenu<1||intMenu>3) {
				System.out.println("선택된 업무가 없습니다.");
				continue;
			}
			
			if(intMenu==1) {
				stuService.inputStudent();
			} else if(intMenu==2) {
				scoService.inputScore();
			} else if(intMenu==3) {
				scoService.scoreList();
			}
			
		}*/
		
	}

}
