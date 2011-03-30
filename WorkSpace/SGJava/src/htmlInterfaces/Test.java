package htmlInterfaces;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Vector;

/**
 * <b>This is a class for testing and will be removed later on. </b></br>
 * This class should be replaced with a JUnit TEST
 * @author Alexander
 * @version 0.1
 * @see HTMLTablable
 */
public class Test
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		// test 1
		Dummy dum = new Dummy("Dum",16);
		Vector<Method> getters=AnnotationTool.FetchMethods(dum.getClass(), TableInput.class);
		for (Method i:getters)
		{
			System.out.println(i.toString());
		}
		System.out.println();
		
		Vector<Object> objects =AnnotationTool.ExecuteMethodsAsGetter(dum,getters);
		for (Object i:objects)
		{
			System.out.println(i.toString());
		}
		System.out.println();
		
		// test 2
		Vector<Method> setters=AnnotationTool.FetchMethods(dum.getClass(), FormOutput.class);
		for (Method i:setters)
		{
			System.out.println(i.toString());
		}
		System.out.println();
		
		Vector<Object> values=new Vector<Object>();
		values.add(33);
		values.add(new String("Dumdum"));
		AnnotationTool.ExecuteMethodsAsSetters(dum,setters,values);

		System.out.println(dum.getAge());
		System.out.println(dum.getName());
		System.out.println();
		
		// test 3
		Vector<Annotation> annotations=AnnotationTool.FetchMethodAnnotation(dum.getClass(),TableInput.class);
		System.out.println(annotations.size());
		for (Annotation i:annotations)
		{
			System.out.println(i.toString());
		}
		System.out.println();
	}

}
