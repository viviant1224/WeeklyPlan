package viviant.cn.weeklyplan.log;

import android.content.Context;
import android.util.Log;

import viviant.cn.weeklyplan.constant.Constants;
import viviant.cn.weeklyplan.okhttp.AbstractBaseOkHttp;
import viviant.cn.weeklyplan.util.FileUtils;


public class PrintLogUtil {
	/**
	 * 日志名称
	 */
	private static String mtsLog = "MTSLog";

	/**
	 * 打印日志
	 * 
	 * @param
	 */
	private static void printLogToSdCard(Context context, LogBean printLog) {
		String fileName = mtsLog + printLog.getRequestTime() + ".txt";
		String messageLog = printLog.toString();
		boolean b = FileUtils.writeFile(Constants.APP_BASE_PATH + fileName, messageLog);
		if (b) {
			Log.e("hww", "日志打印成功");
		} else {
			Log.e("hww", "日志打印失败");
		}
		// 清除日志对象
		fileName = null;
		printLog = null;
		messageLog = null;
		// StatService.reportError(context, messageLog);
		// Logger.e(messageLog.toString());
	}

	public static void createPrintLogToSdCard(Context context, AbstractBaseOkHttp abstractBaseOkHttp) {
		LogBean printLog = new LogBean();
		printLog.setHeaders(abstractBaseOkHttp.getRequest().toString());
		printLog.setResponseTime(abstractBaseOkHttp.getResponseTime());
		printLog.setRequestTime(abstractBaseOkHttp.getRequestTime());
		printLog.setRequestBody(abstractBaseOkHttp.getRequestJson() == null ? "" : abstractBaseOkHttp.getRequestJson());
		printLog.setResponseBody(abstractBaseOkHttp.getErrorMessage());
		printLogToSdCard(context, printLog);
	}
}
