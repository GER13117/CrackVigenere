import java.util.Arrays;
import java.util.Scanner;

public class CrackVigenere {
    public static void main(String[] args) {
        System.out.println("Hallo zum Entschlüsselungsprogramm \n Bitte wähle aus ob du entschlüsseln oder verschlüsseln möchtest");
        System.out.println("[D]ecrypt or [E]ncrypt");
        Scanner scanner = new Scanner(System.in);
        menu(scanner);
    }

    public static void menu(Scanner scanner) {
        switch (scanner.nextLine()) {
            case "D", "d" -> decrypt(scanner);
            case "E", "e" -> encrypt(scanner);
            default -> {
                System.out.println("You didn't provide the correct action");
                menu(scanner);
            }
        }
        System.out.println("Please Enter your next action: [D]ecrypt or [E]ncrypt");
        menu(scanner);
    }

    public static void encrypt(Scanner scanner) {
        System.out.println("Please Enter the text you want to encrypt");
        String rawMessage = scanner.nextLine();
        System.out.println("Please enter your key");
        String key = scanner.nextLine().toUpperCase();
        String cleanMessage = rawMessage.toUpperCase()
                .replace("Ü", "UE")
                .replace("Ö", "OE")
                .replace("Ä", "AE")
                .replace("ß", "SS")
                .replaceAll("[^A-Z]", "");
        String encMessage = encodeString(cleanMessage, key, true);
        System.out.println(encMessage);
    }


    public static void decrypt(Scanner scanner) {
        System.out.println("Please enter your decrypted Text");
        String rawMessage = scanner.nextLine();
        String encString = rawMessage.toUpperCase()
                .replace("Ü", "UE")
                .replace("Ö", "OE")
                .replace("Ä", "AE")
                .replace("ß", "SS")
                .replaceAll("[^A-Z]", "");
        System.out.println("Do you know the key? y/n");
        String yesNo = scanner.nextLine();
        switch (yesNo) {
            case "Y", "y" -> {
                System.out.println("Please Provide it!");
                String key = scanner.nextLine();
                encodeString(encString, key, true);
            }
            case "N", "n" -> {
                System.out.println("Please enter the maximum key length you want to look for \n A longer key increases the time to decrypt heavily!");
                System.out.println(crackKey(encString, scanner.nextInt()));
            }
            default -> {
                System.out.println("You didn't provide the correct action");
                decrypt(scanner);
            }
        }

    }

    public static double[] LETTER_FREQUENCIES = new double[]{
            6.516,
            1.886,
            2.732,
            5.076,
            16.396,
            1.656,
            3.009,
            4.577,
            6.550,
            0.268,
            1.417,
            3.437,
            2.534,
            9.776,
            2.594,
            0.670,
            0.018,
            7.003,
            7.270,
            6.154,
            4.166,
            0.846,
            1.921,
            0.034,
            0.039,
            1.134
    };

    public static String numberToKey(long currentKey) {
        if (currentKey < 0) {
            return "";
        } else {
            return numberToKey((currentKey / 26) - 1) + (char) (65 + currentKey % 26); //TODO: verstehen wäre gut xD
        }
        //return currentKey < 0 ? "" : numberToKey((currentKey / 26) - 1) + (char)(65 + currentKey % 26);
    }

    public static String crackKey(String encString, int maxKeyLength) {

        long bestKey = 0;
        double bestOffset = Double.MAX_VALUE;
        for (long currentKey = 1; currentKey < Math.pow(27, maxKeyLength); currentKey++) {
            double offset = calcFreqDif(encodeString(encString, numberToKey(currentKey), false)); //gibt der Klasse calcFreqDif einen möglicherweise entschlüsselten String --> encodeString kriegt den verschlüsselten String und versucht ihn mit dem aktuellen Key zu entschlüsseln

            if (offset < bestOffset) {
                bestOffset = offset;
                bestKey = currentKey;
            }
        }


        return numberToKey(bestKey);
    }

    public static String encodeString(String s, String keyString, Boolean menu) {
        StringBuilder encString = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int key = ((int) keyString.charAt(i % keyString.length())) - 65;
            if (menu) {
                encString.append(encode(s.charAt(i), key));
            } else {
                encString.append(encode(s.charAt(i), -key));
            }

        }
        return encString.toString();
    }

    public static char encode(char c, int key) {

        if ((int) c == 32) {
            return (char) 32; //Checks if a char is a space.

        } else if (((int) c + key) > 90) {
            return (char) (((int) c + key) - 26);

        } else if (((int) c + key) < 65) {
            return (char) ((int) c + 26 + key);

        } else {
            return (char) ((int) c + key);

        }
    }

    public static double calcFreqDif(String posDecString) {
        char[] pSCArray = posDecString.toCharArray();
        Arrays.sort(pSCArray);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        double actualFreq = 0;
        String msgSorted = (Arrays.toString(pSCArray));

        for (int i = 0; i < alphabet.length(); i++) {
            String c = Character.toString(alphabet.charAt(i));
            int charAmount = msgSorted.length() - msgSorted.replace(c, "").length();
            double offset = (LETTER_FREQUENCIES[i] - charAmount / (double) posDecString.length());
            offset *= offset;
            actualFreq += offset;
        }

        return actualFreq / 26D;
    }

}