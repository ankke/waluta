package pl.parser.nbp;

import java.text.ParseException;

public class MainClass {

    public static void main(String[] args) {

        try{
            CommandLineBuilder cmd = new CommandLineBuilder();
            cmd.runParser(args);
        }catch(IllegalArgumentException e){
            System.out.println("Invalid currency. Choose from USD, EUR, CHF, GBP. Try -h for help.");
            e.printStackTrace();
        }catch (ParseException e){
            System.out.println("Wrong date format. Should be yyyy-MM-dd. Try -h for help.");
            e.getMessage();
        }
    }

}