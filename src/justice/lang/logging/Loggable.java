package justice.lang.logging;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface Loggable extends HasLogger {

	default Loggable getLogger() {
		return this;
	}

	//Base case logger
	void log(Level level, String method, String format, Object... args);

	//Error with explanation
	<T extends Throwable> T error(String method, String message, T error);

	static Loggable getLogger(String name) {
		return getLogger(LogManager.getLogger(name));
	}

	//creates a loggable from an apache 4j logger
	static Loggable getLogger(Logger logger) {
		return new Loggable() {
			@Override
			public void log(Level level, String method, String format, Object... args) {
				String message = String.format(format, args);
				String output = method + ": " + message;
				logger.log(level, output);
			}

			//Error with explanation
			@Override
			public <T extends Throwable> T error(String method, String message, T error) {
				logger.error(String.format("Error in method %s (%s)", method, message), error);
				return error;
			}

		};
	}
}
