package Session;

public class ConducteurConnecte {
    private static int conducteurId;

    public static void setConducteurId(int id) {
        conducteurId = id;
    }

    public static int getConducteurId() {
        return conducteurId;
    }
}
