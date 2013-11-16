/**
 * Class: SourceLoader
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 16.11.13
 * Time: 12:47
 * Mail: ysb.kanivtsi@gmail.com
 */

package source;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * SourceLoader contains all implementations of SourceProviders to be able to load different sources.
 */
public class SourceLoader {
    private List<SourceProvider> sourceProviders = new ArrayList<>();

    public SourceLoader() {
        sourceProviders.add(new FileSourceProvider());
        sourceProviders.add(new URLSourceProvider());
    }

    /* case load type URL or LOCAL */
    public String loadSource(String pathToSource) throws IOException {
        for(SourceProvider src: sourceProviders){
            if(src.isAllowed(pathToSource)){
                return src.load(pathToSource);
            }
        }
        throw new IOException();
    }
}
