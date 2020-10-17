package converter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.*;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        String output = null;
        String input = "";
        final String fileName = "test.txt";
        String filePath = "C:\\Users\\Mateusz\\IdeaProjects\\JSON - XML converter\\JSON - XML converter\\task\\";
        File file = new File(filePath + fileName);
        Scanner scanner = new Scanner(file);
        XMLtoJSON xmlToJSON = new XMLtoJSON();
        JSONtoXML jsonToXML = new JSONtoXML();


        while (scanner.hasNextLine()) {
            input += scanner.nextLine();
        }


        Pattern patternXML = Pattern.compile("<(.*?)>");
        Matcher matcherXML = patternXML.matcher(input);

        Pattern patternJson = Pattern.compile("\\{(.*?)\\}");
        Matcher matcherJson = patternJson.matcher(input);

        scanner.close();


        if (matcherXML.matches()) {

            if (xmlToJSON.getAttribute(input) == null || xmlToJSON.getAttribute(input).equals("")) {
                output = "{" + xmlToJSON.getElement(input) + ":" + xmlToJSON.getContent(input) + "}";
            } else {

                output = "{\n\t"
                        + xmlToJSON.getElement(input) + " : {"
                        + xmlToJSON.getAttribute(input)
                        + "\n\t\t\"#" + xmlToJSON.getElement(input).replace("\"", "") + "\" : " + xmlToJSON.getContent(input)
                        + "\n" + "\t}\n"
                        + "}";
            }


        } else if (matcherJson.find()) {



            if (jsonToXML.getAttribute(input) == null || jsonToXML.getAttribute(input).equals("")) {

                if (input.contains("null")) {
                    output = "<" + jsonToXML.getElement(input) + "/>";
                } else {

                    output = "<" + jsonToXML.getElement(input) + ">" + jsonToXML.getContent(input) + "</" + jsonToXML.getElement(input) + ">";

                }
            } else {

                if (input.contains("null")) {
                    output = "<" + jsonToXML.getElement(input) + jsonToXML.getAttribute(input) + "/>";
                } else {

                    output = "<" + jsonToXML.getElement(input)
                            + jsonToXML.getAttribute(input) + ">"
                            + jsonToXML.getContent(input) + "</"
                            + jsonToXML.getElement(input) + ">";

                }


            }


        }


        System.out.println(output);




    }


}
