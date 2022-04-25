package theDogDoor;

public class DogDoorSimulator {
	public static void main(String[]args){
		//test01();
		test02();
	}
	
	public static void test01(){
		DogDoor door = new DogDoor();
		Remote remote = new Remote(door);
		
		System.out.println("Fido barks to go outside");
		remote.pressButton();
		System.out.println("Fido goes outside");
		remote.pressButton();
		System.out.println("Fido's all done");
		remote.pressButton();
		System.out.println("Fido wants to come back inside");
		remote.pressButton();
		System.out.println("Fido is back inside");
		remote.pressButton();
	}
	
	public static void test02(){
		DogDoor door = new DogDoor();
		Remote remote = new Remote(door);
		
		System.out.println("Fido barks to go outside");
		remote.pressButton();
		System.out.println("Fido goes outside");
		System.out.println("Fido's all done");
		System.out.println("Fido wants to come back inside");
		System.out.println("Fido is back inside");
	}
}