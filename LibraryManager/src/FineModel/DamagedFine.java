package FineModel;

import LoanModel.LoanDetail;

public class DamagedFine implements FinePolicy {

	@Override
	public double calculateFine(LoanDetail detail) {
		// TODO Auto-generated method stub
		return detail.getBookItem().getBookTitle().getPrice() * 0.5; // đền nửa tiền cuốn sách
	}
}
