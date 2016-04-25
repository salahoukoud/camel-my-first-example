package saoah.tutorial.camel.my_first_example;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class FileToFileExampleTest extends TestCase {
	/**
	 * Create the test case
	 * 
	 * @param testName
	 *            name of the test case
	 */
	public FileToFileExampleTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(FileToFileExampleTest.class);
	}

	/**
	 * Rigourous Test :-)
	 */
	public void testApp() {
		String text = "TOTO";
		try {
			FileToFileExample example = new FileToFileExample();

			example.sendToCamelLog(text);
			example.sendToCamelFile(text);
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
