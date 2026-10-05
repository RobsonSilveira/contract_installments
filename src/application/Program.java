package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import modal.services.ContractService;
import modal.services.PaypalService;
import model.entities.Contract;
import model.entities.Installment;

public class Program {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter contract data:");
		System.out.print("\nNumber:");
		int number = sc.nextInt();

		sc.nextLine();
		System.out.println("Date (dd/MM/yyyy):");
		Date date = sdf.parse(sc.nextLine());

		System.out.println("Contract value:");
		double totalValue = sc.nextDouble();

		Contract contract = new Contract(number, date, totalValue);

		System.out.println("Enter number of installments:");
		int numOfInstallments = sc.nextInt();

		ContractService cs = new ContractService(new PaypalService());

		cs.processContract(contract, numOfInstallments);

		System.out.println("Installments: ");
		for (Installment i : contract.getInstallments()) {
			System.out.println( sdf.format(i.getDueDate()) + " - " + i.getAmount());
		}

		sc.close();
	}

}
