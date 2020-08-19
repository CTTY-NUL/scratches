class Scratch {
    public static void main(String[] args) {


        for (int i = 0; i < 9; i++) {

            for (int j = 0; j < ; j++) {

                for (int k = 0; k < 6; k++) {
                    for (int l = 0; l < 6; l++) {
                        for (int m = 0; m < 6; m++) {
                            for (int n = 0; n < 6; n++) {
                                for (int o = 0; o < 6; o++) {
                                    for (int p = 0; p < 6; p++) {
                                        for (int q = 0; q < 6; q++) {
                                            for (int r = 0; r < 6; r++) {

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

            }

        }



        //https://noviuniversity.sharepoint.com/sites/FullStackDevelopers52020/Lesmateriaal/002%20-%20Inleiding%20Programmeren/Java%20CheatSheet.png

        String besturingsysteem = System.getProperty("os.name");
        System.out.println();
        System.out.println(besturingsysteem);
        System.getProperties().list(System.out);


        String numberText = "100"; /* verander string naar integer */
        int potato = Integer.parseInt(numberText);


//        Integer naar string op 3 manieren: (moet nog alle 3 uitschrijven)
        String mappedText = Integer.toString(potato); //string.valueOf   string = "" + a (a is dan een integer)
        numberText = mappedText;

        import java.util.Scanner;

        int age = 0;
        age = age + 5;
        System.out.println("Student age is : " + age);

        char[] board = new char[10];
        for (int i=1; i<board.length;i++) {
            board[i] = (char) (i + '0'); //dus eigenlijk plus 48, de ascii waarde van 0
            System.out.print(board[i]);
            if (i % 3 == 0) { // if divisible by 3
                System.out.println();
            }
        }





        String scoreString = "Tom = 28";
        String[] playerInfo = scoreString.split(" = ");  /* vul hier de juiste waarde in*/
        System.out.println("name = " + playerInfo[0]);
        System.out.println("score = " + playerInfo[1]);



    }
}