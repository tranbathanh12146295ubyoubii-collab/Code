package BookModel;

import FineModel.FinePolicy;
import FineModel.*;

public enum Condition {
	NEW(new NoFine(), "Mới"),
	DAMAGED(new DamagedFine(), "Hỏng"),
	LOST(new LostFine(), "Mât");
	
	private FinePolicy finePolicy;
	private String decription;

	private Condition(FinePolicy finePolicy, String decription) {
		this.finePolicy = finePolicy;
		this.decription = decription;
	}
	public FinePolicy getFinePolicy() {
		return finePolicy;
	}
	public void setFinePolicy(FinePolicy finePolicy) {
		this.finePolicy = finePolicy;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}
}
