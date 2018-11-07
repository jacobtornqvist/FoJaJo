package Exception;

public class BalanceTooLowException extends Exception {

	private static final long serialVersionUID = 1L;

	public BalanceTooLowException() {
		super("Transaktions fel: Summan p� kontot �r inte tillr�cklig");
	}

}
