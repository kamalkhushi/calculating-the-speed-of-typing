package com.company;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;
public class Main {
    public static void main(String[] args)
    {

        String wordInput,temp;
        int numberInput=0,score=0,totalScore=0,highScore=0;
        //string containing list of words
        String[] randomWords = {"Volvo", "BMW", "Ford", "Mazda","Sixpep","Kamal","Khushi","jet","bed","lead","dead","pen","ball"};
        Random obj=new Random();
        File Obj1 = new File("G:\\highscore.txt");
        Scanner scan=new Scanner(System.in);
        //limiting the decimal point
        DecimalFormat df = new DecimalFormat("#.####");
        while(numberInput!=4)
        {
            System.out.println("Test your typing speed\n1. New Word 2. Score 3. HighScore 4. Save & Quit\nPlayer input is");
            numberInput=scan.nextInt();
            if(numberInput>4||numberInput<1)
            {
                System.out.println("Invalid input please try again");
            }
            else if(numberInput==1)
            {
                // (obj.nextInt(n) random number from 0 to n-1) and using that number to store random word from the string randomWords
                temp=randomWords[obj.nextInt(13)];
                System.out.println("Your new word is "+temp);
                //storing the start time in seconds
                double start=System.currentTimeMillis()/1000D;
                //taking user input
                wordInput=scan.next();
                //storing the end time in seconds
                double end=System.currentTimeMillis()/1000D;
                //finding time taken to type (seconds=end-start)
                double seconds=end-start;
                // -1 point if the word we typed is incorrect
                if(!temp.equals(wordInput))
                {
                    score=-1;
                }
                // 2 points if the word is typed below 2 seconds
                else if(seconds<2D)
                {
                    score=2;
                }
                // 1 points if the word is typed below 3 seconds
                else if(seconds<3D&&seconds>2D)
                {
                    score=1;
                }
                // 0 points if the word is typed more than 3 seconds
                else if(seconds>3D)
                {
                    score=0;
                }
                totalScore=totalScore+score;
                System.out.println("Time take to type "+temp+" is "+df.format(seconds)+" seconds\nYou scored "+score+" points and your total score is "+totalScore);
            }
            //press 2 for Score
            else if(numberInput==2)
            {
                System.out.println("Your Score "+totalScore);
            }
            //press 3 for Highscore
            else if(numberInput==3)
            {
                if(highScore==0)
                {  //Getting highscore
                    try
                    {
                        File myObj = new File("G:\\highscore.txt");
                        Scanner myReader = new Scanner(myObj);
                        while (myReader.hasNextLine())
                        {
                            String data = myReader.nextLine();
                            highScore = Integer.parseInt(data);
                        }
                        myReader.close();
                    }
                    catch (FileNotFoundException e)
                    {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                }
                //updating Highscore
                if(highScore<totalScore)
                {
                    highScore=totalScore;
                    System.out.println("HighScore "+highScore);
                }
                else
                {//displaying the highscore from the file saved
                    try
                    {
                        File myObj = new File("G:\\highscore.txt");
                        Scanner myReader = new Scanner(myObj);
                        while (myReader.hasNextLine())
                        {
                            String data = myReader.nextLine();
                            System.out.println("HighScore "+data);
                        }
                        myReader.close();
                    } catch (FileNotFoundException e)
                    {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                }
            }
            //press 4 for saving HighScore and quiting
            else if(numberInput==4)
            {//if highscore greater than zero then it save the highscore in the text file
                if(highScore>0)
                {
                    try
                    {
                        File myObj = new File("G:\\highscore.txt");
                        FileWriter myWriter = new FileWriter("G:\\highscore.txt");
                        //(myobj.createNewFile() This method returns a boolean value true if the file was successfully created, and false if the file already exists
                        if (myObj.createNewFile())
                        {
                            continue;
                        }
                        //writing the highscore into the text file
                        myWriter.write("" + highScore);
                        myWriter.close();
                    }
                    catch (IOException e)
                    {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                }
                System.out.println("Saving Score and exiting the application");
                break;
            }
        }
    }
}
