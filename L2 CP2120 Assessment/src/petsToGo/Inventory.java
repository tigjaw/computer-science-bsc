package petsToGo;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;

public class Inventory {
	private LinkedList<Pet> pets;
	private LinkedList<Pet> petsFromXml;
	private int numberOfPets;

	public Inventory() {
		initialiseData();
	}

	public boolean addPet(Pet pet1) {
		if (pet1 != null) {
			pets.add(pet1);
			numberOfPets++;
			return true;
		} else {
			return false;
		}
	}

	public boolean remPet(int id) {
		boolean deleted = false;
		Iterator<Pet> it = pets.iterator();
		Pet loopPet;
		LinkedList<Pet> loopPets = new LinkedList<Pet>();
		while (it.hasNext()) {
			loopPet = it.next();
			if (loopPet.getPetID() == id) {
				loopPet.setSold(true);
				loopPets.add(loopPet);
				deleted = true;
				numberOfPets--;
			} else {
				loopPets.add(loopPet);
			}
		}
		pets = loopPets;
		return deleted;
	}

	public LinkedList<Pet> listAllPets() {
		Iterator<Pet> it = pets.iterator();
		Pet loopPet;
		LinkedList<Pet> loopPets = new LinkedList<Pet>();
		while (it.hasNext()) {
			loopPet = it.next();
			if (loopPet instanceof Pet && loopPet.getIsSold() != true) {
				loopPets.add(loopPet);
			}
		}
		return loopPets;
	}

	public LinkedList<Pet> listMammals() {
		Iterator<Pet> it = pets.iterator();
		Pet loopPet;
		LinkedList<Pet> loopPets = new LinkedList<Pet>();
		while (it.hasNext()) {
			loopPet = it.next();
			if (loopPet instanceof Mammal && loopPet.getIsSold() != true) {
				loopPets.add(loopPet);
			}
		}
		return loopPets;
	}

	public LinkedList<Pet> listBirds() {
		Iterator<Pet> it = pets.iterator();
		Pet loopPet;
		LinkedList<Pet> loopPets = new LinkedList<Pet>();
		while (it.hasNext()) {
			loopPet = it.next();
			if (loopPet instanceof Bird && loopPet.getIsSold() != true) {
				loopPets.add(loopPet);
			}
		}
		return loopPets;
	}

	public LinkedList<Pet> listReptiles() {
		Iterator<Pet> it = pets.iterator();
		Pet loopPet;
		LinkedList<Pet> loopPets = new LinkedList<Pet>();
		while (it.hasNext()) {
			loopPet = it.next();
			if (loopPet instanceof Reptile && loopPet.getIsSold() != true) {
				loopPets.add(loopPet);
			}
		}
		return loopPets;
	}

	public LinkedList<Pet> listAquatics() {
		Iterator<Pet> it = pets.iterator();
		Pet loopPet;
		LinkedList<Pet> loopPets = new LinkedList<Pet>();
		while (it.hasNext()) {
			loopPet = it.next();
			if (loopPet instanceof Aquatic && loopPet.getIsSold() != true) {
				loopPets.add(loopPet);
			}
		}
		return loopPets;
	}

	public LinkedList<Pet> listMarines() {
		Iterator<Pet> it = pets.iterator();
		Pet loopPet;
		LinkedList<Pet> loopPets = new LinkedList<Pet>();
		while (it.hasNext()) {
			loopPet = it.next();
			if (loopPet instanceof Marine && loopPet.getIsSold() != true) {
				loopPets.add(loopPet);
			}
		}
		return loopPets;
	}

	public LinkedList<Pet> listFresh() {
		Iterator<Pet> it = pets.iterator();
		Pet loopPet;
		LinkedList<Pet> loopPets = new LinkedList<Pet>();
		while (it.hasNext()) {
			loopPet = it.next();
			if (loopPet instanceof Freshwater && loopPet.getIsSold() != true) {
				loopPets.add(loopPet);
			}
		}
		return loopPets;
	}

	/**
	 * @return the numberOfPets
	 */
	public int getNumberOfPets() {
		return numberOfPets;
	}

	/**
	 * Gets data from XML
	 */
	private void initialiseData() {
		pets = new LinkedList<Pet>();
/*
 * this is the progress I made toward producing initialising the data
 * from the xml file. The data is saved and initialised but during
 * saving some data is lost, namely the values of the instanced of
 * the Money, Temp or IntAboveZero classes.
		try {
			XMLDecoder decoder = new XMLDecoder(new FileInputStream("inventory.xml"));
			pets = (LinkedList<Pet>) decoder.readObject();
			numberOfPets = pets.size();
			decoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
*/
		
		pets.add(new Bird("Pidgeon", Gender.male, new Age(
				new GregorianCalendar(2003, Calendar.NOVEMBER, 18)), new Money(
				33, 33), new Money(55, 55), Trilean.yes, Trilean.no,
				Trilean.yes, "England"));
		pets.add(new Reptile("iguana", Gender.female, new Age(
				new GregorianCalendar(2005, Calendar.MARCH, 24)), new Money(55,
				99), new Money(65, 89), Trilean.yes, new IntAboveZero(33),
				new Temp(55)));
		pets.add(new Mammal("Dog", Gender.male, new Age(new GregorianCalendar(
				2006, Calendar.JULY, 05)), new Money(230, 50), new Money(300,
				55), Cage.No_Cage, Trilean.yes, Trilean.no));
		pets.add(new Marine("Tang", Gender.female, new Age(
				new GregorianCalendar(2007, Calendar.JANUARY, 21)), new Money(
				30, 50), new Money(45, 99), FoodType.flakes,
				new IntAboveZero(2), new Salinity(60)));
		pets.add(new Freshwater("Carp", Gender.male, new Age(
				new GregorianCalendar(2007, Calendar.AUGUST, 13)), new Money(9,
				55), new Money(19, 22), FoodType.pellets, new IntAboveZero(5),
				Trilean.yes));
		numberOfPets = pets.size();
	}

	public void saveData() {
		try {
			XMLEncoder encoder = new XMLEncoder(new FileOutputStream("inventory.xml"));
			encoder.writeObject(pets);
			encoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}