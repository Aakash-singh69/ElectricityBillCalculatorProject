package javaFiles;

import java.sql.SQLException;
import java.util.Scanner;

import jdbc.AdminUtility;
import jdbc.ConsumerUtility;

public class MainClass {
	
/*	public static void cls() {
		try {
			new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();
		}catch(Exception E) {
			System.out.println(E);
		}
	}
*/	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		Scanner scanner = new Scanner(System.in);
		int choice;
		System.out.flush();
		System.out.println("---------WELCOME TO ELECTRICITY BILL CALCULATOR---------");
		System.out.println("Choose your role");
		System.out.println("1) Administrator");
		System.out.println("2) Consumer");
		System.out.println("3) Exit");
		choice = scanner.nextInt();
		switch (choice) {
			case 1: {
      			    System.out.print("Enter Admin ID :");
                    String Id = scanner.next();
                    int adminId=0;
					try {
						adminId=Integer.parseInt(Id);
					}catch(Exception e) {
						System.out.println("Please enter a valid adminId.\n\n");
						MainClass.main(null);
					}

				
					scanner.nextLine();
					System.out.print("Enter Password :");
					String pass = scanner.nextLine();
					if (Admin.validateAdmin(adminId, pass)) {
						boolean rule2 = true;
						String answer;
						int option;
						System.out.println("Welcome Admin!");
						while (rule2) {
							System.out.println("Choose an option: ");
							System.out.println("1) Add units consumed in a month for a consumer");
							System.out.println("2) Generate bills for all consumers");
							System.out.println("3) Generate bills for an area");
							System.out.println("4) Generate bills for a city");
							System.out.println("5) Generate bills for a month");
							System.out.println("6) Generate bills for an year");
							option = scanner.nextInt();
							switch (option) {
								case 1: {
									AdminUtility.addUnitsConsumed();
								}
								break;
								case 2: {
									AdminUtility.generateBillsForAllCustomers();
									
								}
								break;
								case 3: {
									AdminUtility.generateBillsForAnArea();
								}
								break;
								case 4: {
									AdminUtility.generateBillsForACity();
								}
								break;
								case 5: {
									AdminUtility.generateBillByMonth();
								}
								break;
								case 6: {
									AdminUtility.generateBillByYear();
								}
								break;
								default:
									System.out.println("Invalid choice.");
									rule2 = false;
							}
							System.out.println("Do you want to continue y or n");
							answer = scanner.next();
							scanner.nextLine();
							if (!answer.equalsIgnoreCase("y")) {
								rule2 = false;
							} else {
								rule2 = true;
							}
						}
					} else {
						System.out.println("ERROR! entered details are wrong please enter correct details");
					}
			}
			break;
			case 2: {
				System.out.println("Choose option");
				System.out.println("1) New User");
				System.out.println("2) Existing User");
				int option1 = scanner.nextInt();
				switch (option1) {
					case 1: {
						Consumer cobj = ConsumerUtility.newRegistration();
						if (cobj == null) {
							System.out.println("Sorry, registration failed.");
							return;
						}
						System.out.print("Your Consumer ID: " + cobj.getId());
						System.out.print("\nYour Password: " + cobj.getPassword());
						System.out.println();
					}
						break;
					case 2: {
						Consumer cobj = ConsumerUtility.consumerLogin();
						if (cobj == null) {
							System.out.println("Invalid id or password");
							break;
						}
						System.out.println("*************  Welcome " + cobj.getName()+"  ******************\n");
						String ch = "yes";
						while (ch.equalsIgnoreCase("yes") || ch.equalsIgnoreCase("y")) {
							System.out.println("Choose an option");
							System.out.println("1. Get bill by month and year");
							System.out.println("2. Get bills by year");
							System.out.println("3. Get all your bills");
							System.out.print("Enter your choice: ");
							int option2 = scanner.nextInt();
							scanner.nextLine();
							System.out.println("-------------------------------");
							switch (option2) {
								case 1: {
									int flag=0;
									String arr[]= {"jan","feb","march","april","may","june","july","august","sep","oct","nov","dec"};
									System.out.print("Enter month: ");
									String month = scanner.nextLine();
									for(int i=0;i<arr.length;i++) {
										if(arr[i].equals(month)) {
											flag=1;
											break;													
										}
									}
									if(flag==0) {
										System.out.println("Please enter a valid month");	
									}
									
									System.out.print("Enter year: ");
									int year = scanner.nextInt();
									scanner.nextLine();
									ConsumerUtility.getBillByMonth(month, year, cobj.getId());
								}
									break;
								case 2: {
									System.out.print("Enter year: ");
									int year = scanner.nextInt();
									scanner.nextLine();
									ConsumerUtility.getBillsByYear(year, cobj.getId());
								}
									break;
								case 3: {
									ConsumerUtility.getAllBills(cobj.getId());
								}
									break;
								default:
									System.out.println("Invalid choice");
							}
							System.out.print("Do you wish to continue? y and n");
							ch = scanner.nextLine();
						}
					}
						break;
					default: {
						System.out.println("Invalid choice");
					}
				}
			}
				break;
				
			case 3:
				System.out.println("Program Terminated...");
				return;
			default:
				System.out.println("Invalid choice");
		}
		System.out.println("\nThank you for using Electricity Bill Calculator.\n");
		
		System.out.println("Directing you to Home Page.......\n");
		System.out.println("**************************************************************************\n");
		//cls();

		MainClass.main(null);
		return;
	}
}