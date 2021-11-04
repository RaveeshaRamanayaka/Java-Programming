public class MainApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Receptionist rs = new Receptionist("Amal","958432982V", 1);
		
		Counsellor co = new Counsellor("Rathnayake", "925438761V", "nRathnayake@gmail.com", "Dermatologist");
		co.addHospital("Leesons Hospital");
		co.addHospital("Lanka Hospital");
		co.addHospital("Hemas Hospitals");
		co.addHospital("Nawaloka Hospital");
		co.addHospital("Asiri Surgical Hospital");
		
		double revenue = rs.calculateRevenue(co);
		System.out.println("Revenue for " + co.getName() + " : " + revenue + "\n");
		
		rs.printDetails();
		System.out.println();
		
		co.printDetails();
	}
}
