package justice.lang.code;

import justice.lang.logging.HasLogger;
import justice.lang.util.Keyable;

public interface NamespaceObject<P extends Namespace> extends HasLogger, Keyable {

		P parent();

		String name();

		default String getFullname() {
			if (parent() == null) return name();
			else return parent().getFullname() + "." + name();
		}

		@Override
		default String loggerName() {
			return name();
		}

		@Override
		default String parentLoggerName() {
			return parent().getFullname();
		}

		@Override
		default String loggerFullname() {
			return getFullname();
		}
}
