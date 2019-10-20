package justice.lang.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Utils {


	public static String loadFile(String path) throws IOException {
		return loadFile(new File(path));
	}

	public static String loadFile(File f) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(f));
		StringBuilder output = new StringBuilder();
		String s;
		while ((s = br.readLine()) != null) output.append(s);
		br.close();
		return output.toString();
	}
}
