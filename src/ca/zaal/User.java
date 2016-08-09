package ca.zaal;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by czaal on 8/4/2016.
 */
public class User {
    String name;
    String[] projects;
    float[] hours;
    int numProjects;

    protected User() {
    }

    protected User(String name) {
        setName(name);
    }

    protected User(String name, String[] project, float[] hours) {
        setName(name);
        setProjects(project);
        setHours(hours);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getProjects() {
        return projects;
    }

    public void setProjects(String[] project) {
        this.projects = project;
    }

    public float[] getHours() {
        return hours;
    }

    public void setHours(float[] hours) {
        this.hours = hours;
    }

    /**
     * This function will split an input string from a CSV file and we can check it.
     * @param inputString
     * @return String the user's name as stripped out of the string. null if not a username
     */
    public String getUsername(String inputString) {
        String[] items = null;

        items = inputString.split(",");
        //System.out.println("Element 0 of the items array is "+ items[0]);

        if (items[0].compareToIgnoreCase("Total time") == 0 || items[0].length() < 0)
            return null;

        return items[0];
    }

    /**
     * This method will process an input line and check to see if it's either a User name, a project number or
     * something else.
     * @param line String that will be checked for different values.
     * @return String containing the values to be passed back to calling function
     */
    public int processLine(String line, User thisUser) {
        String tempString, temp;
        String[] items;
        StringBuilder sb = new StringBuilder(15);

        tempString = getUsername(line);
        if (tempString != null && !tempString.isEmpty()) {
            thisUser.setName(tempString);
            return 1;
        }

        items = line.split(",");

        Pattern pattern = Pattern.compile("([0-9]{4})");
        Matcher m = pattern.matcher(line);
        //System.out.println("Name field contains "+ items[0]);

        if (m.find()) {
            System.out.println("True!");
            //matchedString = line.substring(m.start(), m.end());
            sb.append(m.group(1));
            System.out.println("Matched String is " + tempString);
            pattern = Pattern.compile("[0-9]{2,3}(\\.[0-9]{1,2})");
            m.reset();
            m = pattern.matcher(line);
            m.find();
            temp = m.group();
            sb.append("," + temp);

        }
        if (tempString != null && !tempString.isEmpty())
            return 2;

        return -1;
    }

    /**
     * This method will determine the type of record that is being
     * @param line string containing data to be searched through
     * @return int 1 for username, 2 for project, 3 for anything else that will probably be discarded.
     */
    public int lineType(String line){
        int typeOfLine = 0;


        return typeOfLine;
    }
}