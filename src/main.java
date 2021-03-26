public class main {
    public static void main(String[] args) {
        String rawMessage = "Faust wird von Mephisto zum Hexentanz der Walpurgisnacht auf den Blocksberg gelockt. Sie geraten in eine Windsbraut, ein Gewimmel von Hexen, die zur Bergspitze hinauf reiten, wo der Teufel Hof hält. Faust wünscht sich, bis zum Gipfel vorzudringen: Dort strömt die Menge zu dem Bösen; Da muss sich manches Rätsel lösen. Mephisto aber überredet Faust, stattdessen an einer Hexenfeier teilzunehmen. Er bietet ihm an, dort als Fausts Kuppler zu fungieren. Bald ergehen sich beide im Tanz und anzüglichem Wechselgesang mit zwei lüsternen Hexen. Faust bricht den Tanz ab, als seiner Partnerin ein rotes Mäuschen aus dem Mund springt und ihm ein blasses, schönes Kind erscheint, das ihn an Gretchen erinnert und ein rotes Schnürchen um den Hals trägt (eine Vorausdeutung auf Gretchens Hinrichtung). Um Faust von diesem Zauberbild abzulenken, führt Mephisto ihn auf einen Hügel, wo ein Theaterstück aufgeführt werden soll.".toUpperCase();
        String cleanMessage = rawMessage.toUpperCase()
                .replace("Ü", "UE")
                .replace("Ö", "OE")
                .replace("Ä", "AE")
                .replace("ß", "SS") //Großes ß ?
                .replaceAll("[ ,.;:'()]","");
        System.out.println(cleanMessage);
        String key = "test".toUpperCase();
        String encString = encodeString(cleanMessage, key, true);
        crackKey(encString, 4);


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
    public static String numberToKey(long currentKey){
        return currentKey < 0 ? "" : numberToKey((currentKey / 26) - 1) + (char)(65 + currentKey % 26); //TODO: verstehen wäre gut xD
    }

    public static String crackKey( String encString, int maxKeyLength) {

        long bestKey = 0;
        double bestOffset = Double.MAX_VALUE;
        for (long currentKey = 1; currentKey < Math.pow(27, maxKeyLength); currentKey++){
            String keyString = numberToKey(currentKey);
            System.out.println(keyString);
            double offset = calcFreqDif(encodeString(encString, numberToKey(currentKey), false)); //gibt der Klasse calcFreqDif einen möglicherweise entschlüsselten String --> encodeString kriegt den verschlüsselten String und versucht ihn mit dem aktuellen Key zu entsclüsseln

            if (offset < bestOffset) {
                bestOffset = offset;
                bestKey = currentKey;
            }
        }


        return numberToKey(bestKey);

    }
    public static String encodeString(String s, String keyString, Boolean menu) {
        String encString = "";
        for (int i = 0; i < s.length(); i++) {
            int key = ((int) keyString.charAt(i % keyString.length())) - 65;
            if (menu) {
                encString += encode(s.charAt(i), key);
            } else {
                encString += encode(s.charAt(i), -key);
            }

        }
        return encString;
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

    public static double calcFreqDif(String posDecString){ //TODO: Methode um den Unterschied der verschiedenen Buchstaben in ihrer Häufigkeit zu der der deutschen Sprache schreiben
        double offset = 0;





        return offset;
    }

}