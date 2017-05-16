
import java.io.*;
import java.util.ArrayList;

public class ReadFile {
    private static ArrayList<String> inputFromFile(String arg) throws IOException {
        ArrayList<String> arr = new ArrayList<>();
        File f = new File(arg);
        if (!f.exists()){
            throw new IOException("file not found");
        }
        if (f.length()==0){
            throw new IOException("file is empty");
        }
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
        } catch (FileNotFoundException e) {
            throw new IOException("file Exception");
        }
        String line;
        while ((line = br.readLine()) != null) {
            arr.add(line);
        }
        return arr;
    }

    private static void outputInFile(String outputFile, Comparable[] array) throws IOException{
        PrintWriter printWriter= new PrintWriter(new FileWriter(outputFile));
        for (int i = 0; i <array.length ; i++) {
            printWriter.println(array[i]);
        }
        printWriter.close();
    }
    private static void insertSort(String directSort, Comparable[] array){

        for (int i = 0; i <array.length ; i++) {
            Comparable tmp= array[i];
            int j=i-1;
            while (j >= 0 && (("-a".equals(directSort) && (array[j]).compareTo(tmp) > 0) || ("-d".equals(directSort) && (array[j]).compareTo(tmp) < 0))) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = tmp;
        }
    }

    public static void main(String[] args) {
        try {
            if (args.length < 4) {
                throw new IllegalArgumentException("Должно быть указано 4 параметра");
            }
            ArrayList<String> arr = inputFromFile(args[0]);

            if (!("-a".equals(args[3]) || "-d".equals(args[3]))) {
                throw new Exception("Error in 4 parameter");
            }
            Comparable[] array =null;
            if (args[2].equals("-i")) {
                array = new Integer[arr.size()];
                try {
                    for (int i = 0; i < arr.size(); i++) {
                        array[i] = Integer.parseInt(String.valueOf(arr.get(i)));
                    }
                } catch (NumberFormatException e) {
                    throw new NumberFormatException("Not a number in file");
                }
            } else if (args[2].equals("-s")) {
                 array = new String[arr.size()];
                for (int i = 0; i < arr.size(); i++) {
                    array[i] = String.valueOf(arr.get(i));
                }
            } else {
                throw new Exception("Invalid 3 parameter");
            }

            insertSort(args[3], array);

            outputInFile(args[1], array);

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}