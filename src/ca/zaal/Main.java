package ca.zaal;

import static java.lang.System.*;

import java.lang.*;
import java.io.*;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 While we continue to receive lines from the opened csv file
    Check what kind of entry it is (Username, Project or other)
        If it’s a username
            If no current user object exists
                Create a User Object and store the name in it
            Else
                Save current user object to the User Objects arraylist
                Instantiate a User Object and store the name in it
        If it’s a project( with project Number)
            Add the project number and the number of hours to the User Objects projects and hours arrays. Use the projectNum var for the proper index.
        If it’s Other
            Discard

 Close CSV file

 Process User Objects Arraylist
    For each User object
        Get each object from the Projects Arraylist
        Create a string formatted for the ARIS TS application and write to the out file
 Close out file

 */

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            out.println("No file name specified");
            out.println("USAGE: java -jar RedmineFormatter <filename>");
            exit(-99);
        }
        out.println("Application starting...");
        //open the input file
        String fileName = args[0];

        String line;
        String retValue;
        int retType;
        boolean newName = false;
        String temp = null;
        float[] projectNum = null;
        int projectCount = 0;
        User thisUser = new User();
        ArrayList<User> UserList = new ArrayList<User>();
        ArrayList<Integer> ProjectsList = new ArrayList<Integer>();
        ArrayList<String> recordList = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                retType = thisUser.processLine(line, thisUser);
                if(retType == 1) { // we got a username

                }
                else if(retType == 2){// we got a project
                }

            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * parseNumbers
     *
     * @param inputString string that will be searched to find a possible project number
     * @return the project number if found; 0 if no project number found.
     */
    protected static User parseNumbers(String inputString, ArrayList<Integer> projectList) {
        int retVal = 0;
        String[] temp = null;
        String matchedString = null;
        User localUser = new User();

        temp = inputString.split(",");
        Pattern pattern = Pattern.compile("[0-9]{4}");
        Matcher m = pattern.matcher(inputString);
        System.out.println("Name field contains " + temp[0]);
        if (temp[0].isEmpty() != true) {
            localUser.setName(temp[0]);
        }
        if (m.lookingAt()) {
            System.out.println("True!");
            matchedString = inputString.substring(m.start(), m.end());
            System.out.println("Matched String is " + matchedString);
        }
        return localUser;
    }
}
