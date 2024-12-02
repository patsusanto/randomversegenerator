package sqlitetutorial;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            String wanted_verse = "SELECT text FROM KJV_verses WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(wanted_verse);
            ps.setInt(1, desired_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println(rs.getString("text"));
            }
            else {
                System.out.println("No verse found with ID: " + desired_id);
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
