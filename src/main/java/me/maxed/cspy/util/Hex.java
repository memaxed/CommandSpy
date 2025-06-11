package me.maxed.cspy.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Hex {
    private static final Pattern hex = Pattern.compile("#[0-9a-fA-F]{6}");
    private static final Pattern fix2 = Pattern.compile("\\{#[0-9a-fA-F]{6}\\}");
    private static final Pattern fix3 = Pattern.compile("\\&x[\\&0-9a-fA-F]{12}");
    private static final Pattern gradient1 = Pattern.compile("<#[0-9a-fA-F]{6}>[^<]*</#[0-9a-fA-F]{6}>");
    private static final Pattern gradient2 = Pattern.compile("\\{#[0-9a-fA-F]{6}>\\}[^\\{]*\\{#[0-9a-fA-F]{6}<\\}");
    public static class TextColor {
        private final int red;
        private final int green;
        private final int blue;
        public TextColor(String hexCode) {
            int hexColor = Integer.parseInt(hexCode.substring(1), 16);
            this.red = (hexColor >> 16) & 0xFF;
            this.green = (hexColor >> 8) & 0xFF;
            this.blue = hexColor & 0xFF;
        }
        public int getRed() {
            return red;
        }
        public int getGreen() {
            return green;
        }
        public int getBlue() {
            return blue;
        }
    }
    
    private static String toChatColor(String hexCode) {
        StringBuilder magic = new StringBuilder("§x");
        char[] var3 = hexCode.substring(1).toCharArray();

        for (char c : var3) {
            magic.append('§').append(c);
        }
        return magic.toString();
    }
    
    private static String toHexString(int red, int green, int blue) {
        String s = Integer.toHexString((red << 16) + (green << 8) + blue);
        while (s.length() < 6) s = "0" + s;
        return s;
    }
    
    private static String applyFormats(String textInput) {
        String text = textInput;
        text = fixFormat1(text);
        text = fixFormat2(text);
        text = fixFormat3(text);
        text = setGradient1(text);
        text = setGradient2(text);
        return text;
    }
    
    public static String toChatColorString(String textInput) {
        String text = applyFormats(textInput);
        Matcher m = hex.matcher(text);
        while (m.find()) {
            String hexcode = m.group();
            text = text.replace(hexcode, toChatColor(hexcode));
        }
        return text.replace("&", "§");
    }
    private static String fixFormat1(String text) {
        return text.replace("&#", "#");
    }
    private static String fixFormat2(String input) {
        String text = input;
        Matcher m = fix2.matcher(text);
        while (m.find()) {
            String hexcode = m.group();
            String fixed = hexcode.substring(2, 8);
            text = text.replace(hexcode, "#" + fixed);
        }
        return text;
    }
    private static String fixFormat3(String text) {
        text = text.replace('§', '&');
        Matcher m = fix3.matcher(text);
        while (m.find()) {
            String hexcode = m.group();
            String fixed = new String(new char[]{
                    hexcode.charAt(3), hexcode.charAt(5), hexcode.charAt(7),
                    hexcode.charAt(9), hexcode.charAt(11), hexcode.charAt(13)
            });
            text = text.replace(hexcode, "#" + fixed);
        }
        return text;
    }
    private static String setGradient1(String input) {
        String text = input;
        Matcher m = gradient1.matcher(text);
        while (m.find()) {
            String format = m.group();
            TextColor start = new TextColor(format.substring(2, 8));
            String message = format.substring(9, format.length() - 10);
            TextColor end = new TextColor(format.substring(format.length() - 7, format.length() - 1));
            String applied = asGradient(start, message, end);
            text = text.replace(format, applied);
        }
        return text;
    }
    private static String setGradient2(String input) {
        String text = input;
        Matcher m = gradient2.matcher(text);
        while (m.find()) {
            String format = m.group();
            TextColor start = new TextColor(format.substring(2, 8));
            String message = format.substring(10, format.length() - 10);
            TextColor end = new TextColor(format.substring(format.length() - 8, format.length() - 2));
            String applied = asGradient(start, message, end);
            text = text.replace(format, applied);
        }
        return text;
    }
    private static String asGradient(TextColor start, String text, TextColor end) {
        StringBuilder sb = new StringBuilder();
        int length = text.length();
        for (int i = 0; i < length; i++) {
            int red = (int) (start.getRed() + (float) (end.getRed() - start.getRed()) / (length - 1) * i);
            int green = (int) (start.getGreen() + (float) (end.getGreen() - start.getGreen()) / (length - 1) * i);
            int blue = (int) (start.getBlue() + (float) (end.getBlue() - start.getBlue()) / (length - 1) * i);
            sb.append("#").append(toHexString(red, green, blue)).append(text.charAt(i));
        }
        return sb.toString();
    }
}