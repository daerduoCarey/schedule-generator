package htmlInterfaces;

import htmlInterfaces.HTMLTablable.TableHead;
import htmlInterfaces.HTMLTablable.TableInput;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Vector;

/**
 * <b>Class containing static methods to handle HTMLinterfaces </b> </br>
 * This class will be used by interfaces based on annotations.
 * @author Alexander
 * @version 1.0
 * @see HTMLTablable, HTMLFormable
 */
public class HTMLInterfaceTool 
{	
	/**
	 * Checks if the HTMLTablable is well implemented in the given class
	 * The implementation is considered successful if there is one HeadTable 
	 * and the number of InputTable annotations is equal to number specified in
	 * The HeadTable annotation. 
	 */
	public static boolean checkHTMLInterface(Class<? extends HTMLTablable> cl)
	{
		Annotation classAnnotations= AnnotationTool.fetchClassAnnotation(cl,TableHead.class);
		if (classAnnotations!=null)
		{
			int numberOfinputs=TableHead.class.cast(classAnnotations).numberOfInputs();
			Vector<Annotation> methodAnnotations= AnnotationTool.fetchMethodAnnotation(cl,TableInput.class);
			if ((methodAnnotations!=null)&& (methodAnnotations.size()==numberOfinputs))
			{
				return true;
			}
		}
		return false;
	}
	
	/**
	 * This method collects all information needed to represent a HTMLTablalbe
	 * object as a table. If the operation is succesful the resulting vector 
	 * will always contain 2 vectors of string of equal length. The first one 
	 * contains all the names of of the table-elements. The second one contains 
	 * the corresponding values.</br>
	 * This method doesn't check if the interface of the given object is well
	 * implemented. When an error occurs null is returned. </br></br>
	 * <center> !!! not yet finished !!!</center>
	 */
	public static Vector<Vector<String>> fetchTableContent(HTMLTablable obj)
	{
		TableHead tablehead= TableHead.class.cast(AnnotationTool.fetchClassAnnotation(obj.getClass(),TableHead.class));
		Vector<Annotation> tableinputs= AnnotationTool.fetchMethodAnnotation(obj.getClass(),TableInput.class);
		Vector<Method> methods=  AnnotationTool.fetchMethods(obj.getClass(),TableInput.class) ;
		
		Vector<String> names= new Vector<String>();
		Vector<String> values= new Vector<String>();
		names.setSize((tablehead.numberOfInputs()));
		values.setSize((tablehead.numberOfInputs()));
		
		int index;
		TableInput ti;
		for (Annotation i: tableinputs)
		{
			 ti=TableInput.class.cast(i);
			 index=ti.order()-1;
			 names.set(index,ti.text());
			 try 
			 {
			   values.set(index,methods.get(index).invoke(obj).toString());
			 } catch (Exception e) 
			 {
				e.printStackTrace();
				return null;
			 }
		}
		Vector<Vector<String>> result = new Vector<Vector<String>>();
		result.add(names);
		result.add(values);
		return result;
	}
	
}