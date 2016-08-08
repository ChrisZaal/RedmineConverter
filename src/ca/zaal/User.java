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
     * This is going to be a weird function. I want to use it to identify if a new username is in the line. If so
     * I want to return null, else we're going to pick up the project and send it back to the calling function.
     * @param line will there be something
     * @return null if a username; null if project number isn't found
     */
    public String getProject(String line) {
        String[] items = null;
        String matchedString = null, temp = null;

        if(getUsername(line) != null)
            return null;

        items = line.split(",");

        Pattern pattern = Pattern.compile("([0-9]{4})");
        Matcher m = pattern.matcher(line);
        //System.out.println("Name field contains "+ items[0]);

        if (m.find()) {
            System.out.println("True!");
            //matchedString = line.substring(m.start(), m.end());
            matchedString = m.group(1);
            System.out.println("Matched String is " + matchedString);
            pattern = Pattern.compile("[0-9]{2,3}(\\.[0-9]{1,2})");
            m.reset();
            m = pattern.matcher(line);
            m.find();
            temp = m.group();
            matchedString += "," + temp;

        }
        if (matchedString == null)
            return null;

        return matchedString;
    }

    /**
     * This method will process an input line and check to see if it's either a User name, a project number or
     * something else.
     * @param line String that will be checked for different values.
     * @return String containing the values to be passed back to calling function
     */
    public int processLine(String line, User thisUser) {
        String tempString;

        tempString = getUsername(line);
        if (tempString != null && !tempString.isEmpty()) {
            thisUser.setName(tempString);
            return 1;
        }

        tempString = getProject(line);
        if (tempString != null && !tempString.isEmpty())
            return 2;

        return -1;
    }

    public boolean isName(String line){
        String[] items = null;

        items = line.split(",");

        if (items[0].length() > 4)
            return true;

        return false;
    }
}