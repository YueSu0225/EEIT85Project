package tw.rc.tags;

import java.io.IOException;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class HelloTag extends SimpleTagSupport{
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void doTag() throws JspException, IOException {
		JspContext jsp = getJspContext();
		JspWriter out = jsp.getOut();
		
		
		if(name != null) {
			out.print(String.format("Hello, %s", name));
	
		}else {
			out.print(String.format("Hello, %s", "world"));
		}
	
		JspFragment body = getJspBody();
		if(body != null ) {
			
			out.print("I am body");
			body.invoke(out);
		}
		
	}
	
}
