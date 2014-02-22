package exceptions;

/**
 * Classe qui gère les FatalException
 * 
 */
public class FatalException extends RuntimeException {
	private static final long serialVersionUID = 634584548335692632L;

	/**
	 * Constructeur d'exception
	 */
	public FatalException() {
		super();
	}

	/**
	 * Constructeur d'exception avec paramètres
	 * 
	 * @param s
	 *            Message de l'exception
	 */
	public FatalException(final String s) {
		super(s);
	}

	/**
	 * Constructeur avec un paramètre Throwable
	 * 
	 * @param e
	 *            De type Throwable
	 */
	public FatalException(final Throwable e) {
		super(e);
	}
}
