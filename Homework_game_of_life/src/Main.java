import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int getNeighbourCount(char[][] lifeMatrix, int X, int Y)
    {
        int count = 0;
        int iters = 0;
        for(int i = -1; i <= 1; i++)
        {
            for(int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0){
                    //System.out.print(" ");
                    continue;
                }
                ++iters;
                int neighbourX = ((X + i) % lifeMatrix.length + lifeMatrix.length) % lifeMatrix.length;
                int neighbourY = ((Y + j) % lifeMatrix.length + lifeMatrix.length) % lifeMatrix.length;
                //System.out.println(neighbourX + " " + neighbourY + " " + lifeMatrix[neighbourX][neighbourY]);
                //System.out.print(lifeMatrix[neighbourX][neighbourY]);
                if(lifeMatrix[neighbourX][neighbourY] == 'X')
                    count++;
            }
            //System.out.println("");
        }
        //System.out.println(X + " " + Y  + " " +count);
        return  count;
    }

    public static char[][] generateNextCycle(char[][] lifeMatrix)
    {
        char[][] newMatrix = new char[lifeMatrix.length][lifeMatrix.length];
        for(int i = 0; i < lifeMatrix.length; i++)
        {
            for(int j = 0; j < lifeMatrix.length; j++)
            {

                int neighbours = getNeighbourCount(lifeMatrix,i,j);
                //System.out.print(neighbours);
                if(lifeMatrix[i][j] == 'X' && neighbours < 2)
                {
                    newMatrix[i][j] = '_';
                }
                else if(lifeMatrix[i][j] == 'X' && (neighbours == 2 || neighbours == 3))
                {
                    newMatrix[i][j] = 'X';
                }
                else if(lifeMatrix[i][j] == 'X' && neighbours > 3)
                {
                    newMatrix[i][j] = '_';
                }
                else  if(lifeMatrix[i][j] == '_' && neighbours == 3)
                {
                    newMatrix[i][j] = 'X';
                }
                else
                {
                    newMatrix[i][j] = '_';
                }

            }
            //System.out.println("");

        }
        return newMatrix;


    }

    public static void main(String[] args) {
	// write your code here
        try(BufferedReader input = new BufferedReader(new FileReader("INPUT")))
        {
            int c = 0;
            StringBuilder word = new StringBuilder();
            while((c = input.read()) != -1 && (char)(c) != ' ')
            {
                word.append((char) c);
            }
            int N = Integer.parseInt(word.toString());
            word = new StringBuilder();
            c = 0;
            while((c = input.read()) != -1 && (char)(c) != '\n' )
            {
                word.append((char) c);
            }
            int IterationNumber = Integer.parseInt(word.toString());

            char[][] lifeMatrix = new char[N][N];

            for(int i = 0; i < N; i++)
            {
                for(int j = 0; j < N; j++)
                {
                    lifeMatrix[i][j] = (char)input.read();
                }
                input.read();
            }

            for(int i = 0; i < IterationNumber; i++)
            {
                lifeMatrix = generateNextCycle(lifeMatrix);

                for(int k = 0; k < N; k++)
                {
                    for(int j = 0; j < N; j++)
                    {
                        System.out.print(lifeMatrix[k][j]);
                    }
                    System.out.println("");
                }
                System.out.println("");


            }

            for(int i = 0; i < N; i++)
            {
                for(int j = 0; j < N; j++)
                {
                    System.out.print(lifeMatrix[i][j]);
                }
                System.out.println("");
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
