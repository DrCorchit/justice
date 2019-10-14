package justice.lang.logging;

import org.apache.logging.log4j.Level;

/**
 * The outer class hides some implementation details of the inner interface default methods.
 * This is done to encourage classes to implement the interface, HasLogger
 */
public interface HasLogger {

	default Loggable getLogger() {
		return Loggable.getLogger(loggerFullname());
	}

	//return the name of the logger used by this instance
	default String loggerName() {
		return getClass().getSimpleName();
	}

	default String parentLoggerName() {
		return getClass().getPackage().getName();
	}

	default String loggerFullname() {
		return String.format("%s.%s", parentLoggerName(), loggerName());
	}

	default void fatal(String method, String format, Object... args) {
		getLogger().log(Level.FATAL, method, format, args);
	}

	default <T extends Throwable> T error(String method, T e) {
		return getLogger().error(method, e.getMessage(), e);
	}

	default void error(String method, String format, Object... args) {
		getLogger().log(Level.ERROR, method, format, args);
	}

	default void warn(String method, String format, Object... args) {
		getLogger().log(Level.WARN, method, format, args);
	}

	default void info(String method, String format, Object... args) {
		getLogger().log(Level.INFO, method, format, args);
	}

	default void debug(String method, String format, Object... args) {
		getLogger().log(Level.DEBUG, method, format, args);
	}

	default void trace(String method, String format, Object... args) {
		getLogger().log(Level.TRACE, method, format, args);
	}

	default void log(Level level, String method, String format, Object... args) {
		getLogger().log(level, method, format, args);
	}
}
