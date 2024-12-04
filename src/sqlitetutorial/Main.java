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
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }


    }

    public static void main(String[] args) {
        //ask for a verse
        Connection con = ConnectionManager.getConnection();

        //ask for new verse
        System.out.println("Would you like to get a random bible verse");
        Main.randomVerse(con);

    }
}
