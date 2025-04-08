import org.junit.Assert;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Tests {

    // Helper method to run Main.main() with arguments and return output as a string
    private String runMainWithArgs(String[] args) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(buffer, true));

        Main.main(args); // Execute the main method

        System.setOut(originalOut);
        return buffer.toString().replace("\r\n", "\n"); // Normalize line endings
    }

    @Test
    public void testSawTeethBasicCase() {
        // Test input: 3 teeth, height 4
        String[] args = {"3", "4"};
        String actualOutput = runMainWithArgs(args);

        // Expected output pattern
        String expectedOutput =
                "*\n" +
                        "**\n" +
                        "***\n" +
                        "****\n" +
                        "*\n" +
                        "**\n" +
                        "***\n" +
                        "****\n" +
                        "*\n" +
                        "**\n" +
                        "***\n" +
                        "****\n";

        // Assert that the output matches the expected result
        Assert.assertEquals("Generated output does not match expected pattern.", expectedOutput, actualOutput);
    }

    @Test
    public void testSawTeethSingleTooth() {
        // Test input: 1 tooth, height 3
        String[] args = {"1", "3"};
        String actualOutput = runMainWithArgs(args);

        // Expected output pattern
        String expectedOutput =
                "*\n" +
                        "**\n" +
                        "***\n";

        Assert.assertEquals("Output for a single tooth is incorrect.", expectedOutput, actualOutput);
    }

    @Test
    public void testSawTeethZeroTeeth() {
        // Test input: 0 teeth, height 5 (should produce no output)
        String[] args = {"0", "5"};
        String actualOutput = runMainWithArgs(args);


        String expectedOutput = ""; // No output expected
        Assert.assertEquals("When teeth count is 0, no output should be produced.", expectedOutput, actualOutput);
    }

    @Test
    public void testSawTeethZeroHeight() {
        // Test input: 4 teeth, height 0 (should produce no output)
        String[] args = {"4", "0"};
        String actualOutput = runMainWithArgs(args);


        String expectedOutput = ""; // No output expected
        Assert.assertEquals("With height 0, no output should be produced.", expectedOutput, actualOutput);
    }
}
