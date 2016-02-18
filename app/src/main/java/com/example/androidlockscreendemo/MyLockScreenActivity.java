package com.example.androidlockscreendemo;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.Locale;

public class   MyLockScreenActivity extends Activity implements OnClickListener {

    private static final int ADMIN_INTENT = 15;
    private static final String description = "Sample Administrator description";
    private DevicePolicyManager mDevicePolicyManager;
    private ComponentName mComponentName;
    private static String TAG = "MyLockScreenActivity";

    static final String baseUrl = "https://play.google.com/store/apps/details?id=";

    public static String startString = "<a class=\"document-subtitle category\" href=\"/store/apps/category/";
    public static String endString = "\">";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_lock_screen);
        mDevicePolicyManager = (DevicePolicyManager)getSystemService(
                Context.DEVICE_POLICY_SERVICE);
        mComponentName = new ComponentName(this, MyAdminReceiver.class);
        Button btnEnableAdmin = (Button) findViewById(R.id.btnEnable);
        Button btnDisableAdmin = (Button) findViewById(R.id.btnDisable);
        Button btnLock = (Button) findViewById(R.id.btnLock);
        btnEnableAdmin.setOnClickListener(this);
        btnDisableAdmin.setOnClickListener(this);
        btnLock.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEnable:
                Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mComponentName);
                intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION,description);
                startActivityForResult(intent, ADMIN_INTENT);
                break;

            case R.id.btnDisable:
                mDevicePolicyManager.removeActiveAdmin(mComponentName);
                Toast.makeText(getApplicationContext(), "Admin registration removed", Toast.LENGTH_SHORT).show();
                break;

            case R.id.btnLock:
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        connect();
                    }
                };
                thread.start();
                break;
        }
    }

    private void connect() {
        JSONObject obj = new JSONObject(), apps = new JSONObject();
        JSONArray list = new JSONArray();
        boolean needToStore = false;

        List<ResolveInfo> ril = getAllAppsInDevice();
        for (ResolveInfo ri : ril) {
            try {
                getAppNameAndStoreIt(ri, list);
            } catch (Exception e) {
                Log.d(TAG, "getAppNameAndStoreIt: " + e.getMessage());
            }
        }

        try {
            for (int i = 0, length = list.length(); i < length; ++i) {
                String pName = list.getString(i);
                Log.d(TAG, "pName: " + pName + ", category: " + getCategory(pName));
            }
//            HttpClient httpClient = new DefaultHttpClient(); // Deprecated
//            HttpPost httpPost = new HttpPost("http://strong-chain-122309.appspot.com/");
//            httpPost.setHeader("Content-type", "application/json");
//            StringEntity params = new StringEntity(obj.toString());
//            httpPost.setEntity(params);
//            HttpResponse lResp = httpClient.execute(httpPost);
//
//            ByteArrayOutputStream lBOS = new ByteArrayOutputStream();
//            lResp.getEntity().writeTo(lBOS);
//            String lInfoStr = lBOS.toString("UTF-8");
//            Log.d(TAG, lInfoStr);
//            JSONObject categoryResponse = new JSONObject(lInfoStr);
//            JSONArray appArr = categoryResponse.getJSONArray("apps");
//
//            for (int i = 0; i < appArr.length(); i++) {
//                JSONObject appObj = appArr.getJSONObject(i);
//                String packageVal = appObj.optString("package", null);
//                String categoryVal = appObj.optString("category", null);
//
//                if (packageVal != null)
//                    Log.d(TAG, "packageVal: " + packageVal);
//                if (categoryVal != null)
//                    Log.d(TAG, "categoryVal: " + categoryVal);
//
//                if (packageVal == null || categoryVal == null)
//                    continue;
//                apps.put(packageVal, categoryVal);
//            }
//            Log.d(TAG, apps.toString());
//            JSONArray all = apps.names();
//            for (int i = 0; i < all.length(); i++) {
//                Log.d("GetAppsService", all.getString(i) + ": " + apps.getString(all.getString(i)));
//            }
        } catch (Exception e) { Log.d(TAG, e.getMessage()); }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADMIN_INTENT) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(getApplicationContext(), "Registered As Admin", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getApplicationContext(), "Failed to register as Admin", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public List<ResolveInfo> getAllAppsInDevice() {
        final Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        return getPackageManager().queryIntentActivities(mainIntent, 0);
    }

    public void getAppNameAndStoreIt(ResolveInfo ri, JSONArray list) throws Exception {
        String name;
        if (ri.activityInfo == null)    return;

        Resources res = getPackageManager().getResourcesForApplication(ri.activityInfo.applicationInfo);
        AssetManager assets = res.getAssets();
        DisplayMetrics metrics = res.getDisplayMetrics();
        Configuration config = new Configuration(res.getConfiguration());
        config.locale = Locale.US;

        Resources engRes = new Resources(assets, metrics, config);
        if (ri.activityInfo.labelRes != 0) {
            name = engRes.getString(ri.activityInfo.labelRes);
            if (name.equals("")) {
                name = res.getString(ri.activityInfo.labelRes);
            }
        } else {
            name = ri.activityInfo.applicationInfo.loadLabel(getPackageManager()).toString();
        }
        list.put(ri.activityInfo.packageName);

    }

    private static String getCategory(String packageName) {
        try {
            URL url = new URL(baseUrl + packageName);
            InputStream is = (InputStream) url.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            StringBuffer sb = new StringBuffer();
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            String htmlContent = sb.toString();
            int start = htmlContent.indexOf(startString);
            int end = htmlContent.indexOf(endString, start);
            String category_beta = htmlContent.substring(start + startString.length(), end);
            Log.d("getCategory", "category_beta: " + category_beta);
            String category = "";
            boolean lowerCase = false;
            for (int j = 0, length = category_beta.length(); j < length; ++j) {
                char c = category_beta.charAt(j);
                if (lowerCase) {
                    if (c >= 'A' && c <= 'Z')
                        category += (char) (c + 32);
                    else
                        lowerCase = false;
                } else {
                    category += c;
                    if (c >= 'A' && c <= 'Z')
                        lowerCase = true;
                }
            }
            Log.d("getCategory", "category: " + category);
            String converted = categoryConvert(category);
            Log.d("getCategory", "categoryConvert: " + converted);
            return converted;
        } catch (Exception e) { Log.d("getCategory", e.getMessage()); }
        return "";
    }


    private static String categoryConvert(String category) {
        if (category.substring(0, 4).equals("Game"))
            return "Games";
        if (category.substring(0, 6).equals("Family"))
            return "Family";
        return category;
    }
}