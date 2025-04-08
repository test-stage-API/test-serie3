import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Tests {
    @Test
    public void testSolution() {
        // Redirect standard output
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(buffer, true);
        System.setOut(printStream);

        String[] args = {"120"};
        Main.main(args);
        // get output message
        String output = buffer.toString();
        String expected = "120 = 2 x 2 x 2 x 3 x 5";
        Assert.assertTrue("Expected solution for 120 should contain \"" + expected + "\"",
                output.contains(expected));
        buffer.reset();

        args = new String[]{"9537"};
        Main.main(args);
        // get output message
        output = buffer.toString();
        expected = "9537 = 3 x 11 x 17 x 17";
        Assert.assertTrue("Expected solution for 9537 should contain \"" + expected + "\"",
                output.contains(expected));
        buffer.reset();

        args = new String[]{"53233"};
        Main.main(args);
        // get output message
        output = buffer.toString();
        expected = "53233 = 53233";
        Assert.assertTrue("Expected solution for 53233 should contain \"" + expected + "\"",
                output.contains(expected));
    }
}