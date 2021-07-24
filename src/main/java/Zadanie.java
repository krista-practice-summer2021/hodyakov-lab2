import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Zadanie {

    private File fileIn;
    private File fileOut;

    private List<Integer> inputList;
    private List<Integer> fltrList;
    private List<Integer> sqrList;

    public Zadanie() {
        inputList = new ArrayList<>();
        fltrList = new ArrayList<>();
        sqrList = new ArrayList<>();

    }

    public static void main(String[] args) {

        Zadanie filtrovator = new Zadanie();
        String pathIn = "src/main/resources/input.txt";
        String pathOut = "src/main/resources/output.txt";

        filtrovator.setFileIn(new File(pathIn).getAbsoluteFile());
        filtrovator.setFileOut(new File(pathOut).getAbsoluteFile());
        filtrovator.readFile();

        filtrovator.filter();
        filtrovator.sqrtList();
        filtrovator.sortList();
        filtrovator.output();

    }

    public void setFileIn(File fileIn) {
        this.fileIn = fileIn;
    }
    public void setFileOut(File fileOut) {
        this.fileOut = fileOut;
    }

    public void readFile() {
        try (Scanner in = new Scanner(fileIn)) {
            while (in.hasNextLine()) {
                int num = in.nextInt();
                this.inputList.add(num);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.fillInStackTrace());
        }
    }

    public void filter() {
        inputList.stream().forEach(element -> {
            if(element % 2 == 0) fltrList.add(element);
        });
    }

    public void sqrtList() {
        fltrList.stream().forEach(element -> {
            sqrList.add(element * element);
        });
    }

    public void sortList() {
        Collections.sort(sqrList);
    }

    public void output() {
        try (FileWriter writer = new FileWriter(fileOut)) {
            sqrList.stream().forEach(element -> {
                try {
                    writer.write(element + " ");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
