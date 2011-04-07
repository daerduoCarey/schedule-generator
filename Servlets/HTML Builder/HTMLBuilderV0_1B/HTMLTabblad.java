package HTMLBuilder2;

import java.util.Vector;

import xml.ElementWithChildren;
import xml.ElementWithValue;
import xml.XMLElement;


public class HTMLTabblad extends ContainerTemplate<HTMLFunction> implements HTMLable
{	
	static protected final String name="Tabblad";
	
	private String titel;
	
	@Override
	public String toHTML()
	{
		String html=new String();
		ElementWithValue e1= (ElementWithValue)(doc.getElement(top));
		html=html+(e1.getValue());
		for (HTMLable e:elements)
		{
			html=html+e.toHTML();
		}
		ElementWithValue e2= (ElementWithValue)(doc.getElement(bottom));
		html=html+(e2.getValue());
		return html;
	}

	public void setTitel(String titel)
	{
		this.titel = titel;
	}

	public String getTitel()
	{
		return titel;
	}
	
	
	
}