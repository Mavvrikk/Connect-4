import java.util.Scanner;
public class ConnectFour
{
    private int redCount = 0;
    private int yelCount = 0;
    public boolean hasWon = false;
    public String [][] gameBoard;
    public boolean Player1 = true;
    public ConnectFour()
    {
        this.gameBoard = CreateBoard();
    }
    public String[][] CreateBoard()
    {
        String[][] C4 = new String[8][15];
        for (int i = 0; i < C4.length; i++)
        {
            for (int j = 0; j < C4[i].length; j++)
            {
                if (j % 2 == 0)
                {
                    C4[i][j] = "|";
                }
                else
                {
                    C4[i][j] = " ";
                }
                if (i == 7)
                {
                    C4[i][j] = "-";
                }
            }
        }
        return C4;
    }

    public void printGame()
    {
        for (int i = 0; i < this.gameBoard.length; i++)
        {
            for (int j = 0; j < this.gameBoard[i].length; j++)
            {
                System.out.print(this.gameBoard[i][j]);
            }
            System.out.println();
        }
    }
    public void makinMoves()
    {
        Scanner scanner = new Scanner(System.in);
        int input;
        do
        {
            if (Player1)
            {
                System.out.println("Player 1 please make a move");
            } else
            {
                System.out.println("Player 2 please make a move");
            }
            do
            {
                System.out.println("Please choose a column 1 - 7 ");
                input = scanner.nextInt();
                input *= 2;
                input -= 1;
            }
            while (input > 16);

            boolean dropped = false;
            for (int i = 6; i > 0; i--)
            {
                if (this.gameBoard[i][input].equals(" ") && !dropped)
                {
                    dropped = true;
                    if (Player1)
                    {
                        this.gameBoard[i][input] = "R";
                        checkWins(i, input);
                    }
                    else
                    {
                        this.gameBoard[i][input] = "Y";
                        checkWins(i, input);
                    }
                }
            }
            Player1= !Player1;
            this.printGame();
        }while(!hasWon);
    }
    public void checkWins (int y, int x)
    {
        // x = input, is being tested
       for (int i=6; i>=0; i--) // vertical test
       {
           this.winsLoop(i,x);

       }
       this.redCount = 0;
       this.yelCount=0;
        for (int i=0; i< gameBoard[y].length; i++) // horizontal test
        {
            this.winsLoop(y,i);
        }

        this.redCount = 0;
        this.yelCount=0;
        // Tom's Theorem (Up and Right Diagonal)
        x += 1;
        x/=2;
        int z = x+y;
        int tempX;
        int tempY;
        // establish top right x coordinate
         tempX = Math.min(z, 7);
        // establish top right y coordinate
        tempY = z-tempX;
        // turn X coord back into a grid point
        tempX*=2;
        tempX-=1;

        while (tempX >=1 && tempY < this.gameBoard.length)
        {
            winsLoop(tempY, tempX);
            tempY ++;
            tempX-=2;

        }
        // Test for the other diagonal
        z= Math.min(x,y);
        tempX = x - z;
        // turn X coord back into a grid point
        tempX*=2;
        tempX-=1;
        if (tempX <= 0)
        {
            tempX = 1;
        }
        tempY = y - z;
        while (tempX < this.gameBoard[y].length && tempY < this.gameBoard.length)
        {

            winsLoop(tempY, tempX);
            tempY ++;
            tempX+=2;
        }
    }

    public void winsLoop(int y, int x)
    {
        if (this.gameBoard[y][x].equals("R"))
        {
            this.yelCount=0;
            this.redCount++;
        }
        if (this.gameBoard[y][x].equals("Y"))
        {
            this.redCount = 0;
            this.yelCount++;
        }

        if (this.yelCount > 3 || this.redCount > 3)
        {
            hasWon = true;
            if(Player1)
            {
                System.out.println ("Congratulations, Player 1 wins");
            }
            else
            {
                System.out.println("Congratulations, Player 2 wins");
            }
        }
        if (this.gameBoard[y][x].equals(" "))
        {
            this.yelCount = 0;
            this.redCount = 0;
        }
    }
}
