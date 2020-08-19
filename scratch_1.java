//import Scratch.GetFileInfo;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

class Scratch {
    public static void main(String[] args) {
        //TODO list_directories_recursively
        //TODO file_to_string & string_to_file op verschillende manieren
        //TODO CSV inlezen en opslaan mbv apache commons
        //TODO Eerstvolgende niet-bezette filename
        //TODO http GET
        //TODO http POST
        //TODO object to JSON
        //TODO serialization, zit in cttynul tic tac toe
        //TODO iets soortgelijks aan de AutoIt Between versie, en ook een recursive variant daarvan.
        //TODO logit functie

    }

    public static void get_file_info(String path) {
        //Required: import java.io.File;
        //get_file_info("C:\\Temp\\test.txt");
        File myObj = new File(path);
        if (myObj.exists()) {
            System.out.println("File name: " + myObj.getName());
            System.out.println("Absolute path: " + myObj.getAbsolutePath());
            System.out.println("Writeable: " + myObj.canWrite());
            System.out.println("Readable " + myObj.canRead());
            System.out.println("File size in bytes " + myObj.length());
        } else {
            System.out.println("The file does not exist.");
        }
    }


    public static int generate_random_int_within_range(int min, int max) {
        //System.out.println(generate_random_int_within_range(1,5));
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


    public static void read_properties(Properties properties) {
        //http://tutorials.jenkov.com/java-collections/properties.html
        //https://docs.oracle.com/javase/tutorial/essential/environment/properties.html


//        System.out.println("nu lezen!");
//        read_properties(properties);
//        String email = properties.getProperty("email");
//        System.out.println(email);
//

        //Properties properties = new Properties();
        try (FileReader fileReader = new FileReader("data/props.properties")) {
            properties.load(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //String email2 = properties.getProperty("email");
        //System.out.println("asdasd " + email2);
    }

    public static void write_properties(Properties properties) {

//        Properties properties = new Properties();
//        properties.setProperty("email", "john@doe.com");
//        write_properties(properties);


        //Create folder if it doesnt exist
        try {
            String directory = "data";
            File dir = new File(directory);
            // If you require it to make the entire directory path including parents, use directory.mkdirs(); here instead.
            if (!dir.exists()) dir.mkdir();
            // eigenlijk hoeft dit niet, je kan em gewoon een directory laten aanmaken die al bestaat en dan gebeurt er niks
        } catch (SecurityException f) { // geen IOException maar een SecurityException https://stackoverflow.com/a/37723632
            f.printStackTrace();
        }

        //Create file if it doesnt exist
        try {
            File myFile = new File("data/props.properties");

            if (myFile.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        //Properties properties = new Properties();
        try (FileWriter output = new FileWriter("data/props.properties")) {
            properties.store(output, "These are properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void check_if_file_can_be_read(String filename) {

        File hFile = new File(filename);
        if (!hFile.exists()) {
            System.out.println("Bestand " + filename + " bestaat niet!");
            System.exit(-1);
            //return false;
        }
        if (!hFile.canRead()) {
            System.out.println("Bestand " + filename + " kan niet gelezen worden!");
            System.exit(-1);
            //return false;
        }

    }


    public static void create_file_if_it_doesnt_exist(String filename) {

        File hFile = new File(filename);
        if (!hFile.exists()) {
            try {
                hFile.createNewFile();
            } catch (IOException e) {
                System.out.println("Fout tijdens aanmaken bestand! Mogelijk geen schrijfrechten.");
                e.printStackTrace();
                System.exit(-1);
            }
        } else if (!hFile.canWrite()) {
            System.out.println("Bestand bestaat maar ik kan niet schrijven! Mogelijk readonly.");
            System.exit(-1); //return false;
        }

    }


    public static void write_array_to_file(int[] inputarray, String filename) {

//        String fileName = "test.txt";
//        int[] myIntArray = new int[]{1, 2, 3};
//        System.out.println("eerst schrijven:");
//        write_array_to_file(myIntArray, fileName);
//        System.out.println("nu lezen:");
//        String[] arr = read_file_to_array(fileName);
//        System.out.println("tijd om te checken of ik een array met alles erin heb:");
//        for (int i = 0; i < arr.length; i++) {
//            System.out.println("" + arr[i]);
//        }

        create_file_if_it_doesnt_exist(filename);
        try {
            FileWriter writer = new FileWriter(filename, true);
            for (int i = 0; i < inputarray.length; i++) {
                writer.write(inputarray[i] + "\r\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static String[] read_file_to_array(String filename) {

        check_if_file_can_be_read(filename);

        int hoeveel_regels = 0;
        String alles = "";
        try {
            FileReader reader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                hoeveel_regels++;
                alles = alles + line + "\r\n";
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //ik kan hier geen array redimmen met behoud v.d. inhoud. Als ik een ArrayList zou hebben zou ik .add kunnen doen
        String[] arrOfStr = alles.split("\r\n");
        System.out.println("dat waren " + hoeveel_regels + " regels!");
        return arrOfStr;

    }


    public static void write_arraylist_to_file(String filename, ArrayList<String> playerNames) {

//        System.out.println("nu arraylists vullen");
//        ArrayList<String> playerNames = new ArrayList<>();
//        for (int playerIndex = 1; playerIndex <= 10; playerIndex++) {
//            playerNames.add("Player" + playerIndex);
//        }
//        //waarom werkt System.out.println(Arrays.toString(playerNames)); niet en deeptostring wel? Googlen!
//        System.out.println(Arrays.deepToString(playerNames.toArray()));
//        System.out.println("nu schrijven naar arraylist.txt");
//        write_arraylist_to_file("arraylist.txt",playerNames);
//        System.out.println("nu lezen van arraylist.txt");
//        ArrayList<String> nieuwearraylist = read_file_to_arraylist("arraylist.txt");
//        System.out.println("print uitgelezen array list");
//        System.out.println(Arrays.deepToString(nieuwearraylist.toArray()));

        create_file_if_it_doesnt_exist(filename);
        try {
            FileWriter writer = new FileWriter(filename, true);
            for (int i = 0; i < playerNames.size(); i++) {
                writer.write(playerNames.get(i) + "\r\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static ArrayList<String> read_file_to_arraylist(String filename) {

        check_if_file_can_be_read(filename);

        ArrayList<String> ingelezen_array = new ArrayList<String>();
        try {
            FileReader reader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                ingelezen_array.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ingelezen_array;
    }


    public static File[] list_directories(String path) {
        //required: import java.io.File;

//        File[] Contents = list_directories("C:\\");
//
//        for (int i = 0; i < Contents.length; i++) {
//            System.out.println(Contents[i]);
//        }
//
//        System.out.println(Contents);

        return new File(path).listFiles(File::isDirectory);
    }


    public static File[] list_files_in_directory(final File folder) {
        //required: import java.io.File;

//    File[] FilesInFolder = list_files_in_directory(new File("C:\\"));
//
//    for (int i = 0; i < FilesInFolder.length; i++) {
//        System.out.println(FilesInFolder[i]);
//    }


        return folder.listFiles(File::isFile);
    }






    public static void show_utf16(String line) throws UnsupportedEncodingException {
//        Hij begint met -2 en -1 (of fffffffe ffffffff) omdat dat de Byte Order Marks zijn
//        Required: import java.io.UnsupportedEncodingException;
//        Usage:
//        try {
//            show_utf16(line);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

        byte [] array1 = line.getBytes("UTF-16");
        for(byte b1: array1){
            System.out.print(b1 + " ");
//            String hex = Integer.toHexString(b1);
//            System.out.print(hex + " ");
        }
        System.out.println();
    }

    public static void show_utf8(String line) throws UnsupportedEncodingException {
//        Required: import java.io.UnsupportedEncodingException;
//        Usage:
//        try {
//            show_utf8(line);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        byte [] array2 = line.getBytes("UTF-8");
        for(byte b2: array2){
            System.out.print(b2 + " ");
        }
        System.out.println();


    }

    public static void hex_string(String line){
        for (int i = 0; i < line.length(); i++){
            char c = line.charAt(i);
            String a = Integer.toHexString(c);
            System.out.print(a + " ");
        }
        System.out.println();
    }



    public static void put_string_on_clipboard(String temp) {
//        import java.awt.Toolkit;
//        import java.awt.datatransfer.Clipboard;
//        import java.awt.datatransfer.StringSelection;
//        put_string_on_clipboard("dingen eddnzo");
        StringSelection selection = new StringSelection(temp);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }



    public static String findnextavailablefilename(String path, String filename, String fileextension) {
//        String nieuwebestandsnaam;
//        nieuwebestandsnaam = findnextavailablefilename("c:\\tmp\\","pizza","jpg");
//        System.out.println(nieuwebestandsnaam);
//


        //is het dan beter om gewoon Stings in de method te stoppen? of moeten de parameters al direct File zijn
        //is het beter om Path path te gebruiken? of File path?
        //static void findnextavailablefilename(Path pad) {

        File file = new File(path, filename + "." + fileextension);

        int num;
        for (num = 1; file.exists(); num++) {
            //System.out.println("Probeer " + path + filename + num + "." + fileextension);
            file = new File(path, filename + num + "." + fileextension);
        }
        num--;
        return "" + path + filename + num + "." + fileextension;

    }


    public static String[] subdirectoriesinpath2array(String path) {
        //deze ook geschikt maken voor files, en files+folders
//        String[] aFolders = subdirectoriesinpath2array(sPath1);
        File file = new File(path);
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
                return new File(current, name).isDirectory();
            }
        });
//        System.out.println(Arrays.toString(directories));
        return directories;
    }




    public static int count_substring_in_string(String text, String find) {
        int index = 0, count = 0, length = find.length();
        while( (index = text.indexOf(find, index)) != -1 ) {
            index += length; count++;
        }
        return count;
    }


    public static ArrayList<String> between_recursive(String content, String start, String end){
        //import java.util.ArrayList;
//        String test = "dit begin is een eind begin onasdas being eind begin begin begin sdddddeindddddddd";
//        ArrayList<String> antwoord = between_recursive(test,"begin","eind");
//        for(String item:antwoord) {
//            System.out.println(item);
//        }


        ArrayList<String> response = new ArrayList<String>(0);

        //kijk of content, start en end niet leeg zijn
        if (content.length() == 0) {
            System.out.println("Content is leeg."); //kan ook er van maken: if (content.length() < 2)
            return response;
        } else if (start.length() == 0) {
            System.out.println("Start is leeg");
            return response;
        } else if (end.length() == 0) {
            System.out.println("End is leeg");
            return response;
        }

        //check of content groter is dan start en end samen, zo niet dan is er iets mis!
        if (!(content.length() > start.length() + end.length())) {
            System.out.println("De lengte van content is niet groter dan de lengte van start en de lengte van end samen.");
            return response;
        }

        //kijken of content start en end allebei bevat
        if (!content.contains(start)) {
            System.out.printf("content bevat de start niet");
            return response;
        } else if (!content.contains(end)) {
            System.out.printf("content bevat het eind niet");
            return response;
        }

        //Stringinstr = The indexOf() method returns the index (the position) of the first occurrence of a specified text in a string
        //TrimString = substring   String s="hello";   System.out.println(s.substring(0,2));//he
        String nogtedoorzoekentekst = content;

        while (nogtedoorzoekentekst.contains(start) && nogtedoorzoekentekst.contains(end)) {
            nogtedoorzoekentekst = nogtedoorzoekentekst.substring(nogtedoorzoekentekst.indexOf(start) + start.length());
            response.add(nogtedoorzoekentekst.substring(0,nogtedoorzoekentekst.indexOf(end)));
            nogtedoorzoekentekst = nogtedoorzoekentekst.substring(nogtedoorzoekentekst.indexOf(end) + end.length());
        }

        return response;

    }






}
