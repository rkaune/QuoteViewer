package quoteviewer;

import java.util.*;
import java.io.*;

public class QuotationEngine {
    private ArrayList <Quote> quotes;
    private int quoteCount, currQuoteNum;

    public QuotationEngine(){
        quoteCount = 0;
        quotes = new ArrayList<Quote>();
    }

    public String viewCurrQuote() {
        return quotes.get(currQuoteNum).getQuote() + "\n" + quotes.get(currQuoteNum).getAuthor();
    }

    public void addQuote(String _Quote, String _Author){
        quotes.add(new Quote(_Quote, _Author));
        quoteCount++;
    }

    public void deleteQuote(int n){
        quotes.remove(n);
        quoteCount--;
    }

    public Quote getQuote(int i){
        currQuoteNum = i;
        return quotes.get(i);
    }

    public int getCurrQuoteNum() {
        return currQuoteNum;
    }

    public void nextQuote() {
        currQuoteNum++;
        if(currQuoteNum >= quoteCount)
            currQuoteNum = 0;
    }

    public void prevQuote() {
        currQuoteNum--;
        if(currQuoteNum < 0)
            currQuoteNum = quoteCount-1;
    }


    public void randQuote() {
        currQuoteNum = (int)(Math.random() * quoteCount);
    }

    public void saveQuotesToFile() throws IOException {
        System.out.println(toString());
        PrintWriter fileOut = new PrintWriter(new FileWriter("quotes.txt"));
        fileOut.println(toString());
        fileOut.close();
    }

    public void findAuthor(String name) {
        currQuoteNum = -1;
        for(int i = 0; i < quoteCount; i++){
            if(quotes.get(i).getAuthor().equalsIgnoreCase(name)){
                currQuoteNum = i;
            }
        }
        if(currQuoteNum == -1){
            System.out.println("ERROR!  Author not found!  Displaying first quote.");
            currQuoteNum = 0;
        }
    }

    public void sortQuotes() {
        selectionSort(quotes);
        //Write the quotes to the file
        try {
            saveQuotesToFile();
        }
        catch(IOException e){
            System.err.println("Error saving to file");
        }
        System.out.println("Sorted and Written to File.");
    }

     public static void selectionSort(ArrayList<Quote> q) {
        int smallest;
        for (int i = 0; i < q.size() - 1; i++) {
            smallest = i;
            //see if there is a smaller number further in the array
            for (int index = i + 1; index < q.size(); index++) {
                if (q.get(index).getAuthor().compareTo(q.get(smallest).getAuthor()) < 0) {
                    swap(q, smallest, index);
                }
            }
        }
    }

    public static void swap(ArrayList<Quote> q, int first, int second) {
        Quote hold = q.get(first);
        q.set(first, q.get(second));
        q.set(second, hold);
    }

    public String toString() {
        String output = "";
        for(int i = 0; i < quoteCount; i++){
            output += quotes.get(i).getQuote() + "\n" + quotes.get(i).getAuthor();
            if(i != (quoteCount - 1))
                output += "\n";
        }
        return output;
    }

    public String toStringWithNumbers() {
        String output = "";
        for(int i = 0; i < quoteCount; i++){
            output += "---Quote #"+i+"---\n" + quotes.get(i).getQuote() + "\n" + quotes.get(i).getAuthor();
            if(i != (quoteCount - 1))
                output += "\n";
        }
        return output;
    }

}
