package AlexIntrospect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import AlexIntrospect.IntrospectExceptions.NoClassFoundException;

public class Test {
	
	public static void main(String[] args){
		DummyPOJO dm = new DummyPOJO();
		
		
		ArrayList<Method> setters=null;
		ArrayList<Method> getters=null;
		try {
			setters = Introspect.getSetters(Introspect.getClass(dm));
			getters = Introspect.getGetters(Introspect.getClass(dm));
		} catch (NoClassFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		Method[] allMethods;
		Method setId=null;
		
		allMethods = Introspect.getAllMethods(dm);
		System.out.println("Setters-----------------------------");
		for(Method method: setters){
			if(method.getName().equals("setId")){
				setId=method;
			}
			System.out.println(method);
		}
		System.out.println("Getters-----------------------------");
		for(Method method: getters){
			System.out.println(method);
		}
		System.out.println("All Methods-----------------------------");
		for(Method method: allMethods){
			System.out.println(method);
		}
		//System.out.println(setId);
		MethodDetails md = Introspect.getMethodDetails(setId);
		ArrayList<Class> arr = md.getArguments();
		String name = md.getName();
		System.out.println("Name of method is "+name);
		System.out.println("Argument types");
		for(Class c:arr){
			System.out.println(c.getName());
		}
		
		System.out.println("==================================");
		HashMap<String,Object> values = new HashMap<String,Object>();
		values.put("id",3);
		values.put("fname","alex");
		values.put("lname","oiko");
		values.put("email","alex_oiko@hotmail.com");
		values.put("password","trololo");
		values.put("username","yolex");
		values.put("mobileNo","2108940190");
		values.put("birthday",new Date(1992,11,29));
		
		System.out.println("=====================");
		System.out.println("Before Introspect");
		System.out.println(dm.getId());
		System.out.println(dm.getFname());
		System.out.println(dm.getLname());
		System.out.println(dm.getEmail());
		System.out.println(dm.getPassword());
		System.out.println(dm.getUsername());
		System.out.println(dm.getMobileNo());
		System.out.println(dm.getBirthday());
		
		
		try {
			Introspect.introspect(dm, values);
		} catch (NoClassFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(Introspect.introspect(obj, values));
		System.out.println("=====================");
		System.out.println("Introspect Results");
		System.out.println(dm.getId());
		System.out.println(dm.getFname());
		System.out.println(dm.getLname());
		System.out.println(dm.getEmail());
		System.out.println(dm.getPassword());
		System.out.println(dm.getUsername());
		System.out.println(dm.getMobileNo());
		System.out.println(dm.getBirthday());
		
		
		
		
	}

}
