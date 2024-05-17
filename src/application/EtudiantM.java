package application;

import ma.projet.connexion.Connexion;
import application.Etudiant;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtudiantM {

    public boolean create(Etudiant o) {
        try {
            String query = "INSERT INTO Etudiant (nom, prenom, sexe, filiere) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = Connexion.getCn().prepareStatement(query);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getPrenom());
            ps.setString(3, o.getSexe());
            ps.setString(4, o.getFiliere());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(Etudiant o) {
        try {
            String query = "DELETE FROM Etudiant WHERE id = ?";
            PreparedStatement ps = Connexion.getCn().prepareStatement(query);
            ps.setInt(1, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(Etudiant o) {
        try {
            String query = "UPDATE Etudiant SET nom = ?, prenom = ?, sexe = ?, filiere = ? WHERE id = ?";
            PreparedStatement ps = Connexion.getCn().prepareStatement(query);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getPrenom());
            ps.setString(3, o.getSexe());
            ps.setString(4, o.getFiliere());
            ps.setInt(5, o.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Etudiant findById(int id) {
        try {
            String query = "SELECT * FROM Etudiant WHERE id = ?";
            PreparedStatement ps = Connexion.getCn().prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Etudiant(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("sexe"),
                    rs.getString("filiere")
                );
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Etudiant> findAll() {
        try {
            String query = "SELECT * FROM Etudiant";
            PreparedStatement ps = Connexion.getCn().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            List<Etudiant> etudiants = new ArrayList<>();
            while (rs.next()) {
                etudiants.add(new Etudiant(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("sexe"),
                    rs.getString("filiere")
                ));
            }
            return etudiants;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
