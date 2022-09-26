public class FindMin {
    private int min = Integer.MAX_VALUE;

    public void FindMin(int[] arr, int threadsCnt) throws java.lang.Exception
    {
        Thread[] threads = new Thread[threadsCnt];


        Object block = new Object();

        for (int threadId = 0; threadId < threads.length; threadId++)
        {
            var th = threadId;
            threads[threadId] = new Thread(() ->
            {
                for (int i = arr.length * th / threadsCnt; i < arr.length *
                        (th + 1) / threadsCnt; i++)
                {
                    if (arr[i] < min)
                    {
                        synchronized (block)
                        {
                            min = arr[i];
                        }
                    }
                }
            });

            threads[threadId].run();
        }
        for (Thread item : threads)
        {
            item.join();
        }

        System.out.println("amount of threads = " + threadsCnt + ", min is " + min);
    }
}
