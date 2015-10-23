import java.util.Random;
public class SentenceGenerator {
    static Random rand;
    static String[] nouns = {"motorcycle", "cat", "store"};
    static String[] verbs = {"rode", "ate", "kissed", "bought", "chewed"};
    static String[] adjectives = {"blue", "big", "mean", "stinky", "weird", "freaky", "nice"};
    static String[] adverbs = {"strongly", "barely", "readily", "angrily"};
    public static void main() {
        rand = new Random();
        String sen;
        for(int i = 0; i < 10; i++) {
            sen = "A ";
            sen += getNoun();
            sen += " ";
            sen += getVerb();
            sen += " a ";
            sen += getNoun();
            //sen += getPrepPhrase();
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
        return adjectives[rand.nextInt(adjectives.length)];
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
        return adverbs[rand.nextInt(adverbs.length)];
    }
//     public static String getPrepPhrase() {
//         
//     }
}