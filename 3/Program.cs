using System;
using System.Threading;
using System.Collections.Generic;

namespace Lab3_cSharp
{
    class Program
    {
        private static Random r = new Random();
        static void Main(string[] args)
        {
            Console.WriteLine("Enter storage size: ");
            int storageSize = Convert.ToInt32(Console.ReadLine());
            Console.WriteLine("Enter the amount of items for that producer and consumer need to process: ");
            int amount = Convert.ToInt32(Console.ReadLine());

            Storage storage = new Storage(storageSize);
            for (int i = 0; i < 5; i++)
            {
                new Thread(() => Producer("Producer " + i, amount, storage)).Start();
                new Thread(() => Consumer("Consumer " + i, amount, storage)).Start();
            }
        }
        class Storage
        {
            public Semaphore sem = new Semaphore(1, 1);
            public Semaphore notEmpty;
            public Semaphore notFull;

            public List<string> products = new List<string>();

            public Storage(int storageSize)
            {
                this.notEmpty = new Semaphore(0, storageSize);
                this.notFull = new Semaphore(storageSize, storageSize);
            }
        }
        static void Producer(string name, int amount, Storage storage)
        {
            for (int i = 0; i < amount; i++)
            {
                storage.notFull.WaitOne();
                storage.sem.WaitOne();
                storage.products.Add("Product");
                Console.WriteLine(name + " put the product. Now in storage: " + storage.products.Count);
                storage.notEmpty.Release();
                storage.sem.Release();
            }
        }
        static void Consumer(string name, int amount, Storage storage)
        {
            for (int i = 0; i < amount; i++)
            {
                storage.notEmpty.WaitOne();
                storage.sem.WaitOne();
                storage.products.RemoveAt(0);
                Console.WriteLine(name + " took product. Now in storage: " + storage.products.Count);
                storage.notFull.Release();
                storage.sem.Release();
            }
        }
    }
}