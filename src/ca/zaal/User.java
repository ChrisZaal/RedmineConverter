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

    public User() {
    }

    public User(String name){
        setName(name);
    }

    public User(String name, String[] project, float[] hours) {
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

    public String getUsername(String inputString) {
        String[] items = null;

        items = inputString.split(",");
        //System.out.println("Element 0 of the items array is "+ items[0]);

        if(items[0].length() < 4)
            return null;

        return items[0];
    }

    public String getProject(String line) {
        String[] items = null;
        String matchedString = null, temp = null;
        int projectNumber = 0;

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
            matchedString += ","+temp;
            System.out.println("Not Found");

        }
        if(matchedString == null)
            return null;

        return matchedString;
    }

    public Object processLine(String line){
        String username;
        String projectNumber;

        username = getUsername(line);
        if(username != null && !username.isEmpty())
            return username;

        projectNumber = getProject(line);
        if(projectNumber != null && !projectNumber.isEmpty())
            return projectNumber;

        return null;
    }
}