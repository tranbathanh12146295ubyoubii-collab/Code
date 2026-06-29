
package FineModel;

import LoanModel.LoanDetail;

public interface FinePolicy {
	public double calculateFine(LoanDetail detail);
}
