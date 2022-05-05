public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        int storageSize = 7;
        int item = 11;
        main.starter(storageSize, item, 2, 5);
    }

    private void starter(int storageSize, int item, int producer, int consumer){
        StorageManager storageManager = new StorageManager(storageSize);

        for(int i = 0; i < producer; i++){
            new Producer(i * item / producer, (i+1)*item/producer, storageManager);
        }

        for(int i = 0;i < consumer; i++){
            new Consumer((i+1)*item/consumer- i*item/consumer, storageManager);
        }
    }
}
