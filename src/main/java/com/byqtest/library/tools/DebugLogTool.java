package com.byqtest.library.tools;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class DebugLogTool {
    private ByteArrayOutputStream systemOut;
	private ByteArrayOutputStream systemError;

	public DebugLogTool() {
		systemOut = new ByteArrayOutputStream();
		systemError = new ByteArrayOutputStream();
		PrintStream out = new PrintStream(systemOut);
		PrintStream error = new PrintStream(systemError);
		System.setOut(out);
		System.setErr(error);
	}
    
    public void destoty() {
		try {
			systemOut.close();
			systemError.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
}
