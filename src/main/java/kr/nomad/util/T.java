package kr.nomad.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ibm.icu.util.ChineseCalendar;

public class T {

	/**
	 * SimpleDateFormat
	 * 		yyyy : �⵵
	 * 		w    : �ϳ�ȿ��� ���° ������		W   : �Ѵ޾ȿ��� ���° ������
	 * 		MM   : ��
	 * 		dd   : ��							D   : �ϳ⿡�� ���° ������
	 * 		E    : ����(Tuesday,Tue)			F   : ������ ���ڷ�(2)
	 * 		hh   : �ð�(1~12)					HH  : �ð�(1~24)
	 * 		kk   : �ð�(0~11)					KK  : �ð�(0~23)
	 * 		a	 : AM/PM
	 * 		mm	 : ��
	 * 		ss	 : ��							SSS : Millisecond
	 */
	
	private static String[] solarArr = new String[]{"0101", "0301", "0505", "0606", "0815", "1003", "1009", "1225"};
	private static String[] solarValueArr = new String[]{"����", "������", "��̳�", "������", "������", "��õ��", "�ѱ۳�", "ũ��������"};

	private static String[] lunarArr = new String[]{"1231", "0101", "0102", "0408", "0814", "0815", "0816"};
	private static String[] lunarValueArr = new String[]{"��������", "����", "��������", "����ź����", "�߼�����", "�߼�", "�߼�����"};



