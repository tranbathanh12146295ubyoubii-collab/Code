package FineModel;

import LoanModel.LoanDetail;

public class LostFine implements FinePolicy {
	@Override
	public double calculateFine(LoanDetail detail) {
		return detail.getBookItem().getBookTitle().getPrice() + 10000; // = tiền sách + phí xử lý 10000
	}
}
