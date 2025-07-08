public class Mayan extends Language {

    Mayan(String name, int numSpeakers) {
        super(name, numSpeakers, "Central America", "Verb | Object | Subject");
        // // super(name, numSpeakers, "Central America", "verb-object-subject");
    }

    @Override
    public void getInfo() {
        super.getInfo();
        System.out.println("Fun fact: " + this.name + " is an ergative language.");
    }
}
