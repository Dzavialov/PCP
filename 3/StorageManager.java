import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class StorageManager {
    public Semaphore sem;
    public Semaphore storageFull;
    public Semaphore storageEmpty;

    public ArrayList<String> products = new ArrayList<String>();

    public StorageManager(int storageSize){
        sem = new Semaphore(1);
        storageFull = new Semaphore(storageSize);
        storageEmpty = new Semaphore(0);
    }
}
