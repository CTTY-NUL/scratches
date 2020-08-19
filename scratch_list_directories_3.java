import java.io.File;

class Scratch {
    public static void main(String[] args) {

        File[] Contents = list_directories("C:\\test2");

        for (int i = 0; i < Contents.length; i++) {
            System.out.println(Contents[i]);
        }

        System.out.println(Contents);

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
}