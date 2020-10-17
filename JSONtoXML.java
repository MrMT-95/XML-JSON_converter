package converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JSONtoXML {
    String element = null;
    String content = null;
    String attribute = null;


    public String getElement(String input) {

        Matcher elementMatch = Pattern.compile("(?<=\\{\")([\\w.]+)(?=\")").matcher(input.replaceAll("\\s", ""));

        elementMatch.find();

        element = elementMatch.group();

        return element;
    }

    public String getContent(String input) {
        Matcher contentMatch = Pattern.compile("(?<=\")([\\w.]+)(?=\"\\})").matcher(input.replaceAll("\\s", ""));

        if (contentMatch.find()) {
            content = contentMatch.group();
        } else {
            content = null;
        }

        return content;
    }

    public String getAttribute(String input) {

        Matcher attributeMatch = Pattern.compile("(?<=@)(\\w+)").matcher(input.replaceAll("\\s", ""));
        Matcher attributeValueMatch = Pattern.compile("(?<=\\:)([\\w\\\"]+)(?=\\,)").matcher(input.replaceAll("\\s", ""));
        String value = "";
        while (attributeMatch.find()) {
            attributeValueMatch.find();

            value += " " + attributeMatch.group()
                    + " = \""
                    + attributeValueMatch.group().replaceAll("\"", "") + "\"";

        }
        attribute = value;

        return attribute;
    }


}
