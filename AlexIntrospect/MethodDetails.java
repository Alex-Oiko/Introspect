package AlexIntrospect;

import java.util.ArrayList;

public class MethodDetails {
	
	private ArrayList<Class> arguments = new ArrayList<Class>();
	private String name;
	
	public MethodDetails(String name,ArrayList<Class> arguments){
		this.arguments=arguments;
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public ArrayList<Class> getArguments() {
		return arguments;
	}


	public void setArguments(ArrayList<Class> arguments) {
		this.arguments = arguments;
	}
	
	

}
