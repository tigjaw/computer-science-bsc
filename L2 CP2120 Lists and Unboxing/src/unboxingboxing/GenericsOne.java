package unboxingboxing;

public class GenericsOne {

	public static void main(String[] args) {
		System.out.println("Test One:\n---");
		test01();
		System.out.println("\nTest Two:\n---");
		test02();
	}

	public static void test01() {
		String s1 = "Hello";
		String s2 = "Hello";
		boolean s3 = s1 == s2;
		System.out.println("boolean result " + s3);
		if (s1 == s2) {
			System.out.println("Result: True");
		} else {
			System.out.println("Result: False");
		}
	}

	public static void test02() {
		String s1 = "Hello";
		String s2 = new String("Hello");
		boolean s3 = s1 == s2;
		System.out.println("boolean result " + s3);
		if (s1 == s2) {
			System.out.println("Result: True");
		} else {
			System.out.println("Result: False");
		}
	}
}