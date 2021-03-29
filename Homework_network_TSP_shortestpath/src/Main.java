import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Formatter;
import java.util.HashSet;
import java.util.function.IntBinaryOperator;


public class Main {

    static  double min = Double.POSITIVE_INFINITY;

    static void calcDistance(double[][] dist,int[] a)
    {
        double length = 0;
        for(int i = 0; i < a.length-1; i++)
        {
            //System.out.print(a[i] + " " + a[i+1] + " ");
            length += dist[a[i]][a[i+1]];
        }
        if(length < min)
            min = length;
        //System.out.println(length);
    }
    //Generating permutation using Heap Algorithm
    static void  heapPermutation(double[][] dist,int a[], int size, int n)
    {
        // if size becomes 1 then prints the obtained
        // permutation
        if (size == 1)
        {
            calcDistance(dist,a);
            return;
        }

        for (int i=0; i<size; i++)
        {
            //heapPermutation(dist, a, size-1, n);

            // if size is odd, swap first and last
            // element
            if (size % 2 == 1)
            {
                //switch(a[0],a[size-1])
                int temp = a[0];
                a[0] = a[size-1];
                a[size-1] = temp;
            }

            // If size is even, swap ith and last
            // element
            else
            {
                //switch(a[i],a[size-1])
                int temp = a[i];
                a[i] = a[size-1];
                a[size-1] = temp;
            }
            System.out.println(Arrays.toString(a));
        }
    }

    public static void main(String[] args) {

        try(BufferedReader input = new BufferedReader(new FileReader("INPUT")))
        {
            int num = Integer.parseInt(input.readLine());
            double[][] dist = new double[num][num];
            int[][] vertices = new int[num][2];
            String line;
            int k = 0;
            while ((line = input.readLine() )!= null)
            {
                var params = line.split(" ");
                vertices[k][0] = Integer.parseInt(params[0]);
                vertices[k][1] = Integer.parseInt(params[1]);

                k++;
            }

            for(int i = 0; i < num; i++)
            {
                for(int j = 0; j < num; j++)
                {
                    dist[i][j] = Math.sqrt(((vertices[i][0]-vertices[j][0])*(vertices[i][0]-vertices[j][0]) + (vertices[i][1]-vertices[j][1])*(vertices[i][1]-vertices[j][1])));
                }
            }

            int[] vertexPerm = new int[num];
            for(int i = 0; i < num; i++)
            {
                vertexPerm[i] = i;
            }
            heapPermutation(dist,vertexPerm,num,num);
            //Formatter fmt = new Formatter();

            //fmt.format("%.2f", min);

            System.out.printf("Minimal length of the network is: %.2f",min);

            // System.out.print("Minimal length of the network is: " + dec.format(min));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
