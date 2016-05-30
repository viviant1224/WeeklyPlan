package viviant.cn.weeklyplan.constant;

import java.io.File;

import viviant.cn.weeklyplan.util.ExternalStorageUtil;


/**
 * 项目名称：Mts_android
 * 类描述：
 * 创建人：hww
 * 创建时间：2016/5/03 10:13
 * 修改人：Administrator
 * 修改时间：2016/5/03 10:13
 * 修改备注：
 */

public class Constants {

    public static final String INTENT_PLAN_THING = "mPlanthing";
    public static final String INTENT_PLAN_THING_UPDATE = "mPlanthingUpdate";
    public static final String NOTIFICATION_PLAN_THING = "mPlanthingNotification";

    /**
     * 程序隐藏根目录
     */
    public static final StringBuilder APP_BASE_PATH = ExternalStorageUtil.getExternalStoragePath();
    /**
     * 程序异常崩溃日志
     */
    public static final StringBuilder APP_CRASH_BASE_PATH = new StringBuilder().append(APP_BASE_PATH).append("crash").append(File.separator);
    /**
     * 照片存储路径
     */
    public static final StringBuilder APP_IMG_FOLDER_PATH = new StringBuilder().append(APP_BASE_PATH).append("photos").append(File.separator);
    /**
     * 服务地址配置路径
     */
    public static final StringBuilder APP_URL_FOLDER_PATH = new StringBuilder().append(APP_BASE_PATH).append("URL/URL.txt");
    /**
     * 照片存储路径文件夹名
     */
    public static final StringBuilder APP_IMG_FOLDER_NAME = new StringBuilder().append("photos").append(File.separator);
    /**
     * 打开系统相机返回resultCode
     */
    public static final int CAMERA_TAKE = 11;

}
