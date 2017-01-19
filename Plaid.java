import java.io.*;
import java.util.*;

public class Plaid {

    public static void main(String[] args) {
        // Read in file
        String fileName = args[0];
        File file = new File(fileName);
        StringBuilder sb = new StringBuilder();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                sb.append(line);
                sb.append("\n");
            }
            br.close();
        }
        catch (FileNotFoundException fne) {
            fne.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        // write encoded string to file
        try {
            String encoded = encode(sb.toString());
            File encodedFile = new File("encodedData.txt");
            if (!encodedFile.exists()) {
                encodedFile.createNewFile();
            }
            BufferedWriter bw = new BufferedWriter(new FileWriter(encodedFile));
            bw.write(encoded);
            bw.close();
            String decoded = decode(encoded);
            File decodedFile = new File("decodedData.txt");
            if (!decodedFile.exists()) {
                decodedFile.createNewFile();
            }
            BufferedWriter bw2 = new BufferedWriter(new FileWriter(decodedFile));
            bw2.write(decoded);
            bw2.close();

        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    //--Encodes input file string and returns encoded string--
    public static String encode(String file) {
        if (file == null) {
            throw new IllegalArgumentException("file is null");
        }
        StringBuilder sb = new StringBuilder();
        String[] lines = file.split("\\n");
        for (String line : lines) {
            int count = 1;
            for (int i = 0; i < line.length(); i++) {
                // Consecutive
                if (i < line.length() - 1 && line.charAt(i) == line.charAt(i+1)) {
                    count++;
                }
                else {
                    sb.append((int)line.charAt(i));
                    sb.append("[" + count + "]");
                    count = 1;
                }
            }
            sb.append("\n");
        }
        // remove the last new line and return string
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    //--Takes in encoded file string and returns decoded file string--
    public static String decode(String file) {
        if (file == null) {
            throw new IllegalArgumentException("file is null");
        }
        StringBuilder sb = new StringBuilder();
        String[] lines = file.split("\\n");
        for (String line : lines) {
            String[] temp = line.split("\\]");
            for (String t : temp) {
                String[] string = t.split("\\[");
                String s = string[0];
                int count = Integer.parseInt(string[1]);
                for (int i = 0; i < count; i++) {
                    sb.append((char)Integer.parseInt(s));
                }
            }
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
