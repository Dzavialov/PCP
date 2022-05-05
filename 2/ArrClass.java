import java.util.Random;

public class ArrClass extends FindMin{
    public void Start() throws java.lang.Exception
    {
        int[] arr = new int[10000000];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++)
        {
            arr[i] = random.nextInt();
        }
        FindMin(arr, 1);
        FindMin(arr, 5);
        FindMin(arr, 10);
    }
}