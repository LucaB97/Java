import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class RunScript {
    public static void main(String[] args) {
        // Path to the Python script
        String scriptPath = "script.py";

        // Run the Python script
        PythonInterpreter interpreter = new PythonInterpreter();
        interpreter.runScript(scriptPath);
    }
}

class PythonInterpreter {
    void runScript(String scriptPath) {
        try {
            // Build the command to execute the Python script
            ProcessBuilder pb = new ProcessBuilder("python3", scriptPath);
            
            // Start the process
            Process process = pb.start();

            // Read the output of the Python script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the process to finish
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}