package htmlInterfaces;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Vector;

import sessionTracking.SessionCleaner;
import sessionTracking.SessionTable;

/**
 * <b>Class containing static methods to handle annotations </b> </br>
 * This class will be used by interface based on annotations.
 * @author Alexander
 * @version 1.0
 * @see FetchMethods,FetchMethodAnnotation,</br>
 * ExecuteMethodsAsSetters, ExecuteMethodsAsGetter
 */
public class AnnotationTool
{
	
	/**
	 * Searches a class for all methods annotated with the given Annotation
	 * @param cl = class to search in
	 * @param a = class of the annotation that is searched
	 * @return List of annotated methods (can be empty but not null)
	 */
	@SuppressWarnings("unchecked")
	public static Vector<Method> FetchMethods(Class cl,Class a)
	{
		Vector<Method> methods=new Vector<Method>();
		for (Method i:cl.getDeclaredMethods())
		{
			if (i.isAnnotationPresent(a))
			{
				methods.add(i);
			}
		}
		return methods;
	}
	
	/**
	 * Searches a class for all Annotations, relating to a method, of the same class as the given annotation
	 * class.
     * @param cl = class that is searched. 
	 * @param annotation = annotation class of interest
	 * @return Vector of matching Annotations (can be empty but not null)
	 */
	public static Vector<Annotation> FetchMethodAnnotation(Class cl,Class annotation)
	{
		Vector<Annotation> selection=new Vector<Annotation>();
		for (Method i:cl.getDeclaredMethods())
		{
			Annotation[] annotations=i.getDeclaredAnnotations();
			for(Annotation a:annotations)
			{
				if (a.annotationType().equals(annotation))
				{
					selection.add(a);
				}
			}
		}
		return selection;
	}
	
	/**
	 * Executes a list of methods on an object as if it were setters
	 * @param object = object of which the methods are invoked
	 * @param methods = vector of methods to invoke (methods must have one parameter)
	 * @param values = vector of parameters (order has to be right no type checking)
	 * @return Vector of results (can be empty but not null)
	 */
	public static void ExecuteMethodsAsSetters(Object object, Vector<Method> methods, Vector<Object> values)
	{
		Iterator<Object> iterator = values.iterator();
		for (Method i:methods)
		{
				try
				{
					i.invoke(object,iterator.next());
				} catch (Exception e)
				{
					e.printStackTrace();
				}
		}
	}
	
	/**
	 * Executes a list of methods of an object as if it were getters and stores the result
	 * in a vector
	 * @param object = object of which the methods are invoked
	 * @param methods = vector of methods to invoke (methods must not have parameters)
	 * @return List of results (can be empty but not null)
	 */
	public static Vector<Object> ExecuteMethodsAsGetter(Object object,Vector<Method> methods)
	{
		Vector<Object> values = new Vector<Object>();
		for (Method i:methods)
		{
				try
				{
					values.add(i.invoke(object));
				} catch (Exception e)
				{
					e.printStackTrace();
				}
		}
		return values;
	}
	
}
