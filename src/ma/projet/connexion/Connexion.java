package ma.projet.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	private static String url = "jdbc:mysql://localhost:3300/demojdbc";
	private static String utilisateur = "root";
	private static String mot_de_passe = "";
    private static Connection connection = null;

    static {
        try {
            connection = DriverManager.getConnection(url, utilisateur, mot_de_passe);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
