package sqlitetutorial;

import java.sql.*;

public class Main {
    public static int randomNumberGenerator(int min, int max)
    {
        double r = Math.random();
        return (int)(r * (max - min)) + min;
    }

    //create function that randomizes id
    public static void randomVerse(Connection con) {
        //random id number
        int total_no_verses = 217714;
        int desired_id = randomNumberGenerator(0, total_no_verses);
        try {
            String wanted_verse = "SELECT name, chapter, verse, text FROM bible WHERE verse_id = ?";
            PreparedStatement ps = con.prepareStatement(wanted_verse);
            ps.setInt(1, desired_id);
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            String book = "";
            int chapter = 0;
            int verseNumber = 0;
            String verse = "";
            while (rs.next()) {
                book = rs.getString("name");
                chapter = rs.getInt("chapter");
                verseNumber = rs.getInt("verse");
                verse = rs.getString("text");

            }
            String result = String.format("\"%s\" %s %d:%d", verse, book, chapter, verseNumber);
            System.out.println(result);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }


    }

    public static void main(String[] args) {
        //get connection
        Connection con = ConnectionManager.getConnection();

        Main.randomVerse(con);

    }
}
