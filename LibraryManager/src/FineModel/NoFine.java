package FineModel;

import LoanModel.LoanDetail;

public class NoFine implements FinePolicy {
	@Override
	public double calculateFine(LoanDetail detail) {
		return 0;
	}

}
