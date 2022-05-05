public class Consumer implements Runnable{
    private final int counter;
    private final StorageManager storageManager;

    public Consumer(int counter, StorageManager storageManager){
        this.counter = counter;
        this.storageManager = storageManager;

        new Thread(this).start();
    }

    @Override
    public void run(){
        for(int i = 0; i < counter; i++){
            String item;
            try{
                storageManager.storageEmpty.acquire();
                storageManager.sem.acquire();

                item = storageManager.products.get(0);
                storageManager.products.remove(0);
                System.out.println("Consumer " + Thread.currentThread().getId() + " took " + item);

                storageManager.sem.release();
                storageManager.storageFull.release();
            }   catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
