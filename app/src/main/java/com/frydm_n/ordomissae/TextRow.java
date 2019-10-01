package com.frydm_n.ordomissae;

public class TextRow {
    private String title = "";
    private int level = 0;
    private String rubrics = "";
    private String latin = "";
    private String polish = "";

    TextRow(String t) {
        title = t;
    }

    TextRow(String l, String p) {
        latin = l;
        polish = p;
    }

    TextRow(String t, String l, String p) {
        title = t;
        latin = l;
        polish = p;
    }

    TextRow(String t, String r, String l, String p) {
        title = t;
        rubrics = r;
        latin = l;
        polish = p;
    }

    TextRow(String t, int lvl, String r, String l, String p) {
        title = t;
        level = lvl;
        rubrics = r;
        latin = l;
        polish = p;
    }

    String getTitle() {
        if (title.length() > 0) {
            String spaces = new String(new char[2* level]).replace('\0', ' ');
            return spaces + title;
        }
        return "";

    }

    String getRubrics() {
        return rubrics;
    }

    String getLatin() {
        return latin;
    }

    String getPolish() {
        return polish;
    }

    int getLevel() {
        return level;
    }
}
