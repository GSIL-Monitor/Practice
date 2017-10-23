package thinkinginjava.strings;

/**
 * Created by DreamYao on 2016/7/31.
 * P285 StringBuilder对象
 */
public class WhitherStringBuilder {
    public String implicit(String[] fields) {
        String result = "";
        for (int i=0;i<fields.length;i++) {
            result += fields[i];
        }
        return result;
    }

    public String explicit(String[] fields) {
        StringBuilder result = new StringBuilder();
        for (int i=0;i<fields.length;i++) {
            result.append(fields[i]);
        }
        return result.toString();
    }
}