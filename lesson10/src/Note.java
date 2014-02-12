import java.util.Date;

/**
 * Created by Yarik on 11.02.14.
 *
 * Note
 */
public class Note{

    String name;
    String value;
    Date creationDate;

    public Note(String name, String value, Date creationDate) {
        this.name = name;
        this.value = value;
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
