package ti.ikruglik.appshortcut;

import org.appcelerator.kroll.KrollModule;

import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.kroll.common.Log;

import android.content.Intent;
import android.app.Activity;
import android.content.ComponentName;
import android.content.pm.*;
import android.content.pm.PackageManager.NameNotFoundException;

import android.graphics.drawable.*;
import android.widget.Toast;

@Kroll.module(name="Appshortcut", id="ti.ikruglik.appshortcut")
public class AppshortcutModule extends KrollModule
{
	public AppshortcutModule()
	{
		super();
	}

	@Kroll.onAppCreate
	public static void onAppCreate(TiApplication app)
	{
		// onAppCreate()
	}

	@Kroll.method
	public boolean createShortcut(){
		TiApplication appContext = TiApplication.getInstance();
		Activity appActivity = TiApplication.getAppRootOrCurrentActivity();
				
		if (appActivity == null) return false;
		
		Intent shortcutIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
	    
	    try {
			ApplicationInfo appInfo = appContext.getPackageManager().getApplicationInfo(appContext.getPackageName(), 0);  
			PackageManager appPM = appContext.getPackageManager();
			
			String appName = appPM.getApplicationLabel(appInfo).toString();
		    shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, appName);
		    
		    Drawable appIcon = appPM.getApplicationIcon(appInfo);	    
		    BitmapDrawable appIconBitmap = (BitmapDrawable) appIcon;
		    shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON, appIconBitmap.getBitmap());
		}
		catch (NameNotFoundException e)
		{
			Log.e("[EXCEPTION]", "NameNotFoundException");
			return false;
		}	    

		ComponentName appComp = new ComponentName(appContext.getPackageName(), "."+appActivity.getLocalClassName());  
	    shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(Intent.ACTION_MAIN).setComponent(appComp));  
	    
	    shortcutIntent.putExtra("duplicate", false);
	    
	    //String shortcutUri = shortcutIntent.toUri(Context.MODE_WORLD_WRITEABLE);
	    //Toast.makeText(appContext, shortcutUri, Toast.LENGTH_LONG).show();
	    
	    appContext.sendBroadcast(shortcutIntent);
	    
	    //setResult(RESULT_OK, shortcutIntent);
		//finish();	        
	    return true;
	}
	
	@Kroll.method
	public boolean deleteShortcut(){		
		TiApplication appContext = TiApplication.getInstance();
		Activity appActivity = TiApplication.getAppRootOrCurrentActivity();
				
		if (appActivity == null) return false;
		
		Intent shortcutIntent = new Intent("com.android.launcher.action.UNINSTALL_SHORTCUT");
	    
	    try {
			ApplicationInfo appInfo = appContext.getPackageManager().getApplicationInfo(appContext.getPackageName(), 0);  
			PackageManager appPM = appContext.getPackageManager();
			
			String appName = appPM.getApplicationLabel(appInfo).toString();
		    shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, appName);
		}
		catch (NameNotFoundException e)
		{
			Log.e("[EXCEPTION]", "NameNotFoundException");
			return false;
		}	    

		ComponentName appComp = new ComponentName(appContext.getPackageName(), "."+appActivity.getLocalClassName());  
	    shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(Intent.ACTION_MAIN).setComponent(appComp));  
	    
	    //String shortcutUri = shortcutIntent.toUri(Context.MODE_WORLD_WRITEABLE);
	    //Toast.makeText(appContext, shortcutUri, Toast.LENGTH_LONG).show();
	    
	    appContext.sendBroadcast(shortcutIntent);
	    return true;
	}

}

