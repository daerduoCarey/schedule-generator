package htmlBuilder;

import pseudoServlets.PseudoServlet;
import xml.ElementWithChildren;
import xml.ElementWithValue;
import xml.XMLDocument;
import other.Globals;

public class Site 
{
	private final String fileName="site.xml";
	
	public enum TabName
	{
		Login,Search,Schedule,Accounts,Buildings,Educators,Students,Courses,EditCalendar;
		
		public String toLanguageTag()
		{
			return "##"+toString()+"##";
		}
	}
	
	private final String tabTitle="{tabtitle}";
	private final String tabContent="{tabcontent}";
	private final String pageContent="{pagecontent}";
	private final String frameSrc="{framesrc}";
	private final String actionLogin="{actionlogin}";
	private final String messageLogin="{messagelogin}";
	
	private String htmlPage;
	
	private String htmlTab;
	
	private String htmlFrame;
	
	private String loginForm;
	
	private String htmlcode;
	
	private void init()
	{
		htmlcode=new String(htmlPage);
	}
	
	private boolean loadTemplate()
	{
		XMLDocument template = new XMLDocument(Globals.templatePath+fileName);
		if (template.load())
		{
			htmlPage=ElementWithValue.class.cast(template.getElement("HTMLPAGE")).getValue();
			htmlTab=ElementWithValue.class.cast(template.getElement("TAB")).getValue();
			htmlFrame=ElementWithValue.class.cast(template.getElement("IFRAME")).getValue();
			loginForm=ElementWithValue.class.cast(template.getElement("LOGIN")).getValue();
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public Site()
	{
		loadTemplate();
		init();
	}
	
	public String getHtmlCode() 
	{
		return htmlcode.replace(pageContent,"");
	}

	public void clear()
	{
		init();
	}
	
	public void reloadTemplate()
	{
		loadTemplate();
	}
		
	private String createTab(String tabtitle,String tabcontent)
	{
		String tab = htmlTab;
		tab=tab.replace(tabTitle, tabtitle);
		tab=tab.replace(tabContent,tabcontent);
		return tab;
	}
	
	private  String createIFrame(String src)
	{
		return htmlFrame.replace(frameSrc,src);
	}
	
	private  String createTabWithIFrame(String tabtitle,String src)
	{
		return createTab(tabtitle,createIFrame(src));
	}
	
	public String createLoginForm(String action,String message)
	{
		String form = loginForm.replace(actionLogin, action);
		return form.replace(messageLogin, message);
	}
	
	private  void addContent(String content)
	{
		htmlcode=htmlcode.replace(pageContent, content+pageContent);
	}
	
	@Deprecated
	public void addTabWithIFrame(String tabname,String src)
	{
		addContent(createTabWithIFrame(tabname,src));
	}	
	@Deprecated
	public void addTab(String tabname,String content)
	{
		addContent(createTab(tabname,content));
	}
	
	public void addTabWithIFrame(TabName tabname,String src)
	{
		addContent(createTabWithIFrame(tabname.toLanguageTag(),src));
	}	
	public void addTab(TabName tabname,String content)
	{
		addContent(createTab(tabname.toLanguageTag(),content));
	}

	
}
