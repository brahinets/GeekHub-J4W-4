package task_4;

/**
 * Class: IntegerParser
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 26.10.13
 * Time: 11:26
 * Mail: ysb.kanivtsi@gmail.com
 */
public class IntegerParser {

    /*
    * parseInt
    *   input:      String
    *   output:     Integer number
    *
    * if(charAtInput(0) == 0) or Input includes not digits symbols
    *   then throw NumberFormatException
    * else
    *   return output (parsed input to Integer)
    *
    *   codes of digits : 48-57
    * */
    Integer parseInt(String str) throws NumberFormatException{
        Integer var = new Integer(0);


        /* check is first symbol != 0 (code of 0 = 48)*/
        if(str.codePointAt(0) == 48){
            throw new NumberFormatException();
        }


        for(int i=0; i<str.length(); i++){
            /* check is there not digits symbols */
            if(str.codePointAt(i) < 48 || str.codePointAt(i) > 57){
                throw new NumberFormatException();
            }
            /* while just digits symbols parsing input*/
            var += (str.codePointAt(i)-48)*((int)Math.pow(10, str.length()-i-1));
        }

        return var;
    }
}