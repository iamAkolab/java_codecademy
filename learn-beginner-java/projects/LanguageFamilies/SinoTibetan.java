public class SinoTibetan extends Language{

    SinoTibetan(String name, int numSpeakers) {
        super(name, numSpeakers, "Asia", "Subject | Object | Verb");
        if (name.contains("Chinese")) {
            this.wordOrder = "Subject | Vers | Object";
             // this.wordOrder = "subject-verb-object";
        }
    }
}
