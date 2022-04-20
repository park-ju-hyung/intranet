package initech.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class AppDateUtil {
	
	private static TimeZone mySTZ = (TimeZone) TimeZone.getTimeZone("JST");
    private static Locale lc = new Locale("Locale.KOREAN", "Locale.KOREA");
    
	/**
	 * 현재 timestamp 구하기
	 * @return
	 */
	public static String getCurrentTimestamp() {
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+'00:00'");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		return sdf.format(dt);
	}
	
	/**
	 * 현재시간을 yyyyMMddHHmmss 로 리턴
	 * @return
	 */
	public static String getYYYYMMDDHHMMSS() {
	    Calendar today = Calendar.getInstance(mySTZ, lc);
	    int year = today.get(Calendar.YEAR);
	    int mon = today.get(Calendar.MONTH) + 1;
	    int day = today.get(Calendar.DAY_OF_MONTH);
	    int hour = today.get(Calendar.HOUR_OF_DAY);
	    int min = today.get(Calendar.MINUTE);
	    int sec = today.get(Calendar.SECOND);

	    String str = "";

	    str += year;

	    if (mon < 10) {
	      str += "0";
	    }
	    str += mon;

	    if (day < 10) {
	      str += "0";
	    }
	    str += day;

	    if (hour < 10) {
	      str += "0";
	    }
	    str += hour;

	    if (min < 10) {
	      str += "0";
	    }
	    str += min;

	    if (sec < 10) {
	      str += "0";
	    }
	    str += sec;

	    return str;
	  }
}
