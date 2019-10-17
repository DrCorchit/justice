package justice.lang.code.types.evaluators;

import com.google.common.collect.ImmutableMap;
import justice.lang.code.Code;
import justice.lang.code.routines.Fingerprint;
import justice.lang.namespaces.Namespace;

public interface Interface extends Code<Interface>, Namespace<Fingerprint> {

	ImmutableMap<String, Fingerprint> getFingerprints();

}
