import java.util.Random;
public class SenGen {
    static Random rand;
    static String[] nouns = {"motorcycle", "cat", "store", "idiot", "dog", "animal", "zit", "mouse", "pencil", "house"};
    static String[] verbs = {"rode", "ate", "kissed", "bought", "chewed", "killed", "poked", "scratched"};
    static String[] adjectives = {"blue", "big", "mean", "stinky", "weird", "freaky", "nice", "special ed", "starnge", "perfect"};
    static String[] adverbs = {"strongly", "barely", "readily", "angrily", "shrewedly", "quickly", "tenderly", "slowly", "rapidly"};
    static String[] prepositions = {"amid", "among", "in", "inside", "out of", "through", "with", "without"};
    public static void main(String[] args) {
        rand = new Random();
        String sen;
        for(int i = 0; i < 10; i++) {
            if (rand.nextInt(4) == 0)
            	sen = getPrepPhrase() + ", a ";
            else
            	sen = "a ";
            sen += getNoun();
            sen += " ";
            sen += getVerb();
            sen += " a ";
            sen += getNoun();
            sen += " ";
            sen += getPrepPhrase();
            sen += ".";
            sen = sen.substring(0, 1).toUpperCase() + sen.substring(1);
            System.out.println(sen);
        }
    }
    public static String getNoun() {
        String noun;
        if (rand.nextInt(3) == 0)
            noun = getAdjective() + " " + getNoun();
        else
            noun = nouns[rand.nextInt(nouns.length)];
        return noun;
    }
    public static String getAdjective() {
    	String adjective = adjectives[rand.nextInt(adjectives.length)];
    	if (rand.nextInt(4) == 0)
    		adjective = getAdverb() + " " + adjective;
        return adjective;
    }
    public static String getVerb() {
        String verb;
        if (rand.nextInt(4) == 0)
            verb = getAdverb() + " " + getVerb();
        else
            verb = verbs[rand.nextInt(verbs.length)];
        return verb;
    }
    public static String getAdverb() {
    	String adverb = adverbs[rand.nextInt(adverbs.length)];
    	if (rand.nextInt(4) == 0)
    		adverb = getAdverb() + " " + adverb;
    	return adverb;
    }
     public static String getPrepPhrase() {
         String prepositionalPhrase = prepositions[rand.nextInt(prepositions.length)] + " a " + getNoun();
         if (rand.nextInt(4) == 0)
        	 prepositionalPhrase += " " + getPrepPhrase();
         return prepositionalPhrase;
     }
}
