public class TextRow {
    private class Title {
        Title() {
            visible = false;
        }
        Title(String t, int l) {
            title = t;
            level = l;
            visible = true;
        }
        Title(String t, int l, boolean v) {
            this(t, l);
            visible = v;
        }

        boolean visible;
        String title;
        int level;
    }

    private class Text {
        Text(String la, String pl) {
            latin = la;
            polish = pl;
        }
        Text(String rub, String la, String pl) {
            this(la, pl);
            rubrics = rub;
        }
        Text(String rub, String nig, String la, String pl) {
            this(rub, la, pl);
            nigrics = nig;
        }

        String rubrics;
        String nigrics;
        String latin;
        String polish;
    }

    Title title;
    Text text;

    TextRow(Text t) {
        text = t;
        title = new Title();
    }
    TextRow(Title ttl, Text txt) {
        text = txt;
        title = ttl;
    }
}
