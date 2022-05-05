using System;
using System.Threading;

namespace Lab1_cSharp
{
    class Program
    {
        static void Main(string[] args)
        {
            (new Program()).Start();
            Console.ReadKey();
        }
      
        class MainThread
        {
            private int id;
            private int step;
            private BreakThread breakThread;
            public MainThread(int id, int step, BreakThread breakThread)
            {
                this.id = id;
                this.step = step;
                this.breakThread = breakThread;
            }
            public void Calculator()
            {
                long sum = 0;
                long count = 0;
                do
                {
                    sum += step;
                    count++;

                } while (!breakThread.CanStop);
                Console.WriteLine("id = {0}, sum = {1}, count = {2}\n",
                id, sum, count);
            }
        }
        class BreakThread
        {
            private bool canStop = false;

            public bool CanStop { get => canStop; }

            public void Stoper()
            {
                Thread.Sleep(1 * 1000);
                canStop = true;
            }
        }
        void Start()
        {
            BreakThread breakThread = new BreakThread();
            new Thread(new MainThread(1, 10, breakThread).Calculator).Start();
            new Thread(new MainThread(2, 17, breakThread).Calculator).Start();
            new Thread(new MainThread(3, 23, breakThread).Calculator).Start();
            new Thread(new MainThread(4, 26, breakThread).Calculator).Start();
            new Thread(breakThread.Stoper).Start();
        }
    }
}