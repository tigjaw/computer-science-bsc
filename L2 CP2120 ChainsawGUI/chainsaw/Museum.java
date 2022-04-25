package chainsaw;

import java.beans.*;
import java.io.*;

/**
 * Class contains most of the methods that provide the main functions of the
 * program.
 * 
 * @author Kevan Buckley and modified by Joshua Adam Woodyatt.
 * @version 2.1, April 2008, (based on v2.0, February 2008)
 */
public class Museum {
	/**
	 * chainsaws based on Chainsaw.
	 */
	private Chainsaw[] chainsaws;
	/**
	 * integer incremented as chainsaws created.
	 */
	private int numberOfChainsaws;

	/**
	 * Museum constructor calls initialiseData() method.
	 */
	public Museum() {
		initialiseData();
	}

	/**
	 * Used to delete a chainsaw. Returns boolean.
	 * 
	 * @param id
	 *            unqiue id of Chainsaw
	 * @return returns boolean value.
	 */
	public boolean deleteChainsaw(int id) {
		int thisId;
		int i = 0;

		while (i < numberOfChainsaws) {
			thisId = chainsaws[i].getId();

			if (thisId == id) {
				chainsaws[i].delete();
				return true;
			}
			i++;
		}
		return false;
	}

	/**
	 * Used to exhibit a chainsaw. Returns boolean.
	 * 
	 * @param id
	 *            id unqiue id of Chainsaw
	 * @return returns boolean value.
	 */
	public boolean exhibitChainsaw(int id) {
		int thisId;
		int i = 0;

		while (i < numberOfChainsaws) {
			thisId = chainsaws[i].getId();

			if (thisId == id) {
				if (chainsaws[i].getIsDeleted()
						|| chainsaws[i].getIsExhibited())
					return false;
				else {
					chainsaws[i].setIsExhibited(true);
					return true;
				}
			}
			i++;
		}
		return false;
	}

	/**
	 * @param id
	 *            id unqiue id of Chainsaw
	 * @return returns boolean value.
	 */
	public boolean returnChainsaw(int id) {
		int thisId;
		int i = 0;

		while (i < numberOfChainsaws) {
			thisId = chainsaws[i].getId();

			if (thisId == id) {
				if (chainsaws[i].getIsDeleted()
						|| !chainsaws[i].getIsExhibited())
					return false;
				else {
					chainsaws[i].setIsExhibited(false);
					return true;
				}
			}
			i++;
		}
		return false;
	}

	/**
	 * method to add a chainsaw.
	 * 
	 * @param chainsaw
	 *            chainsaw based on Chainsaw.
	 * @return returns boolean value.
	 */
	public boolean addChainsaw(Chainsaw chainsaw) {
		if (numberOfChainsaws + 1 < chainsaws.length) {
			chainsaws[numberOfChainsaws] = chainsaw;
			numberOfChainsaws++;
			return true;
		}
		return false;
	}

	/**
	 * Returns array of all non-deleted chainsaws.
	 * 
	 * @return results Returns the resulting array of Chainsaws.
	 */
	public Chainsaw[] getAllChainsaws() {
		int[] matchingIndices = new int[numberOfChainsaws];
		int nMatches = 0;
		int i;

		for (i = 0; i < numberOfChainsaws; i++) {
			if (!chainsaws[i].getIsDeleted()) {
				matchingIndices[nMatches] = i;
				nMatches++;
			}
		}

		Chainsaw[] results = new Chainsaw[nMatches];
		for (i = 0; i < nMatches; i++) {
			results[i] = chainsaws[matchingIndices[i]];
		}

		return results;
	}

	/**
	 * Returns array of Chainsaws with specified manufacturer.
	 * 
	 * @param manufacturer
	 *            manufacturer of Chainsaw.
	 * @return results Returns the resulting array of Chainsaws.
	 */
	public Chainsaw[] getChainsawsByManufacturer(String manufacturer) {
		int[] matchingIndices = new int[numberOfChainsaws];
		int nMatches = 0;
		int i;

		for (i = 0; i < numberOfChainsaws; i++) {
			if (chainsaws[i].getManufacturer().equalsIgnoreCase(manufacturer)
					&& !chainsaws[i].getIsDeleted()) {
				matchingIndices[nMatches] = i;
				nMatches++;
			}
		}

		Chainsaw[] results = new Chainsaw[nMatches];
		for (i = 0; i < nMatches; i++) {
			results[i] = chainsaws[matchingIndices[i]];
		}

		return results;
	}

	/**
	 * Returns array of non deleted chainsaws that are on exhibition.
	 * 
	 * @return results Returns the resulting array of Chainsaws.
	 */
	public Chainsaw[] getChainsawsOnExhibition() {
		int[] matchingIndices = new int[numberOfChainsaws];
		int nMatches = 0;
		int i;

		for (i = 0; i < numberOfChainsaws; i++) {
			if (chainsaws[i].getIsExhibited() && !chainsaws[i].getIsDeleted()) {
				matchingIndices[nMatches] = i;
				nMatches++;
			}
		}

		Chainsaw[] results = new Chainsaw[nMatches];
		for (i = 0; i < nMatches; i++) {
			results[i] = chainsaws[matchingIndices[i]];
		}

		return results;
	}

	/**
	 * Returns array of chainsaws that are not on exhibition.
	 * 
	 * @return results Returns the resulting array of Chainsaws.
	 */
	public Chainsaw[] getChainsawsInArchive() {
		int[] matchingIndices = new int[numberOfChainsaws];
		int nMatches = 0;
		int i;

		for (i = 0; i < numberOfChainsaws; i++) {
			if (!chainsaws[i].getIsExhibited() && !chainsaws[i].getIsDeleted()) {
				matchingIndices[nMatches] = i;
				nMatches++;
			}
		}

		Chainsaw[] results = new Chainsaw[nMatches];
		for (i = 0; i < nMatches; i++) {
			results[i] = chainsaws[matchingIndices[i]];
		}

		return results;
	}

	/**
	 * Initialises data from xml file and produces chainsaws array.
	 */
	private void initialiseData() {
		try {
			XMLDecoder decoder = new XMLDecoder(new FileInputStream(
					"chainsaws.xml"));
			chainsaws = (Chainsaw[]) decoder.readObject();
			decoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < chainsaws.length; i++) {
			if (!(chainsaws[i] == null))
				numberOfChainsaws = i + 1;
		}
	}

	/**
	 * Saves chainsaws array to xml file.
	 */
	public void saveData() {
		try {
			XMLEncoder encoder = new XMLEncoder(new FileOutputStream(
					"chainsaws.xml"));
			encoder.writeObject(chainsaws);
			encoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}