package modal.services;

import java.util.Calendar;
import java.util.Date;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {

	private OnlinePaymentService paymentService;

	public ContractService() {
	}

	public ContractService(OnlinePaymentService paymentService) {
		this.paymentService = paymentService;
	}

	public void processContract(Contract contract, Integer months) {

		double baseValue = contract.getTotalValue() / months;

		Date date = contract.getDate();
		Calendar calendar = Calendar.getInstance();

		for (int i = 1; i <= months; i++) {

			double interest = paymentService.interest(baseValue, i);
			double paymentFee = paymentService.paymentFee((baseValue + interest));
			double amount = baseValue + interest + paymentFee;

			calendar.setTime(date);
			calendar.add(Calendar.MONTH, i);

			contract.getInstallments().add(new Installment(calendar.getTime(), amount));

		}
	}

	public OnlinePaymentService getPaymentService() {
		return paymentService;
	}

	public void setPaymentService(OnlinePaymentService paymentService) {
		this.paymentService = paymentService;
	}

}
