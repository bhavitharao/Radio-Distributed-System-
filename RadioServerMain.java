import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RadioServerMain {
    public static void main(String[] args) {
        try {
            // Create and bind the RadioServerImpl to the RMI registry
            RadioServerImpl serverImpl = new RadioServerImpl();
            Registry registry = LocateRegistry.createRegistry(1098);
            registry.rebind("RadioServer", serverImpl);

            System.out.println("Radio Server is running.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
