package cn.brotherChun.erp.util.format;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatUtil {
	public static final String formatDate(Long time){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(new Date(time));
	}
	public static final String formatTime(Long time){
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
		return df.format(new Date(time));
	}
	public static final String formatDateTime(Long time){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(new Date(time));
	}
}
