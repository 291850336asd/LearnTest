package com.meng.example.agentadvance.common;

import java.util.regex.Pattern;

public class WildcardMatcher {
    private final Pattern pattern;

    public WildcardMatcher(String expression) {
        String[] parts = expression.split("&");
        StringBuilder regex = new StringBuilder(expression.length() * 2);
        boolean next = false;
        String[] arr$ = parts;
        int len$ = parts.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            String part = arr$[i$];
            if (next) {
                regex.append('|');
            }

            regex.append('(').append(toRegex(part)).append(')');
            next = true;
        }

        this.pattern = Pattern.compile(regex.toString());
    }

    private static CharSequence toRegex(String expression) {
        StringBuilder regex = new StringBuilder(expression.length() * 2);
        char[] arr$ = expression.toCharArray();
        int len$ = arr$.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            char c = arr$[i$];
            switch(c) {
                case '*':
                    regex.append(".*");
                    break;
                case '?':
                    regex.append(".");
                    break;
                default:
                    regex.append(Pattern.quote(String.valueOf(c)));
            }
        }

        return regex;
    }

    public boolean matches(String s) {
        return this.pattern.matcher(s).matches();
    }
}
