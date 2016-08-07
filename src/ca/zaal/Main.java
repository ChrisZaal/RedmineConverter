package ca.zaal;

        import static java.lang.System.*;

        import java.lang.*;
        import java.io.*;
        import java.util.ArrayList;
        import java.util.regex.Matcher;
        import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
        if (args.length < 1) {
            out.println("No file name specified");
            out.println("USAGE: java -jar RedmineFormatter <filename>");
            exit(-99);
        }

        //open the input file
        String fileName = args[0];
        out.println("Application starting...");
        String line;
        Object retValue;
        boolean found = false;
        String temp = null;
        float[] projectNum = null;
        int projectCount = 0;
        User thisUser = new User();
        ArrayList<User> UserList = new ArrayList<User>();
        ArrayList<Integer> ProjectsList = new ArrayList<Integer>();

        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                retValue = thisUser.processLine(line);
                if (retValue != null && retValue.getClass() == String.class && !"".equals(retValue)) {
                    thisUser.setName((String)retValue);
                    continue;
                }
                else if(retValue != null&& retValue.getClass() == Integer.class) {
                    ProjectsList.add((Integer)retValue);
                    System.out.println("Project Num is " + projectNum.toString());

                    continue;
                }
                else System.out.println("Must be an empty line or I don't know what I'll have working through!!!");
            }

            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //store in an arrayList
        //skip first line
        //second line will contain Username
        //store username
        //read other lines until another Username is encountered in column one
        //parse the entries from first username using the account codes that should be found in each project name
        //using regex to find patterns (Foapal account codes should be used for this)
        //if no matches, add to 'default' time bucket for user
        //save errors where there are no matches
        //write out a new CSV file based on format required for Access Timesheet
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
