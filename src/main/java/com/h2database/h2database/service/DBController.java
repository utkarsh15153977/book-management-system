package com.example.DatabaseProject.controller;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // allow React
public class DBController {

    Connection con;
    PreparedStatement ps;
    String sql;

    // ================= District =====================
    @GetMapping("/dist")
    public String getDistricts() {
        ResultSet rs;
        JSONArray districtList = new JSONArray();
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "newpassword");

            sql = "SELECT distcode, name FROM district";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                JSONObject json = new JSONObject();
                json.put("districtcode", rs.getString("distcode").trim());
                json.put("districtname", rs.getString("name").trim());
                districtList.add(json);
            }

            close(con, ps, rs);
        } catch (Exception e) {
            return "{\"error\":\"District Exception ==> " + e.getMessage() + "\"}";
        }
        return districtList.toString();
    }

    // ================= Taluk =====================
//    @GetMapping("/taluk")
//    public String getTaluks(@RequestParam(name = "distCode") String distCode) {
//        ResultSet rs;
//        JSONArray talukList = new JSONArray();
//        try {
//            Class.forName("org.postgresql.Driver");
//            con = DriverManager.getConnection(
//                    "jdbc:postgresql://localhost:5432/postgres",
//                    "postgres", "newpassword");
//
//            // adjust 'name' or 'tname' depending on DB schema
//            sql = "SELECT talukcode, name FROM taluk WHERE distcode=?";
//            ps = con.prepareStatement(sql);
//            ps.setString(1, distCode.trim());
//            rs = ps.executeQuery();
//
//            while (rs.next()) {
//                JSONObject json = new JSONObject();
//                json.put("districtcode", distCode);
//                json.put("talukcode", rs.getString("talukcode").trim());
//                json.put("talukname", rs.getString("name").trim());
//                talukList.add(json);
//            }
//            close(con, ps, rs);
//        } catch (Exception e) {
//            return "{\"error\":\"Taluk Exception ==> " + e.getMessage() + "\"}";
//        }
//        return talukList.toString();
//    }

    @GetMapping("/taluk")
    public String getTaluks(@RequestParam String distCode) {
        ResultSet rs;
        JSONArray talukList = new JSONArray();
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "newpassword");

            // adjust 'name' or 'tname' depending on DB schema
            sql = "SELECT talukcode, name FROM taluk WHERE distcode=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, distCode.trim());
            rs = ps.executeQuery();

            while (rs.next()) {
                JSONObject json = new JSONObject();
                json.put("districtcode", distCode);
                json.put("talukcode", rs.getString("talukcode").trim());
                json.put("talukname", rs.getString("name").trim());
                talukList.add(json);
            }
            close(con, ps, rs);
        } catch (Exception e) {
            return "{\"error\":\"Taluk Exception ==> " + e.getMessage() + "\"}";
        }
        return talukList.toString();
    }



    // ================= Village =====================
    @GetMapping("/village")
    public String getVillages(@RequestParam(name = "distCode") String distCode,
                              @RequestParam(name = "talukCode") String talukCode) {
        ResultSet rs;
        JSONArray villageList = new JSONArray();
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres", "newpassword");

            sql = "SELECT villagecode, vname FROM village WHERE distcode=? AND talukcode=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, distCode);
            ps.setString(2, talukCode);
            rs = ps.executeQuery();

            while (rs.next()) {
                JSONObject json = new JSONObject();
                json.put("districtcode", distCode);
                json.put("talukcode", talukCode);
                json.put("villagecode", rs.getString("villagecode").trim());
                json.put("villagename", rs.getString("vname").trim()); // ✅ correct column
                villageList.add(json);
            }

            close(con, ps, rs);
        } catch (Exception e) {
            return "{\"error\":\"Village Exception ==> " + e.getMessage() + "\"}";
        }
        return villageList.toString();
    }

    // ================= Utility =====================
    private static void close(Connection myConn, Statement myStmt, ResultSet myRs) {
        try {
            if (myRs != null) myRs.close();
            if (myStmt != null) myStmt.close();
            if (myConn != null) myConn.close();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
