import java.util.Scanner;

public class Regex {
    private final String DOT = ".";

    public static void main(String[] args) {
        var regex = new Regex();
        Scanner s = new Scanner(System.in);
        System.out.println(regex.search("a?b?c?", ""));
        while (true) {
            String[] split = s.nextLine().trim().split("\\s+");
            try {
                if (split.length == 1)
                    System.out.println(regex.search(split[0], ""));
                else
                    System.out.println(regex.search(split[0], split[1]));

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println(split[0] + "  " + split[1]);
            }
        }
    }

    private boolean matchOne(String pattern, String text) {
        if (pattern.isEmpty()) {
            return true;
        }
        if (text.isEmpty()) {
            return false;
        }
        if (pattern.equals(DOT)) {
            return true;
        }
        return pattern.equals(text);
    }

    private boolean matchOneChar(char pattern, char text) {
        if (pattern == '.') {
            return true;
        }
        return pattern == text;
    }

    private boolean match(String pattern, String text) {
        if (pattern.isEmpty()) {
            return true;
        }

        if (pattern.equals("$") && text.isEmpty()) {
            return true;
        }
        if (pattern.length() >= 2 && pattern.charAt(1) == '?') {
            return matchQuestion(pattern, text);
        }
        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            return matchStar(pattern, text);
        }
        if (pattern.charAt(0) == '.') {
            if (text.isEmpty()) {
                return match(pattern.substring(1), text);
            } else {
                return match(pattern.substring(1), text.substring(1));
            }
        }
        if (text.isEmpty() && !pattern.isEmpty()) {
            return false;
        }
        return pattern.charAt(0) == text.charAt(0) &&
                match(pattern.substring(1), text.substring(1));
    }

    private boolean matchStar(String pattern, String text) {
        //match the  char
        if (text.length() >= 1) {
            if (pattern.charAt(0) == text.charAt(0) &&
                    match(pattern, text.substring(1))) {
                return true;
            }
        }
        // skip the char
        return match(pattern.substring(2), text);
    }

    private boolean matchQuestion(String pattern, String text) {
        //match the  char
        if (text.length() >= 1) {
            if (pattern.charAt(0) == text.charAt(0) &&
                    match(pattern.substring(2), text.substring(1))) {
                return true;
            }
        }
        // skip the char
        return match(pattern.substring(2), text);
    }

    //costly metthod
    public boolean search(String pattern, String text) {
        if (pattern.isEmpty()) {
            return true;
        }
        if (pattern.charAt(0) == '^') {
            return match(pattern.substring(1), text);
        } else {
            if (match(pattern, text)) {
                return true;
            }
            for (int i = 0; i < text.length(); i++) {
                if (match(pattern, text.substring(i))) return true;
            }
            return false;
        }
    }
}
