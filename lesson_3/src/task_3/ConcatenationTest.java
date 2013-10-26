package task_3;

/**
 * Class: ConcatanationTest
 *
 * Version: 1.0
 *
 * User: Paradoxxx
 * Date: 26.10.13
 * Time: 12:13
 * Mail: ysb.kanivtsi@gmail.com
 */

/*
*   comparing time of Concatenation of three types of string data (String, StringBuffer, StringBuilder)
* */
    public class ConcatenationTest {


    public static void main(String args[]){
        String str;
        StringBuffer stringBuffer;
        StringBuilder stringBuilder;

        double timeStrStart, timeStrEnd, timeStr ;
        double timeBufferStart, timeBufferEnd, timeBuffer;
        double timeBuilderStart, timeBuilderEnd, timeBuilder;

        timeStrStart = timeStrEnd = timeStr = 0.0d;
        timeBufferStart = timeBufferEnd = timeBuffer = 0.0d;
        timeBuilderStart = timeBuilderEnd = timeBuilder = 0.0d;

        int tests = 10;
        long symbols = 10000000;
        for(int i=0; i<tests; i++ ){
            str = new String("");
            stringBuffer = new StringBuffer("");
            stringBuilder = new StringBuilder("");

            /* String concatenation test */
            timeStrStart = System.currentTimeMillis();
            for(long j=0; j<symbols; j++)
                str.concat("1");
            timeStrEnd = System.currentTimeMillis();
            timeStr += (timeStrEnd - timeStrStart);

            /* stringBuilder concatenation test */
            timeBuilderStart = System.currentTimeMillis();
            for(long j=0; j<symbols; j++)
                stringBuilder.append("1");
            timeBuilderEnd = System.currentTimeMillis();
            timeBuilder += (timeBuilderEnd - timeBuilderStart);

            /* stringBuffer concatenation test */
            timeBufferStart = System.currentTimeMillis();
            for(long j=0; j<symbols; j++)
                stringBuffer.append("1");
            timeBufferEnd = System.currentTimeMillis();
            timeBuffer += (timeBufferEnd - timeBufferStart);

            System.out.print(". ");
        }
        timeStr /= tests;
        timeBuilder /= tests;
        timeBuffer /= tests;

        System.out.print("\nTime of String concatenation: "+ timeStr);
        System.out.print("\nTime of StringBuilder concatenation: "+ timeBuilder);
        System.out.print("\nTime of StringBuffer concatenation: "+ timeBuffer);

        /*
            current test shows next
                Time of String concatenation: 542.4
                Time of StringBuilder concatenation: 517.0
                Time of StringBuffer concatenation: 649.3
        */

    }
}
