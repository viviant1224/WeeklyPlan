package viviant.cn.weeklyplan.manager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import viviant.cn.weeklyplan.ApiService;
import viviant.cn.weeklyplan.okhttp.TestOkHttp;

/**
 * Created by weiwei.huang on 16-5-4.
 */
public class StartServiceManager {
    public static void startBaiduOkHttp(Context context) {
        startIntent(context, TestOkHttp.class.getName(), null);
    }

    private static void startIntent(@NonNull Context context, @NonNull String intentType, @Nullable Bundle bundle) {
        Intent intent = new Intent(context, ApiService.class);
        intent.setType(intentType);
        if (bundle != null)
            intent.putExtras(bundle);
        context.startService(intent);
    }

}
