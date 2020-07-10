package com.biz.grade.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.biz.grade.config.Position;
import com.biz.grade.vo.ScoreVO;

public class ScoreServiceV2 extends ScoreServiceV1 {
	
	String sFileName="";
	
	public ScoreServiceV2() {
		sFileName="src/com/biz/grade/exec/data/score.txt";
		this.loadScore();
	}
	
	public void loadScore() {
		scoList.clear();
		
		try {
			fileReader=new FileReader(sFileName);
			buffer=new BufferedReader(fileReader);
			
			String reader="";
			
			while(true) {
				reader=buffer.readLine();
				
				if(reader==null) {
					break;
				}
				
				String[] scores=reader.split(":");
				
				ScoreVO scoVO=new ScoreVO();
				scoVO.setNum(scores[Position.SCO_NUM]);
				scoVO.setKor(Integer.valueOf(scores[Position.SCO_KOR]));
				scoVO.setEng(Integer.valueOf(scores[Position.SCO_ENG]));
				scoVO.setMath(Integer.valueOf(scores[Position.SCO_MATH]));
				scoVO.setMusic(Integer.valueOf(scores[Position.SCO_MUSIC]));
				
				scoList.add(scoVO);
			}
			buffer.close();
			fileReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("성적 정보가 존재하지 않습니다.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
