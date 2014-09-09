package com.drivemode.android.typeface.sample;

import android.app.Application;

import com.drivemode.android.typeface.TypefaceHelper;

/**
 * @author KeithYokoma
 */
public class SampleApp extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		TypefaceHelper.initialize(this);
	}

	@Override
	public void onTerminate() {
		TypefaceHelper.destroy();
		super.onTerminate();
	}
}
