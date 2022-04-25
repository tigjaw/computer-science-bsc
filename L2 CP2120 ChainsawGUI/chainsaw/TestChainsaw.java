package chainsaw;

import java.beans.*;
import java.io.*;

/**
 * Class to test Chainsaw.java variables, constructors and other methods.
 * 
 * @author Joshua
 * @version April 2008
 */
public class TestChainsaw {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestChainsaw tester = new TestChainsaw();
		tester.test1();
		tester.test2();
		tester.test3();
		tester.test4();
	}

	/**
	 * Test the default constructor and query methods.
	 */
	public void test1() {
		System.out.println("==============================================");
		System.out.println(" Test 1 - Default Constructor & Query methods");
		System.out.println("==============================================");

		String expectedManufacturer = "Unknown";
		int expectedNumberOfTeeth = 0;
		double expectedWeight = 0;
		boolean expectedHasSafetyCutOut = false;
		boolean expectedIsUsed = false;
		String expectedColour = "unknown";
		double expectedCutLength = 0;
		boolean expectedIsExhibited = false;
		int expectedId = 0;

		Chainsaw chain = new Chainsaw();

		boolean passedOk = true;

		if (!chain.getManufacturer().equals(expectedManufacturer)) {
			passedOk = false;
			System.out.println("error in expectedManufacturer");
		}

		if (chain.getNumberOfTeeth() != expectedNumberOfTeeth) {
			passedOk = false;
			System.out.println("error in expectedNumberOfTeeth");
		}

		if (chain.getWeight() != expectedWeight) {
			passedOk = false;
			System.out.println("error in expectedWeight");
		}

		if (chain.getHasSafetyCutOut() != expectedHasSafetyCutOut) {
			passedOk = false;
			System.out.println("error in expectedHasSafetyCutOut");
		}

		if (chain.getIsUsed() != expectedIsUsed) {
			passedOk = false;
			System.out.println("error in expectedIsUsed");
		}

		if (!chain.getColour().equals(expectedColour)) {
			passedOk = false;
			System.out.println("error in expectedColour");
		}

		if (chain.getCutLength() != expectedCutLength) {
			passedOk = false;
			System.out.println("error in expectedCutLength");
		}

		if (chain.getIsExhibited() != expectedIsExhibited) {
			passedOk = false;
			System.out.println("error in expectedIsExhibited");
		}

		if (chain.getId() != expectedId) {
			passedOk = false;
			System.out.println("error in expectedId");
		}

		if (passedOk) {
			System.out.println("All parts of Test 1 passed OK.");
		}
	}

	/**
	 * Test the parameterised constructor and query methods.
	 */
	public void test2() {
		System.out
				.println("====================================================");
		System.out
				.println(" Test 2 - Parameterised Constructor & Query methods");
		System.out
				.println("====================================================");

		String expectedManufacturer = "Java";
		int expectedNumberOfTeeth = 55;
		double expectedWeight = 12.2;
		boolean expectedHasSafetyCutOut = true;
		boolean expectedIsUsed = false;
		String expectedColour = "Green";
		double expectedCutLength = 46.5;
		boolean expectedIsExhibited = true;

		Chainsaw chain = new Chainsaw(expectedManufacturer,
				expectedNumberOfTeeth, expectedWeight, expectedHasSafetyCutOut,
				expectedIsUsed, expectedColour, expectedCutLength,
				expectedIsExhibited);

		boolean passedOk = true;

		if (!chain.getManufacturer().equals(expectedManufacturer)) {
			passedOk = false;
			System.out.println("error in expectedManufacturer");
		}

		if (chain.getNumberOfTeeth() != expectedNumberOfTeeth) {
			passedOk = false;
			System.out.println("error in expectedNumberOfTeeth");
		}

		if (chain.getWeight() != expectedWeight) {
			passedOk = false;
			System.out.println("error in expectedWeight");
		}

		if (chain.getHasSafetyCutOut() != expectedHasSafetyCutOut) {
			passedOk = false;
			System.out.println("error in expectedHasSafetyCutOut");
		}

		if (chain.getIsUsed() != expectedIsUsed) {
			passedOk = false;
			System.out.println("error in expectedIsUsed");
		}

		if (!chain.getColour().equals(expectedColour)) {
			passedOk = false;
			System.out.println("error in expectedColour");
		}

		if (chain.getCutLength() != expectedCutLength) {
			passedOk = false;
			System.out.println("error in expectedCutLength");
		}

		if (chain.getIsExhibited() != expectedIsExhibited) {
			passedOk = false;
			System.out.println("error in expectedIsExhibited");
		}

		if (passedOk) {
			System.out.println("All parts of Test 2 passed OK.");
		}
	}

	/**
	 * Test the update and query methods.
	 */
	public void test3() {
		System.out.println("=================================");
		System.out.println(" Test 3 - Update & Query methods");
		System.out.println("=================================");

		String expectedManufacturer = "Java";
		int expectedNumberOfTeeth = 55;
		double expectedWeight = 12.2;
		boolean expectedHasSafetyCutOut = true;
		boolean expectedIsUsed = false;
		String expectedColour = "Green";
		double expectedCutLength = 46.5;
		boolean expectedIsExhibited = true;

		Chainsaw chain = new Chainsaw();
		boolean passedOk = true;

		chain.setManufacturer(expectedManufacturer);
		chain.setNumberOfTeeth(expectedNumberOfTeeth);
		chain.setWeight(expectedWeight);
		chain.setHasSafetyCutOut(expectedHasSafetyCutOut);
		chain.setIsUsed(expectedIsUsed);
		chain.setColour(expectedColour);
		chain.setCutLength(expectedCutLength);
		chain.setIsExhibited(expectedIsExhibited);

		if (!chain.getManufacturer().equals(expectedManufacturer)) {
			passedOk = false;
			System.out.println("error in expectedManufacturer");
		}

		if (chain.getNumberOfTeeth() != expectedNumberOfTeeth) {
			passedOk = false;
			System.out.println("error in expectedNumberOfTeeth");
		}

		if (chain.getWeight() != expectedWeight) {
			passedOk = false;
			System.out.println("error in expectedWeight");
		}

		if (chain.getHasSafetyCutOut() != expectedHasSafetyCutOut) {
			passedOk = false;
			System.out.println("error in expectedHasSafetyCutOut");
		}

		if (chain.getIsUsed() != expectedIsUsed) {
			passedOk = false;
			System.out.println("error in expectedIsUsed");
		}

		if (!chain.getColour().equals(expectedColour)) {
			passedOk = false;
			System.out.println("error in expectedColour");
		}

		if (chain.getCutLength() != expectedCutLength) {
			passedOk = false;
			System.out.println("error in expectedCutLength");
		}

		if (chain.getIsExhibited() != expectedIsExhibited) {
			passedOk = false;
			System.out.println("error in expectedIsExhibited");
		}

		if (passedOk) {
			System.out.println("All parts of Test 3 passed OK.");
		}
	}

	/**
	 * Test the object persistence.
	 */
	public void test4() {
		System.out.println("======================");
		System.out.println(" Test 4 - Persistence");
		System.out.println("======================");

		String expectedManufacturer = "Java";
		int expectedNumberOfTeeth = 55;
		double expectedWeight = 12.2;
		boolean expectedHasSafetyCutOut = true;
		boolean expectedIsUsed = false;
		String expectedColour = "Green";
		double expectedCutLength = 46.5;
		boolean expectedIsExhibited = true;

		Chainsaw chainOut = new Chainsaw(expectedManufacturer,
				expectedNumberOfTeeth, expectedWeight, expectedHasSafetyCutOut,
				expectedIsUsed, expectedColour, expectedCutLength,
				expectedIsExhibited);
		Chainsaw chainIn = new Chainsaw();

		boolean passedOk = true;

		try {
			XMLEncoder encoder = new XMLEncoder(
					new FileOutputStream("test.xml"));
			encoder.writeObject(chainOut);
			encoder.close();
		} catch (Exception e) {
			System.out.println("Exception when writing to file: " + e);
			passedOk = false;
		}

		try {
			XMLDecoder decoder = new XMLDecoder(new FileInputStream("test.xml"));
			chainIn = (Chainsaw) decoder.readObject();
			decoder.close();
		} catch (Exception e) {
			System.out.println("Exception when reading from file: " + e);
			passedOk = false;
		}

		if (passedOk && (!chainOut.equals(chainIn))) {
			System.out.println("Object read does not match object written.");
			passedOk = false;
		}
		if (passedOk) {
			System.out.println("All parts of Test 4 passed OK.");
		}

	}

}