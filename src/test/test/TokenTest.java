package test;

import com.google.common.collect.ImmutableList;
import justice.lang.parsing.Tokens;
import justice.lang.parsing.Tokens.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TokenTest {

	@Test
	public void simpleTest() {
		ArrayList<Tokens.Token> expected = new ArrayList<>();
		expected.add(Tokens.CONSTANT.create("1+2"));
		ImmutableList<Token> actual = Tokens.tokenize("1+3");
		Assertions.assertEquals(expected, actual);
	}



}