	/**
	 * �ش����ڰ� ���� ���������Ͽ� �ش��ϴ� �� Ȯ��
	 * @param date
	 * @return
	 */
	public static String isHolidayLunar (String date) {

		boolean result = false;	

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
		calendar.set(Calendar.MONTH, Integer.parseInt(date.substring(4, 6)) - 1);
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date.substring(6)));
		return isHolidayLunar(Integer.parseInt(date.substring(0, 4)), Integer.parseInt(date.substring(4, 6)) - 1, Integer.parseInt(date.substring(6)) );
	}

	public static String isHolidayLunar (int year, int month, int day) {

		boolean result = false;	
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.DAY_OF_MONTH, day);

		ChineseCalendar chinaCal = new ChineseCalendar();
		chinaCal.setTimeInMillis(calendar.getTimeInMillis());

		/** �������� ��ȯ�� ���� ���� **/
		int mm = chinaCal.get(ChineseCalendar.MONTH) + 1;
		int dd = chinaCal.get(ChineseCalendar.DAY_OF_MONTH);
		
		StringBuilder sb = new StringBuilder();
		if (mm < 10)    sb.append("0");
		sb.append(mm);

		if (dd < 10)    sb.append("0");
		sb.append(dd);

		/** ���� 12���� �������� (���� ù��° ����)���� Ȯ�� **/
		if (mm == 12) {
			int lastDate = chinaCal.getActualMaximum(ChineseCalendar.DAY_OF_MONTH);
			if (dd == lastDate) {
				return "��������";
			}
		}

		for (int i=0;i<lunarArr.length; i++) {
			if (sb.toString().equals(lunarArr[i])) {
				return lunarValueArr[i];
			}
		}

		return "";
	}

	/**
	 * �ش����ڰ� ��� ���������Ͽ� �ش��ϴ� �� Ȯ��
	 * @param date
	 * @return
	 */
	public static String isHolidaySolar (String date) {

		boolean result = false;
		Calendar calendar = Calendar.getInstance();

		if (date.equals("") || date.length() > 8) {
			date = new java.text.SimpleDateFormat("MMdd").format(calendar.getTime());
		} else {
			date = date.substring(4);
		}
		int month = Integer.parseInt(date.substring(0,2));
		int day = Integer.parseInt(date.substring(2,4));
		return isHolidaySolar(month, day);
	}
	public static String isHolidaySolar (int month, int day) {

		boolean result = false;

		String date = F.addZero(month, 2) + F.addZero(day, 2);

		for (int i=0; i<solarArr.length; i++) {
			if (date.equals(solarArr[i])) {
				return solarValueArr[i];
			}
		}

		return "";
	}

	/**
	 * �ش����ڰ� �Ͽ������� Ȯ��
	 * @param date
	 * @return
	 */

	public static boolean isSunday (String date) {

		boolean result = false;

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(date.substring(0, 4)));
		calendar.set(Calendar.MONTH, Integer.parseInt(date.substring(4, 6)) - 1);
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(date.substring(6)));
		if (calendar.get ( Calendar.DAY_OF_WEEK ) == 1) {
			result = true;
		}

		return result;
	}

	
	/**
	 * ���� ��¥�� ���Ѵ�.
	 * @return "yyyyMMDD"
	 */
	public static String getToday() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(System.currentTimeMillis()));
		String today = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());	//"yyyyMMDD"
		return today;
	}
	

	/**
	 * ���� ������ ���Ѵ�.
	 * @return "yyyy"
	 */
	public static String getTodayYear() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(System.currentTimeMillis()));
		String today = new SimpleDateFormat("yyyy").format(calendar.getTime());	//"yyyy"
		return today;
	}

	/**
	 * ���� ���� ���Ѵ�.
	 * @return "MM"
	 */
	public static String getTodayMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(System.currentTimeMillis()));
		String today = new SimpleDateFormat("MM").format(calendar.getTime());	//"MM"
		return today;
	}
	/**
	 * ���� ��¥�� ���Ѵ�.
	 * @return "dd"
	 */
	public static String getTodayDay() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(System.currentTimeMillis()));
		String today = new SimpleDateFormat("dd").format(calendar.getTime());	//"dd"
		return today;
	}

	/**
	 * ���� �ð��� ���Ѵ�. �ð�(0~23)
	 * @return "yyyyMMDDHHmmss"
	 */
	public static String getHour() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(System.currentTimeMillis()));
		String now = new SimpleDateFormat("kk").format(calendar.getTime());	//"hh" : �ð�(0~23)
		return now;
	}

	/**
	 * ���� ������ ���Ѵ�.
	 * @return "yyyyMMDDHHmmss"
	 */
	public static String getWeekday() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(System.currentTimeMillis()));
		String now = new SimpleDateFormat("E").format(calendar.getTime());	//"E" : ������ ���ڷ�
		return now;
	}

	/**
	 * ������ �̹����� ���° ������ ���Ѵ�.
	 * @return "W"
	 */
	public static String getTodayWeekInMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(System.currentTimeMillis()));
		String today = new SimpleDateFormat("W").format(calendar.getTime());	//"W"
		return today;
	}

	/**
	 * ���� ��¥�� �ð�, ��, �ʸ� ���Ѵ�.
	 * @return "yyyyMMDDHHmmss"
	 */
	public static String getNow() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(System.currentTimeMillis()));
		String now = new SimpleDateFormat("yyyyMMddHHmmss").format(calendar.getTime());	//"yyyyMMDDHHmmss"
		return now;
	}
	public static String getNowFomat() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(System.currentTimeMillis()));
		String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());	//"yyyyMMDDHHmmss"
		return now;
	}
	
	/**
	 * �̴��� ������ ���� ���Ѵ�.
	 * @return int ��¥
	 */
	public static int getLastMonthday() {
		Calendar calendar = Calendar.getInstance();
		int lastday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return lastday;
	}

	/**
	 * �־��� ���� ������ ���� ���Ѵ�.
	 * @param year
	 * @param month
	 * @return int ��¥
	 */
	public static int getLastMonthday(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, 1);
		int lastday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return lastday;
	}

	/**
	 * �־��� ���� ������ ���� ���Ѵ�.
	 * @param yyyy_MM
	 * @return int ��¥
	 */
	public static int getLastMonthday(String yyyy_MM) {
		Calendar calendar = Calendar.getInstance();
		int year = Integer.parseInt(yyyy_MM.split("-")[0]);
		int month = Integer.parseInt(yyyy_MM.split("-")[1])-1;
		calendar.set(year, month, 1);
		int lastday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		return lastday;
	}
	
	/**
	 * �̴��� ������ ���� ���Ѵ�.
	 * @return String yyyyMMdd
	 */
	public static String getLastMonthFullday() {
		String today = T.getToday().substring(0, 7);
		Calendar calendar = Calendar.getInstance();
		String lastday = today +"-"+ F.addZero(calendar.getActualMaximum(Calendar.DAY_OF_MONTH),2);
		return lastday;
	}

	/**
	 * �־��� ���� ������ ���� ���Ѵ�.
	 * @param year
	 * @param month
	 * @return String yyyyMMdd
	 */
	public static String getLastMonthFullday(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, 1);
		String lastday = year +"-"+ F.addZero(month, 2) +"-"+ F.addZero(calendar.getActualMaximum(Calendar.DAY_OF_MONTH),2);
		return lastday;
	}

	/**
	 * �־��� ���� ������ ���� ���Ѵ�.
	 * @param yyyy_MM
	 * @return String yyyyMMdd
	 */
	public static String getLastMonthFullday(String yyyy_MM) {
		String today = T.getToday().substring(0, 6);
		Calendar calendar = Calendar.getInstance();
		int year = Integer.parseInt(yyyy_MM.split("-")[0]);
		int month = Integer.parseInt(yyyy_MM.split("-")[1])-1;
		calendar.set(year, month, 1);
		String lastday = yyyy_MM.substring(0, 7) +"-"+ F.addZero(calendar.getActualMaximum(Calendar.DAY_OF_MONTH),2);
		return lastday;
	}
	
	/**
	 * �־��� ���� ù ���� ���Ѵ�.
	 * @param year
	 * @param month
	 * @return int ��¥
	 */
	public static String getFirstMonthday(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month-1, 1);
		String firstDay = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());
		return firstDay;
	}
	/**
	 * �־��� ��¥�� ������ ���Ѵ�.
	 * 1:�Ͽ���, 2:������, 3:ȭ����, 4:������, 5:�����, 6:�ݿ���, 7:�����
	 * @param yyyyMMdd
	 * @return
	 */
	public static int getWeekDay(String yyyyMMdd) {
		String[] date = yyyyMMdd.split("-");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(date[0]));
		calendar.set(Calendar.MONTH, Integer.parseInt(date[1])-1);
		calendar.set(Calendar.DATE, Integer.parseInt(date[2]));
		return calendar.get(Calendar.DAY_OF_WEEK);
	}
	

	/**
	 * �־��� ��¥�� �־��� ��¥�� ���ϰų� ����.
	 * @param yyyyMMdd
	 * @param add
	 * @return
	 */
	public static String getDateAdd(String yyyyMMdd, int add) {
		String[] date = yyyyMMdd.split("-");
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(date[0]));
		calendar.set(Calendar.MONTH, Integer.parseInt(date[1])-1);
		calendar.set(Calendar.DATE, Integer.parseInt(date[2]));
		calendar.add(Calendar.DATE, add);
		String addedDay = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());	//"yyyyMMDD"
		return addedDay;
	}
	
	/**
	 * �־��� �ð��� �־��� ���� ���ϰų� ����.
	 * @param HHmm
	 * @param add
	 * @return
	 */
	public static String getMinuteAdd(String HHmm, int add) {
		int hour = Integer.parseInt(HHmm.substring(0, 2));
		int min = Integer.parseInt(HHmm.substring(2, 4));

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(System.currentTimeMillis()));
		calendar.add(Calendar.MINUTE, add);
		String addedMinute = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());	//"HHmm"
		return addedMinute;
	}

	/**
	 * �� ��¥�� ���Ѵ�.
	 * �� ��¥�� �� ��¥�� ������ : 0
	 * �� ��¥�� �� ��¥���� �����̸� : -1
	 * �� ��¥�� �� ��¥���� �����̸� : 1
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static int getDateDiff(String date1, String date2) {
		String[] d1 = date1.split("-");
		String[] d2 = date2.split("-");

		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(Calendar.YEAR, Integer.parseInt(d1[0]));
		calendar1.set(Calendar.MONTH, Integer.parseInt(d1[1])-1);
		calendar1.set(Calendar.DATE, Integer.parseInt(d1[2]));
		
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(Calendar.YEAR, Integer.parseInt(d2[0]));
		calendar2.set(Calendar.MONTH, Integer.parseInt(d2[1])-1);
		calendar2.set(Calendar.DATE, Integer.parseInt(d2[2]));
		
		return calendar2.compareTo(calendar1);
	}
	
	/**
	 * ��û�� ������ �Ͽ��Ϻ��� ����ϱ����� ��¥�� �迭�� ����
	 * @param year
	 * @param month
	 * @param week
	 */
	public static String[] getFirstAndFinishDayOfWeek(int year, int month, int week) {
		Calendar calendar = Calendar.getInstance();
		//calendar.setFirstDayOfWeek(2);	//�� ������ �����Ϸ� ����(��~��)
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.WEEK_OF_MONTH, week);
		
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		String weekStartDay = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());	//"yyyyMMDD"
		calendar.set(Calendar.DAY_OF_WEEK, 7);
		String weekEndDay = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());	//"yyyyMMDD"
		
		String[] thisWeekDay = { weekStartDay, weekEndDay}; 
		return thisWeekDay;
	}

	/**
	 * ��û�� ������ �����Ϻ��� ������ �Ͽ��ϱ����� ��¥�� �迭�� ����
	 * @param year
	 * @param month
	 * @param week
	 */
	public static String[] getFirstAndFinishDayOfWeekFromMonday(int year, int month, int week) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(2);	//�� ������ �����Ϸ� ����(��~��)
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		calendar.set(Calendar.WEEK_OF_MONTH, week);
		
		calendar.set(Calendar.DAY_OF_WEEK, 2);
		String weekStartDay = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());	//"yyyyMMDD"
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		String weekEndDay = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());	//"yyyyMMDD"
		
		String[] thisWeekDay = { weekStartDay, weekEndDay}; 
		return thisWeekDay;
	}

	/**
	 * ��û�� ������ �����Ϻ��� ������ �Ͽ��ϱ����� ��¥�� �迭�� ����
	 * �� 1���� ��,��,�� �� ��� ������ �������ַ� ó���Ѵ�.
	 * @param year
	 * @param month
	 * @param week
	 */
	public static String[] getFirstAndFinishDayOfWeekFromMondayExceptFriday(int year, int month, int week) {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(2);	//�� ������ �����Ϸ� ����(��~��)
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month-1);
		
		String firstMonthDay = getFirstMonthday(year, month); 				// ���� ù°���� ���Ѵ�.
		int firstWeekDay = getWeekDay(firstMonthDay);						// ���� ù°���� ������ ���Ѵ�.
		if (firstWeekDay == 6 || firstWeekDay == 7 || firstWeekDay == 1) {	// ���� ù°���� ��,��,���̸� ���� �ָ� ù°�ַ� �Ѵ�.
			calendar.set(Calendar.WEEK_OF_MONTH, week+1);
		} else {
			calendar.set(Calendar.WEEK_OF_MONTH, week);
		}
		
		calendar.set(Calendar.DAY_OF_WEEK, 2);
		String weekStartDay = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());	//"yyyyMMDD"
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		String weekEndDay = new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime());	//"yyyyMMDD"
		
		String[] thisWeekDay = { weekStartDay, weekEndDay}; 
		return thisWeekDay;
	}
		
	/**
	 * �־��� ����� ���ֱ������� ����
	 * @return "W"
	 */
	public static int getWeekInMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();

		String[] firstAndFinish = getFirstAndFinishDayOfMonth(year, month);
		String firstMonthDay = firstAndFinish[0]; 				// ���� ù°���� ���Ѵ�.
		int finishMonthDay = getLastMonthday(year, month);				// ���� ���������� ���Ѵ�.
		
		int startDate = 1;
		int endDate = finishMonthDay;

		int firstWeekDay = getWeekDay(firstMonthDay);						// ���� ù°���� ������ ���Ѵ�.
		if (firstWeekDay == 6) {
			startDate = 4;
		} else if (firstWeekDay == 7) {
			startDate = 3;
		} else if (firstWeekDay == 1) {
			startDate = 2;
		}

		int weekCount = (int)Math.ceil((float)(endDate - startDate) / 7);
		return weekCount;
	}

	/**
	 * ��û�� ����� ó�� ��¥�� ������ ��¥�� �迭�� ����
	 * @param year
	 * @param month
	 * @param week
	 */
	public static String[] getFirstAndFinishDayOfMonth(int year, int month) {
		String monthStartDay = T.getFirstMonthday(year, month);	//"yyyyMMDD"
		String monthEndDay = T.getLastMonthFullday(year, month);	//"yyyyMMDD"
		
		String[] thisMonthDay = { monthStartDay, monthEndDay}; 
		return thisMonthDay;
	}
	
	/**
	 * �־��� �Ⱓ������ ���� �����Ѵ�.
	 * @param startDateTime : yyyyMMddHHmm. ex:201303202400
	 * @param endDateTime : yyyyMMddHHmm. ex:201303202400
	 * @return
	 * @throws ParseException
	 */
	public static int getDurationByMinute(String startDateTime, String endDateTime) {
		int duration = 0;
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
		
		Date start = new Date(System.currentTimeMillis());
		Date end = new Date(System.currentTimeMillis());
		try {
			start = formatter.parse(startDateTime);
			end = formatter.parse(endDateTime);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		long diff = end.getTime() - start.getTime();
		long d = diff / (60 * 1000);

		duration = Math.round(d);
		
		return duration;
	}
	
	/**
	 * ������� �־��� �����Ͻ� ������ ���� �����Ѵ�.
	 * @param endDateTime : yyyyMMddHHmm. ex:201303202400
	 * @return
	 * @throws ParseException
	 */
	public static int getDurationByMinute(String endDateTime) {
		int duration = 0;
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
		
		Date start = new Date(System.currentTimeMillis());
		Date end = new Date(System.currentTimeMillis());
		try {
			end = formatter.parse(endDateTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		long diff = end.getTime() - start.getTime();
		duration = (int)diff / (60 * 1000);
		
		return duration;
	}
	
	/**
	 * ���̸� �����Ѵ� : ���� �������� ��������� ������ ����. 
	 * @param birth
	 * @return
	 */
	public static int getAge(String birth) {
		int age = 0;
		if(birth.trim().length()==10) {
			int birthYear = Integer.parseInt(birth.substring(0, 4));
			Calendar calendar = Calendar.getInstance();
			int todayYear = calendar.get(Calendar.YEAR);
			age = todayYear - birthYear;
		}
		return age;
	}
	


}
