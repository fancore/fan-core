package fan.core.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
/**
 * <p> <b> @描述：</b> 执行时异常
 * <p> <b> @作者：</b> fancore
 * <p> <b> @邮箱：</b> fancore@126.com
 * <p> <b> @日期：</b> 2014-06-13
 * <p> <b> @since 0.1.0 </b>
 */
public class ExecutetimeException extends RuntimeException {

	// the real exception
	private Throwable throwable;
	private static final long serialVersionUID = 6635246863882893402L;
	
	public ExecutetimeException(String message){
		// text as a runtime exception
		throwable = new RuntimeException(message);
	}

	public ExecutetimeException(Throwable e){
		this.throwable = e;
	}
	
	public ExecutetimeException(Throwable e, String message){
		this.throwable = e;
		System.err.println("info : " + message);
		try {
			Thread.sleep(10); // hold on
		} catch (InterruptedException ie) { /* ignore exception */ }
	}

	// override >>> try to hide its <<< //
	
	@Override
	public String getMessage() {
		return throwable.getMessage();
	}

	@Override
	public String getLocalizedMessage() {
		return throwable.getLocalizedMessage();
	}

	@Override
	public Throwable getCause() {
		return throwable.getCause();
	}

	@Override
	public synchronized Throwable initCause(Throwable cause) {
		return throwable.initCause(cause);
	}

	@Override
	public String toString() {
		return throwable.toString();
	}

	@Override
	public void printStackTrace() {
		throwable.printStackTrace();
	}

	@Override
	public void printStackTrace(PrintStream s) {
		throwable.printStackTrace(s);
	}

	@Override
	public void printStackTrace(PrintWriter s) {
		throwable.printStackTrace(s);
	}

	@Override
	public synchronized Throwable fillInStackTrace() {
		return super.fillInStackTrace();
	}

	@Override
	public StackTraceElement[] getStackTrace() {
		return throwable.getStackTrace();
	}

	@Override
	public void setStackTrace(StackTraceElement[] stackTrace) {
		throwable.setStackTrace(stackTrace);
	}
}