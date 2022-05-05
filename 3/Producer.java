public class Producer implements Runnable {
    private final int fromId;
    private final int toId;
    private final StorageManager storageManager;

    public Producer(int fromId, int toId, StorageManager storageManager){
        this.fromId = fromId;
        this.toId = toId;
        this.storageManager = storageManager;
        new Thread(this).start();
    }

    @Override
    public void run(){
        for(int i = fromId; i < toId; i++) {
            try {
                storageManager.storageFull.acquire();
                storageManager.sem.acquire();

                storageManager.products.add("item " + i);
                System.out.println("Producer " + Thread.currentThread().getId() + " added item " + i);

                storageManager.sem.release();
                storageManager.storageEmpty.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
