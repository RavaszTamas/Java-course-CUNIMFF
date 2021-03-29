public class Main {

    public static void main(String[] args) {
        int num = Integer.parseInt(args[0]);
        for (int i = 1; i <= 9; i++)
        {
            if(num == 10)
            {
                System.out.println(" " + i + " * " + num + " =  " + i * num);
            }
            else {
                if (i * num >= 10)
                    System.out.println(" " + i + " * " + num + " = " + i * num);
                else
                    System.out.println(" " + i + " * " + num + " =  " + i * num);
            }

        }
        System.out.println(10+" * " + num+ " = " + 10 * num);

    }
}
