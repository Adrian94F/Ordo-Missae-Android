package com.frydm_n.ordomissae;

public class TextRow {
    boolean isTitleVisible;
    String title;
    int titleLevel = 0;
    String rubrics;
    String nigrics;
    String latin;
    String polish;

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

    TextRow(String t, String r, String n, String l, String p) {
        title = t;
        rubrics = r;
        nigrics = n;
        latin = l;
        polish = p;
    }

    String getTitle() {
        String spaces = new String(new char[titleLevel]).replace('\0', ' ');
        return spaces + title;
    }

    String getRubrics() {
        return rubrics;
    }

    String getNigrics() {
        return nigrics;
    }

    String getLatin() {
        return latin;
    }

    String getRPolish() {
        return polish;
    }
}
