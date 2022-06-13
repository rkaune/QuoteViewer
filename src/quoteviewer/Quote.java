package quoteviewer;
/**
    Whatever this is...
*/
public class Quote {
    private String msg, author;
    /**
    Constructor...
    */
    public Quote(String _Quote, String _Author){
        msg = _Quote;
        author = _Author;
    }

    public String getQuote() {
        return msg;
    }

    public String getAuthor() {
        return author;
    }

    public void setQuote(String _Quote){
        msg = _Quote;
    }

    public void setAuthor(String _Author) {
        author = _Author;
    }
}
