package justice.lang.parsing;

import justice.lang.code.types.DataType;
import justice.lang.code.types.SimpleDataType;
import justice.lang.data.Data;

public class AbstractSyntaxTree {






	protected static abstract class AstNode {
		//if the input data types are not valid, return null
		protected abstract DataType getOutputType(DataType...input);
		protected abstract Data getOutput(Data...input);
	}

	private class BoolNode extends AstNode {
		private final AstNode left, right;
		private final Operator op;

		private BoolNode(AstNode left, AstNode right, Operator op) {
			this.left = left;
			this.right = right;
			this.op = op;
			assert op
		}

		@Override
		protected DataType getOutputType(DataType... input) {
			for (DataType type : input) if (type != SimpleDataType.BOOL) return null;
			return SimpleDataType.BOOL;
		}

		@Override
		protected Data getOutput(Data... input) {
			return null;
		}
	}

}
