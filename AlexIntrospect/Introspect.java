package AlexIntrospect;

import java.beans.MethodDescriptor;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import AlexIntrospect.IntrospectExceptions.NoClassFoundException;



/**
 * @author aoikonomou
 * Success up to a point. It prints the correct data after going through the introspection
 * NEEDS
 * -------
 * 1)Incorporate convertToObject in the introspect methods
 * 2)Make the introspect method work with multiple arguments and not just one(see method getMethodArguments)
 */
public class Introspect {
	
	/**
	 * Does the actual intropection of the object
	 * 
	 * @param obj
	 * @param values
	 * @return
	 * @throws NoClassFoundException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException
	 */
	public static Object introspect(Object obj,HashMap<String,Object> values) throws NoClassFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		Class theClass = getClass(obj);
		HashMap<String,Method> fieldToMethod=initializeMethodToValue(theClass.getDeclaredFields(), getSetters(theClass));
		Iterator it = values.entrySet().iterator();
		while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        Method m = fieldToMethod.get(pairs.getKey());
	        //MethodDetails md = getMethodDetails(m);
	        Object[] arguments = new Object[1];
	        arguments[0]=pairs.getValue();
	      //arguments[0]=convertToObject(md.getArguments().get(0), pairs.getValue().toString());
	        m.invoke(obj, arguments);
	        
	    }
		return obj;
		
	}
	
	/*public static Object[] getMethodArguments(MethodDetails md,HashMap<String,Object> values){
		ArrayList<Class>  args= md.getArguments();
		Object[] arguments = new Object[args.size()];
		Iterator it = values.entrySet().iterator();
		while (it.hasNext()) {
	        Map.Entry pairs = (Map.Entry)it.next();
	        Method m = fieldToMethod.get(pairs.getValue());
	        MethodDetails md = getMethodDetails(m);
	        m.invoke(obj, args)
	        //System.out.println(pairs.getKey() + " = " + pairs.getValue()+" "+fieldToRequest.get(pairs.getKey()).getName());
	        
	    }
	}*/
	
	/**
	 * Converts the the value from a string to the given datatype and returns it in the appropriate object
	 * 
	 * @param datatype
	 * @param value
	 * @return
	 */
	public static Object convertToObject(String datatype,String value){
		switch(datatype){
		case "java.lang.Integer":return new Integer(value);
		case "java.lang.String":return value;
		case "java.lang.Double":return new Double(value);
		case "java.lang.Long":return new Long(value);
		case "java.lang.Short":return new Short(value);
		case "java.math.BigInteger":return new BigInteger(value);
		case "java.math.BigDecimal":return new BigDecimal(value);
		case "java.lang.Boolean":return new Boolean(value);
		case "java.sql.Date":return new Date(value);
		}
		return "This datatype is not defined in Introspect";
	}
	
	/**
	 * Initialises the hashmap where on the key you have the field/parameter_name and on the other the method
	 * 
	 * @param fields
	 * @param setters
	 * @return
	 */
	public static HashMap<String,Method> initializeMethodToValue(Field[] fields,ArrayList<Method> setters){
		HashMap<String,Method> fieldToMethod = new HashMap<String,Method>();
		for(int i=0;i<fields.length;i++){
			fieldToMethod.put(fields[i].getName(), setters.get(getIndexFromName(setters, "set"+fields[i].getName())));
		}
		return fieldToMethod;
		
	}
	/**
	 * Returns the index where a method is located in the arraylist based on the methods name.
	 * 
	 * @param methods
	 * @param possibleSetName
	 * @return
	 */
	public static int getIndexFromName(ArrayList<Method> methods,String possibleSetName){
		for(int i=0;i<methods.size();i++){
			if(methods.get(i).getName().equalsIgnoreCase(possibleSetName)){
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * It formats the fieldName attribute so it looks like a set method, that sets the a fieldname in a class
	 * 
	 * @param fieldName
	 * @return
	 */
	public static String formatFieldForSet(String fieldName){
		fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		return "set"+fieldName;
	}
	/**
	 * Creates and returns a MethodDetais object depending on the method argument.
	 * 
	 * @param method
	 * @return
	 */
	public static MethodDetails getMethodDetails(Method method){
		return new MethodDetails(method.getName(),new ArrayList<Class>(Arrays.asList(method.getParameterTypes())));
		
	}
	
	/**
	 * Returns in an ArrayList all the setter methods of the obj
	 * 
	 * @param obj
	 * @return
	 */
	public static ArrayList<Method> getSetters(Class theClass){
		Method[] methods=theClass.getMethods();
		ArrayList<Method> setters = new ArrayList<Method>();
		if(methods==null)
			return setters;
		for(int i=0;i<methods.length;i++){
			if(isSetter(methods[i].getName()))
				setters.add(methods[i]);
		}
		return setters;
	}
	
	/**
	 * Returns in an ArrayList all the getter methods of the obj
	 * 
	 * @param obj
	 * @return
	 */
	public static ArrayList<Method> getGetters(Class theClass){
		Method[] methods=theClass.getMethods();
		ArrayList<Method> getters = new ArrayList<Method>();
		if(methods==null)
			return getters;
		for(int i=0;i<methods.length;i++){
			if(isGetter(methods[i].getName()))
				getters.add(methods[i]);
		}
		return getters;
	}
	
	/**
	 * Returns all the methods of the obj
	 * 
	 * @param obj
	 * @return
	 */
	public static Method[] getAllMethods(Object obj){
		return obj.getClass().getMethods();
	}
	
	/**
	 * Returns the class of the object
	 * 
	 * @param obj
	 * @return
	 * @throws NoClassFoundException
	 */
	public static Class getClass(Object obj) throws NoClassFoundException{
		if(obj==null)
			throw new NoClassFoundException();
		return obj.getClass();
	}
	
	/**Checks of the name contains "set". This is so to determine whether the method is a setter. The name supplied is the method name
	 * 
	 * 
	 * @param name
	 * @return
	 */
	public static boolean isSetter(String name){
		if(name.contains("set"))
			return true;
		else return false;
	}
	
	/**Checks of the name contains "get". This is so to determine whether the method is a getter. The name supplied is the method name
	 * 
	 * 
	 * @param name
	 * @return
	 */
	public static boolean isGetter(String name){
		if(name.contains("get"))
			return true;
		else return false;
	}

}
