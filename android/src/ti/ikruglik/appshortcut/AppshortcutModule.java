package ti.ikruglik.appshortcut;

import org.appcelerator.kroll.KrollModule;
import org.appcelerator.kroll.annotations.Kroll;
import org.appcelerator.titanium.TiApplication;
import org.appcelerator.kroll.common.Log;
import android.content.Intent;
import android.app.Activity;
import org.appcelerator.titanium.util.TiRHelper;
import android.content.ComponentName;

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
	public boolean createShortcut(String shortcutname){
		TiApplication appContext = TiApplication.getInstance();
		Activity appActivity = TiApplication.getAppRootOrCurrentActivity();
		
		if (appActivity == null) return false;
		
		Intent shortcutIntent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");

		ComponentName comp = new ComponentName(appContext.getPackageName(), "."+appActivity.getLocalClassName());  
	    shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(Intent.ACTION_MAIN).setComponent(comp));  
	    
	    shortcutIntent.putExtra("duplicate", false);
	    shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortcutname);
	    
	    try {
	    	shortcutIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, Intent.ShortcutIconResource.fromContext(appContext, TiRHelper.getApplicationResource("drawable.appicon")));
	    } catch (TiRHelper.ResourceNotFoundException e) {
	        Log.e("[ERROR] EXCEPTION", "EXTRA_SHORTCUT_ICON_RESOURCE -- RESOURCE NOT FOUND");
	        return false;
	    }	    
	    
	    appContext.sendBroadcast(shortcutIntent);
	    return true;
	}

}

