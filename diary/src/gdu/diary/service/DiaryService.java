package gdu.diary.service;

import java.util.*;

public class DiaryService { // diary 구현
	public Map<String, Object> getDiary(String targetYear, String targetMonth) {
		// 타겟 년, 월, 일 (날짜)
		// 타겟의 1일
		// 타겟의 마지막 일의 숫자 -> endDay
		
		// startBlank + endDay + (7-(startBlank + endDay)%7) (0~6)
		// 전체 셀의 개수 (마지막일의 숫자보다는 크고, 7로 나누어 떨어져야 함)
		// 앞의 빈 셀의 개수 -> startBlank
		// 이번 달 숫자가 나와야 할 셀의 개수 (1~마지막 날짜)
		// 뒤의 빈 셀의 개수 -> endBlank -> 7-(startBlank + endDay)%7
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar target = Calendar.getInstance();
		
		if(targetYear != null) {
			target.set(Calendar.YEAR, Integer.parseInt(targetYear));
		}
		if(targetMonth != null) {
			// 두번째 인수값이 -1이면 target의 년을 -1하고 월은 11(12월을 가리킴)값이 들어간다.
			// 두번째 인수값이 12이면 target의 년을 +1하고 월은 0(1월을 가리킴)값이 들어간다.
			target.set(Calendar.MONTH, Integer.parseInt(targetMonth)); 
		}
		
		/*
		int numTargetMonth = 0;
		int numTargetYear = 0;
		if(targetMonth != null && targetYear != null) {
			numTargetYear = Integer.parseInt(targetYear);
			numTargetMonth = Integer.parseInt(targetMonth);
			if(numTargetMonth == 0) {
				numTargetYear = numTargetYear - 1;
				numTargetMonth = 12;
			} else if(numTargetMonth == 13) {
				numTargetYear = numTargetYear + 1;
				numTargetMonth = 1;
			}
			target.set(Calendar.YEAR, numTargetYear);
			target.set(Calendar.MONTH, numTargetMonth-1);
		}
		*/
		
		target.set(Calendar.DATE, 1);
		// target 월의 1 숫자 앞에 와야 할 빈 셀의 개수
		int startBlank = target.get(Calendar.DAY_OF_WEEK) - 1;
		// target 월의 마지막 날짜
		int endDay = target.getActualMaximum(Calendar.DATE);
		int endBlank = 0;
		// target 월의 마지막 날 뒤에 와야 할 빈 셀의 개수
		if ((startBlank + endDay) % 7 != 0) {
			endBlank = 7-((startBlank + endDay) % 7);
		}
		
		// int totalCell = startBlank + endDay + endBlank;
		
		map.put("targetYear", target.get(Calendar.YEAR));
		map.put("targetMonth", target.get(Calendar.MONTH));
		map.put("startBlank", startBlank);
		map.put("endDay", endDay);
		map.put("endBlank", endBlank);
		
		return map;
	}
}
