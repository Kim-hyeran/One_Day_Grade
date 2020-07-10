package com.biz.grade.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.ObjectInputStream.GetField;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.biz.grade.vo.ScoreVO;
import com.biz.grade.vo.StudentVO;

public class ScoreServiceV1 implements ScoreService {

	protected List<ScoreVO> scoList;
	protected Scanner scan;
	protected FileReader fileReader=null;
	protected BufferedReader buffer=null;
	
	public ScoreServiceV1() {
		scoList=new ArrayList<ScoreVO>();
		scan=new Scanner(System.in);
	}

	@Override
	public boolean inputScore() {
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
		
		System.out.print("국어>> ");
		String strKor=scan.nextLine();
		int intKor=0;
		try {
			intKor=Integer.valueOf(strKor);
		} catch (Exception e) {
			System.out.println("점수는 숫자로만 입력할 수 있습니다.");
			System.out.println("학번부터 다시 입력하시기 바랍니다.");
			return true;
		}
		
		System.out.print("영어>> ");
		String strEng=scan.nextLine();
		int intEng=0;
		try {
			intEng=Integer.valueOf(strEng);
		} catch (Exception e) {
			System.out.println("점수는 숫자로만 입력할 수 있습니다.");
			System.out.println("학번부터 다시 입력하시기 바랍니다.");
			return true;
		}
		
		System.out.print("수학>> ");
		String strMath=scan.nextLine();
		int intMath=0;
		try {
			intMath=Integer.valueOf(strMath);
		} catch (Exception e) {
			System.out.println("점수는 숫자로만 입력할 수 있습니다.");
			System.out.println("학번부터 다시 입력하시기 바랍니다.");
			return true;
		}
		
		System.out.print("음악>> ");
		String strMusic=scan.nextLine();
		int intMusic=0;
		try {
			intMusic=Integer.valueOf(strMusic);
		} catch (Exception e) {
			System.out.println("점수는 숫자로만 입력할 수 있습니다.");
			System.out.println("학번부터 다시 입력하시기 바랍니다.");
			return true;
		}
		
		ScoreVO scoVO=new ScoreVO();
		
		scoVO.setNum(strNum);
		scoVO.setKor(intKor);
		scoVO.setEng(intEng);
		scoVO.setMath(intMath);
		scoVO.setMusic(intMusic);
		
		scoList.add(scoVO);
		
		this.scoreSave();
		
		return true;
	}
	
	@Override
	public void calcSum() {
		int sum=0;
		
		for(ScoreVO scoVO : scoList) {
			sum=scoVO.getKor()+scoVO.getEng()+scoVO.getMath()+scoVO.getMusic();
			
			scoVO.setSum(sum);
		}
		
	}

	@Override
	public void calcAvg() {
		int avg=0;
		
		for(ScoreVO scoVO : scoList) {
			avg=scoVO.getSum()/4;
			
			scoVO.setAvg(avg);
		}
	}

	@Override
	public void scoreList() {
		this.calcSum();
		this.calcAvg();
		
		int korSum=0;
		int engSum=0;
		int mathSum=0;
		int musicSum=0;
		int sumSum=0;
		int avgAvg=0;
		
		int size=scoList.size();
		
		System.out.println("\t\t\t<성적일람표>");
		System.out.println("==============================================================");
		System.out.println("학번\t이름\t국어\t영어\t수학\t음악\t총점\t평균");
		System.out.println("--------------------------------------------------------------");
		
		
		for (ScoreVO scoVO : scoList) {
			System.out.print(scoVO.getNum()+"\t");

			//이름
			
			System.out.print(scoVO.getKor()+"\t");
			System.out.print(scoVO.getEng()+"\t");
			System.out.print(scoVO.getMath()+"\t");
			System.out.print(scoVO.getMusic()+"\t");
			System.out.print(scoVO.getSum()+"\t");
			System.out.println(scoVO.getAvg());
		}
		
		for (ScoreVO scoVO : scoList) {
			korSum+=scoVO.getKor();
			engSum+=scoVO.getEng();
			mathSum+=scoVO.getMath();
			musicSum+=scoVO.getMusic();
			sumSum+=scoVO.getSum();
			avgAvg+=scoVO.getAvg();
		}
		
		System.out.println("--------------------------------------------------------------");
		System.out.print("과목총점\t");
		System.out.print(korSum+"\t");
		System.out.print(engSum+"\t");
		System.out.print(mathSum+"\t");
		System.out.print(musicSum+"\t");
		System.out.println(sumSum);
		
		System.out.print("과목평균\t");
		System.out.print(korSum/size+"\t");
		System.out.print(engSum/size+"\t");
		System.out.print(mathSum/size+"\t");
		System.out.print(musicSum/size+"\t\t");
		System.out.println(avgAvg/size);
		System.out.println("==============================================================");
		System.out.println("출력 완료. 아무 키나 누르십시오.");
		scan.nextLine();
	}

	@Override
	public void scoreSave() {
		String scoFile="src/com/biz/grade/exec/data/score.txt";
		
		PrintStream outPut=null;
		
		try {
			outPut=new PrintStream(scoFile);
			
			for (ScoreVO scoVO : scoList) {
				outPut.printf("%s:%d:%d:%d:%d\n",
						scoVO.getNum(),
						scoVO.getKor(),
						scoVO.getEng(),
						scoVO.getMath(),
						scoVO.getMusic());
			}
			outPut.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("파일을 찾을 수 없습니다.");
		}
		
	}
	
}
