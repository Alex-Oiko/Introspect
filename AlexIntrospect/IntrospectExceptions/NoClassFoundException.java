package AlexIntrospect.IntrospectExceptions;

public class NoClassFoundException extends Exception{
	
	private static final String ERROR_MESSAGE = "The object supplied could not be found";
	
	public NoClassFoundException(){
		super(ERROR_MESSAGE);
	}

}
