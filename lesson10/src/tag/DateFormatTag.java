package tag; /**
 * Created by Yarik on 12.02.14.
 */

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatTag extends TagSupport{
    private Date date;

    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
            out.print(dateFormatter.format(date));
        } catch(IOException ioe) {
            throw new JspException("Error: " + ioe.getMessage());
        }
        return SKIP_BODY;
    }


    public void setDate(Date date) {
        this.date = date;
    }

}