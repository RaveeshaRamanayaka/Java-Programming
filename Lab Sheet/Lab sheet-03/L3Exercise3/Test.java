/*	Accessor and Mutator Methods
	(Getters and Setters)
*/

public class Test {

	public static void main(String[] args) {

		Student s1 = new Student("Raveesha", "IT0010", "Malabe");
		Student s2 = new Student("Ramanayaka", "IT0011", "Kandy");
		
		s1.setName("Amal");
		s1.setDit("IT020");
		s1.setAddress("Galle");

		s2.setName("Kamal");
		s2.setDit("IT0030");
		s2.setAddress("Gampaha");
		
		System.out.println(s1.getName());
		System.out.println(s1.getDit());
		System.out.println(s1.getAddress());

		System.out.println(s2.getName());
		System.out.println(s2.getDit());
		System.out.println(s2.getAddress());
	}

}
