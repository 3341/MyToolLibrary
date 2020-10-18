package com.byqtest.library.tools;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.IOException;

public class StringTools {
    
    public static String getThrowableStringReport(Throwable th) {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		PrintStream print = new PrintStream(bo);
		th.printStackTrace(print);
		print.flush();
		String result = bo.toString();
		try {
			print.close();
			bo.close();
			return result;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
