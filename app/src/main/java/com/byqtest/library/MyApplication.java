package com.byqtest.library;
import android.app.Application;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import android.content.Context;
import java.io.IOException;

public class MyApplication extends Application {
    private ByteArrayOutputStream systemOut;
	private ByteArrayOutputStream systemError;

	@Override
	public void onCreate() {
		super.onCreate();
		systemOut = new ByteArrayOutputStream();
		systemError = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(systemOut);
		PrintStream error = new PrintStream(systemError);
		System.setOut(out);
		System.setErr(error);
	}
	
	public static MyApplication getApplication(Context context) {
		return (MyApplication)context.getApplicationContext();
	}
	
	public String getSystemOutContent() {
		return systemOut.toString();
	}
	
	public String getSystemErrorContent() {
		return systemError.toString();
	}

	@Override
	public void onTerminate() {
		try {
			systemOut.close();
			systemError.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		super.onTerminate();
	}
}
