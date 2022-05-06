using System;
using System.Threading;

class Program
{
    public static void Main(string[] args)
    {
        new Program().Start();
    }

    private void Start()
    {
        var arr = new int[100000];
        var random = new Random();

        for (int i = 0; i < arr.Length; i++)
        {
            arr[i] = random.Next();
        }

        FindMin(arr, 1);
        FindMin(arr, 3);
        FindMin(arr, 5);
        FindMin(arr, 7);
        FindMin(arr, 9);
    }
    private void FindMin(int[] arr, int threadsCnt)
    {
        var threads = new Thread[threadsCnt];

        var min = int.MaxValue;
        object block = new object();
        var idx = 0;

        for (int threadId = 0; threadId < threads.Length; threadId++)
        {
            var th = threadId;
            threads[threadId] = new Thread(() =>
            {
                for (int i = arr.Length * th / threadsCnt; i < arr.Length * 
                (th + 1) / threadsCnt; i++)
                {
                    if (arr[i] < min)
                    {
                        lock (block)
                        {                           
                                min = arr[i];
                            idx = i;
                        }
                    }
                }
            });

            threads[threadId].Start();
        }

        foreach (var item in threads)
        {
            item.Join();
        }

        Console.WriteLine($"amount of threads = {threadsCnt}, min = {min}, index = {idx}");
    }
}