package Session;

public class ClientConnecte {
    private static int clientId;

    public static void setClientId(int id) {
        clientId = id;
    }

    public static int getClientId() {
        return clientId;
    }
}
