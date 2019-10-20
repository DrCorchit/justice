package justice.lang.namespaces;

import justice.lang.logging.HasLogger;
import justice.lang.util.Keyable;

public interface NamespaceObject extends HasLogger, Keyable {

		Namespace parent();

		String name();

		default String getFullname() {
			if (parent() == null) return name();
			else return parent().getFullname() + "." + name();
		}

		default String key() {
			return Keyable.normalize(name());
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
