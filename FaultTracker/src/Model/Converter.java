package Model;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Converter {
    public static DateFormat dateFormatter = SimpleDateFormat.getDateTimeInstance();
    public static String export_report(Report report) {
        return String.join(
                "|", report.getType(), report.getTitle(), report.getDescription(),
                report.getContact(), report.getLocation(),
                Integer.toString(report.getUrgency()), Integer.toString(report.getSeverity()),
                dateFormatter.format(report.getDate()));
    }

    public static Report import_report(String string) {
        String[] tokens = string.split("[|]");
        try {
            return new Report(
                    tokens[0], tokens[1], tokens[2], tokens[3], tokens[4],
                    Integer.parseInt(tokens[5]), Integer.parseInt(tokens[6]), dateFormatter.parse(tokens[7]));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static String export_report_tree(RBTree<Report> tree) {
        StringBuilder result = new StringBuilder();
        try {
            for (Node<Report> r : tree.levelOrder()) {
                result = result.append(export_report(r.getItem()));
                result = result.append("\n");
            }
            return result.substring(0, result.length() - 1);
        }
        catch (Exception e){
            return "";
        }
    }


    public static RBTree<Report> import_report_tree(String in) {
        Scanner scanner = new Scanner(in);
        scanner.useDelimiter("\n");
        return import_report_tree(scanner);
    }

    public static RBTree<Report> import_report_tree(Scanner in) {
        RBTree<Report> tree = new RBTree<>();
        while (in.hasNext()) {
            String n = in.next();
            System.out.println(n);
            tree.insert(import_report(n));
        }
        return tree;
    }
}
