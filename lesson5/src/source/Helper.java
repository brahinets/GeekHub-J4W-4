/**
 * Class: Helper
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 16.11.13
 * Time: 12:27
 * Mail: ysb.kanivtsi@gmail.com
 */

package source;

public abstract class Helper {

    public static String getExtension(String pathToSource) {
        return pathToSource.lastIndexOf( '.' )>0 ? pathToSource.substring( pathToSource.lastIndexOf( '.' )+1 ) : "";
    }

}
