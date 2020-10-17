package converter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLtoJSON {
    String element = null;
    String content = null;
    String attribute = null;



    public String getElement(String input) {


        Matcher elementMatch = Pattern.compile("(?<=\\<)(.*?)(?=\\>)").matcher(input);
        Matcher elementMatchNoContent = Pattern.compile("(?<=\\<)(.*?)(?=\\/\\>)").matcher(input);
        Matcher elementMatchWithContent = Pattern.compile("(?<=\\<\\/)(.*?)(?=\\>)").matcher(input);

        while (elementMatch.find()) {

            if (elementMatchNoContent.find()) {

                Matcher elementMatchWithAttribute = Pattern.compile("(?<=<)(\\w+)(?=\\s)").matcher(input);

                if (elementMatchWithAttribute.find()) {


                    element = "\"" + elementMatchWithAttribute.group() + "\"";
                } else {
                    element = "\"" + elementMatchNoContent.group() + "\"";
                }
            } else if (elementMatchWithContent.find()) {

                element = "\"" + elementMatchWithContent.group() + "\"";

            }

        }


        return element;
    }

    public String getContent(String input) {


        Matcher contentMatch = Pattern.compile("(?<=\\>)(.*?)(?=\\<)").matcher(input);
        while (contentMatch.find()) {
            content = "\"" + contentMatch.group(1) + "\"";
        }
        return content;


    }

    public String getAttribute(String input) {

        Matcher attributeMatch = Pattern.compile("(?<=\\s)(\\w+)(?=[\\s|\\=])").matcher(input);
        Matcher attributeValueMatch = Pattern.compile("(?<=\")(\\w+?)(?=\")").matcher(input);
        String value = "";
        while (attributeMatch.find()) {
            attributeValueMatch.find();

            value += "\n\t\t\"@" + attributeMatch.group()
                    + "\" : \""
                    + attributeValueMatch.group() + "\",";

        }
        attribute = value;


        return attribute;
    }


}
