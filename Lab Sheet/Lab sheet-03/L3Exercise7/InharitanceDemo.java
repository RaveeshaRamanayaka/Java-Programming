public class InheritanceDemo { // Begining of the Class

	public static void main(String[] args) { // Begining of the Method
		
		Person p1 = new Person();
		p1.showDetails(); // Default Constructor Called
		System.out.println("");
		
		Student s1 = new Student("Raveesha", "Malabe", "IT0010"); 
		s1.showDetails();
		System.out.println("");
		
		PartTimeStudent pts = new PartTimeStudent("Nimal", "Anuradhapura", "IT0017",6);
		pts.showDetails();

	} // End of the Method

} // End of the Class
