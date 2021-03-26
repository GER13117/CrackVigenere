public class main {
    public static void main(String[] args) {
        String rawMessage = "Faust wird von Mephisto zum Hexentanz der Walpurgisnacht auf den Blocksberg gelockt. Sie geraten in eine Windsbraut, ein Gewimmel von Hexen, die zur Bergspitze hinauf reiten, wo der Teufel Hof hält. Faust wünscht sich, bis zum Gipfel vorzudringen: Dort strömt die Menge zu dem Bösen; Da muss sich manches Rätsel lösen. Mephisto aber überredet Faust, stattdessen an einer Hexenfeier teilzunehmen. Er bietet ihm an, dort als Fausts Kuppler zu fungieren. Bald ergehen sich beide im Tanz und anzüglichem Wechselgesang mit zwei lüsternen Hexen. Faust bricht den Tanz ab, als seiner Partnerin ein rotes Mäuschen aus dem Mund springt und ihm ein blasses, schönes Kind erscheint, das ihn an Gretchen erinnert und ein rotes Schnürchen um den Hals trägt (eine Vorausdeutung auf Gretchens Hinrichtung). Um Faust von diesem Zauberbild abzulenken, führt Mephisto ihn auf einen Hügel, wo ein Theaterstück aufgeführt werden soll.";
        String cleanMessage = rawMessage.toLowerCase().replace("ü", "ue")
                .replace("ö", "oe")
                .replace("ä", "ae")
                .replace("ß", "ss")
                .replaceAll("[ ,.;:']","");
        System.out.println(cleanMessage);
        System.out.println(crackKey(cleanMessage, 4));


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

    public static String crackKey( String encMessage, int maxKeyLength) {

        long bestKey = 0;
        double bestOffeset = Double.MAX_VALUE;
        for (long currentKey = 1; currentKey < Math.pow(27, maxKeyLength); currentKey++){
            String keyString = numberToKey(currentKey);
            System.out.println(keyString);
            /*
            double offset = calculateFrequencyOffset(new Vigenere(encMessage, keyString).decode TODO: Methode um den Unterschied der verschiedenen Buchstaben in ihrer Häufigkeit zu der der deutschen Sprache schreiben
                                                                                                TODO: Entschlüsselmethode aus ViginereVerfahren übertragen


            if (offset > bestOffset){
                bestOffset = offset;
                best = currentKey
             */
        }

        /*
        return intToKey(best)
         */

        return "";
    }

}