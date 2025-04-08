import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.StringTokenizer;

public class Tests {

    private static final String[] answer = {
            "60,90,120,150,180,210,240,270,300,330",
            "360,390,420,450,480,510,540,570,600,630",
            "660,690,720,750,780,810,840,870,900,930",
            "960,990",
    };

    @Test
    public void testSolution() {
        // Redirect standard output
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(buffer, true);
        System.setOut(printStream);

        String[] args = {"50", "1000"};
        Main.main(args);
        // get output message
        String output = buffer.toString();
        StringTokenizer tokenizer = new StringTokenizer(output, "\n");

        for (int i = 0; i < answer.length; i++) {
            Assert.assertEquals("Output has enough line ?", true, tokenizer.hasMoreTokens());
            String token = tokenizer.nextToken();
            Assert.assertTrue("Line " + (i+1) + " should contain \"" + answer[i] + "\"",
                    token.contains(answer[i]));
            Assert.assertTrue("Line " + i+1 + " should not start with ,",
                    token.charAt(0) != ',');
            Assert.assertTrue("Line " + i+1 + " should not end with ,",
                    token.charAt(token.length()-1) != ',');
        }

        Assert.assertEquals("Output has too much lines ?", false, tokenizer.hasMoreTokens());
        buffer.reset();
    }
}
