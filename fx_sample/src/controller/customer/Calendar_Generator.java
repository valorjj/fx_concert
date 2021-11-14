package controller.customer;

import java.util.Calendar;

public class Calendar_Generator {

	@SuppressWarnings("static-access")
	public static void get_calendar(int year, int month) {

		Calendar calendar = Calendar.getInstance();

		calendar.set(year, month - 1, 1);

		int sweek = calendar.get(Calendar.DAY_OF_WEEK);

		int eday = calendar.getActualMaximum(calendar.DAY_OF_MONTH);

		// 4. 현재 월 1일의 위치 앞에 공백 채우기
		for (int i = 1; i < sweek; i++) {
			System.out.print(" \t");
		}
		// 5. 1일부터 마지막 날까지 출력
		for (int i = 1; i <= eday; i++) {
			System.out.print(i + "\t");
			// 요일이 토요일마다 줄바꿈 [ sweek 7의 배수 마다 ]
			if (sweek % 7 == 0)
				System.out.println();
			sweek++; // 요일 증가
		}

	}

	private static Calendar_Generator instance;

	public static Calendar_Generator getinstance() {
		return instance;
	}

	public Calendar_Generator() {
		instance = this;
	}

}
