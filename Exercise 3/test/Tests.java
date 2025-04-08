import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tests {

    // Helper method to execute Main.main() and capture its output
    private String runMainAndCaptureOutput() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(buffer, true));

        Main.main(new String[0]); // Execute main method

        System.setOut(originalOut);
        return buffer.toString().replace("\r\n", "\n"); // Normalize line endings
    }

    @Test
    public void testSolution() {
        // Capture the output of the program
        String output = runMainAndCaptureOutput();


        // Ensure output contains the expected labels
        Assert.assertTrue("Output should contain 'Approximation of e:'", output.contains("Approximation of e:"));
        Assert.assertTrue("Output should contain 'Number of iterations:'", output.contains("Number of iterations:"));

        // Regular expression to extract the calculated value of e from the output
        Pattern ePattern = Pattern.compile("Approximation of e: ([0-9.]+)");
        Matcher eMatcher = ePattern.matcher(output);
        double calculatedE = 0.0;
        if (eMatcher.find()) {
            calculatedE = Double.parseDouble(eMatcher.group(1));
        }

        // Regular expression to extract the number of iterations
        Pattern iterPattern = Pattern.compile("Number of iterations: ([0-9]+)");
        Matcher iterMatcher = iterPattern.matcher(output);
        int iterations = 0;
        if (iterMatcher.find()) {
            iterations = Integer.parseInt(iterMatcher.group(1));
        }

        // Define an acceptable tolerance range for the approximation of e
        double tolerance = 1e-9;
        Assert.assertTrue("The calculated value of e is not within the acceptable tolerance.",
                Math.abs(calculatedE - Math.E) < tolerance);

        // Verify the expected number of iterations (should be around 15)
        Assert.assertTrue("The number of iterations should be reasonable (around 15).", iterations > 10 && iterations < 20);
    }
}
