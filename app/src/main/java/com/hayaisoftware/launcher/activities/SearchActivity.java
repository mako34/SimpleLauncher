

package com.hayaisoftware.launcher.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View;


import com.hayaisoftware.launcher.R;


public class SearchActivity extends Activity
        implements SharedPreferences.OnSharedPreferenceChangeListener {


    private Context mContext;
    private PackageManager mPm;

     private boolean mIsCacheClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search);

        mPm = getPackageManager();

        mContext = getApplicationContext();



        final IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_PACKAGE_ADDED);
        filter.addAction(Intent.ACTION_PACKAGE_CHANGED);
        filter.addAction(Intent.ACTION_PACKAGE_REMOVED);
        filter.addAction(Intent.ACTION_PACKAGE_REPLACED);
        filter.addDataScheme("package");

    }



    @Override
    protected void onResume() {
        super.onResume();
        mIsCacheClear = false;

    }



    @Override
    protected void onPostResume() {
        super.onPostResume();


        Log.d("mk","mako escucha");



    }



    private boolean isCurrentLauncher() {
        final Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        final ResolveInfo resolveInfo =
                mPm.resolveActivity(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return resolveInfo != null &&
                mContext.getPackageName().equals(resolveInfo.activityInfo.packageName);

    }



    @Override
    public void onBackPressed() {
        if (!isCurrentLauncher())
            moveTaskToBack(false);
    }

    @Override
    protected void onDestroy() {

        Log.d("HayaiLauncher", "Hayai is ded");
        super.onDestroy();
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {


    }


    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (!mIsCacheClear && level == TRIM_MEMORY_COMPLETE)
            clearCaches();

    }

    private void clearCaches() {
        mIsCacheClear = true;

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);


    }







}
