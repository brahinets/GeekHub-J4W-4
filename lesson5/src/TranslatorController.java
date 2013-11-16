/**
 * Class: TranslatorController
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 16.11.13
 * Time: 11:43
 * Mail: ysb.kanivtsi@gmail.com
 */

import source.SourceLoader;
import source.URLSourceProvider;
import java.io.IOException;
import java.util.Scanner;

public class TranslatorController {

    public static void main(String[] args) throws IOException {
        SourceLoader sourceLoader = new SourceLoader();
        Translator translator = new Translator(new URLSourceProvider());
        Scanner scanner = new Scanner(System.in);
        String command = "";
        String source;

        while(!command.equals("exit")) {
            System.out.printf("Input path ( 'exit' to exit): ");
            command = scanner.next();
            try{
                source = sourceLoader.loadSource(command);
            } catch (IOException ex){
                System.out.println("Try another path\n");
                continue;
            }
            String translation = translator.translate(source);
            System.out.println("Original: " + source);
            System.out.println("Translation: " + translation);
        }
    }
}