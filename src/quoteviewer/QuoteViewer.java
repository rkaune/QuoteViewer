package quoteviewer;

import javax.swing.*;
import java.io.*;

public class QuoteViewer {

    public static void main(String[] args) throws IOException {
        QuotationEngine qe = new QuotationEngine();
        //Load the file into the QuotationEngine
        try {
            BufferedReader readFile = new BufferedReader(new FileReader("quotes.txt"));
            String myLine;

            String q, a;
            while ((myLine = readFile.readLine()) != null) {
                q = myLine;
                a = readFile.readLine();
                qe.addQuote(q, a);
            }
            readFile.close();
        } catch (IOException ioe) {
            System.err.println("File Crashed!");
        }

        JOptionPane.showMessageDialog(null, "Welcome to the quotation database!");

        String choice = "";
        while (!choice.equals("9")) {
            choice = JOptionPane.showInputDialog("Choose from the following menu:\n\n"
                    + "1 - Display current quote\n"
                    + "2 - Next quote\n"
                    + "3 - Previous quote\n"
                    + "4 - Random quote\n"
                    + "5 - Add a quote to the database\n"
                    + "6 - Remove a quote from the database\n"
                    + "7 - Search for an author\n"
                    + "8 - Sort the database by author\n"
                    + "9 - Exit");
            if (choice.equals("1")) {
                JOptionPane.showMessageDialog(null, qe.viewCurrQuote());
            }
            if (choice.equals("2")){
                qe.nextQuote();
                JOptionPane.showMessageDialog(null, qe.viewCurrQuote());
            }
            if (choice.equals("3")){
                qe.prevQuote();
                JOptionPane.showMessageDialog(null, qe.viewCurrQuote());
            }
            if (choice.equals("4")){
                qe.randQuote();
                JOptionPane.showMessageDialog(null, qe.viewCurrQuote());
            }
            if (choice.equals("5")){
                String qtext = JOptionPane.showInputDialog("Enter the quote:");
                String auth = JOptionPane.showInputDialog("Who is the author of the quote : " + qtext + "?");
                qe.addQuote(qtext, auth);
                qe.saveQuotesToFile();
            }
            if (choice.equals("6")) {
                System.out.println("Displaying ALL Quotes in Database");
                System.out.println(qe.toStringWithNumbers());
                int numToDelete = Integer.parseInt(JOptionPane.showInputDialog("Which quote number do you want to delete?"));
                qe.deleteQuote(numToDelete);
                qe.saveQuotesToFile();
            }
            if (choice.equals("7")){
                String auth = JOptionPane.showInputDialog("Which author do you wish to search for?");
                qe.findAuthor(auth);
                JOptionPane.showMessageDialog(null, qe.viewCurrQuote());
            }
            if (choice.equals("8")){
                qe.sortQuotes();
            }
        }
    }
}
