import java.util.*;

/**
 * Created by Yarik on 11.02.14.
 *
 * Notes manager
 *  - add note
 *  - delete note with some ID
 */
public class NotesManager {

    public static Map<Integer, Note> notes = new HashMap<>();
    private static Integer index = 0;

    public static void addNote(String name, String value, Date creationDate){
        notes.put(index, new Note(name, value, creationDate));
        index++;
    }


    public static void removeNote(int id){
        if(notes.containsKey(id)){
            notes.remove(id);
        }
    }
}
