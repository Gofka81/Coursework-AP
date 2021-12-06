package com.example.courseworkap.manager;

import com.example.courseworkap.entity.Disk;
import com.example.courseworkap.entity.music.Music;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.adapter.JavaBeanIntegerProperty;
import javafx.beans.value.ObservableValue;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBManager {

    private static DBManager dbManager;
    private Connection con;
    private static final Logger logger = Logger.getAnonymousLogger();

    private DBManager() {
        try {
            con = DriverManager.getConnection(getURL());
        } catch (SQLException e) {
            logger.log(Level.SEVERE,e.getMessage());
        }
    }

    private String getURL() {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("app.properties")){

            properties.load(fis);
        } catch (IOException e) {
            logger.log(Level.SEVERE,e.getMessage());
        }
        return properties.getProperty("connection.url");
    }

    public static DBManager getInstance() {
        if(dbManager == null){
            dbManager = new DBManager();
        }
        return dbManager;
    }

    public Connection getConnection(String connectionUrl){
        try {
            con = DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            logger.log(Level.SEVERE,e.getMessage());
        }
        return con;
    }

    public void insertMusic(Music music, int disk)  {
        ResultSet rs = null;
        try(PreparedStatement pst = con.prepareStatement("INSERT INTO  mpdb.music_disk(name, duration, style_id, disk_id) VALUES(?,?,?,?)", Statement.RETURN_GENERATED_KEYS)){
            pst.setString(1, music.getName().getValue());
            pst.setInt(2, music.getDuration().getValue());
            pst.setInt(3, MusicManager.genreStringToIntConverter(music.getStyle().getValue()));
            pst.setInt(4, disk);
            pst.execute();
            rs = pst.getGeneratedKeys();
        } catch (SQLException | NullPointerException e) {
            logger.log(Level.SEVERE,e.getMessage());
        }
        finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE,e.getMessage());
                }
            }
        }
    }

    public List<Music> findAllMusic(int disk){
        List<Music> music = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement pst = con.prepareStatement("SELECT id, name, duration, style_id FROM mpdb.music_disk WHERE disk_id = (?) ORDER BY id",Statement.RETURN_GENERATED_KEYS)){
            pst.setInt(1, disk);
            rs = pst.executeQuery();

            while (rs.next()){
                music.add(MusicManager.getCreatedClass(rs.getInt("id"),new SimpleStringProperty(rs.getString("name")),
                        (ObservableValue) new SimpleIntegerProperty(rs.getInt("duration")),
                        rs.getInt("style_id")));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE,e.getMessage());
        }finally {
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE,e.getMessage());
                }
            }
        }
        return music;
    }

    public List<Music> findAllMusicByStyle(int disk){
        List<Music> music = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement pst = con.prepareStatement("SELECT id, name, duration, style_id FROM mpdb.music_disk WHERE disk_id = (?) ORDER BY style_id",Statement.RETURN_GENERATED_KEYS)){
            pst.setInt(1, disk);
            rs = pst.executeQuery();

            while (rs.next()){
                music.add(MusicManager.getCreatedClass(rs.getInt("id"),new SimpleStringProperty(rs.getString("name")),
                        (ObservableValue) new SimpleIntegerProperty(rs.getInt("duration")),
                        rs.getInt("style_id")));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE,e.getMessage());
        }finally {
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE,e.getMessage());
                }
            }
        }
        return music;
    }

    public List<Music> findAllMusicInRange(int min, int max){
        List<Music> music = new ArrayList<>();
        ResultSet rs = null;
        try (PreparedStatement pst = con.prepareStatement("SELECT id, name, duration, style_id FROM mpdb.music_disk WHERE duration BETWEEN (?) AND (?)")){
            pst.setInt(1, min);
            pst.setInt(2, max);
            rs = pst.executeQuery();

            while (rs.next()){
                music.add(MusicManager.getCreatedClass(rs.getInt("id"),new SimpleStringProperty(rs.getString("name")),
                        (ObservableValue) new SimpleIntegerProperty(rs.getInt("duration")),
                        rs.getInt("style_id")));
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE,e.getMessage());
        }finally {
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE,e.getMessage());
                }
            }
        }
        return music;
    }

    public void deleteMusic(Music music){
        try(PreparedStatement pst = con.prepareStatement("DELETE FROM mpdb.music_disk WHERE name = (?) AND duration = (?) AND style_id = (?)")){
            pst.setString(1,music.getName().getValue());
            pst.setInt(2,music.getDuration().getValue());
            pst.setInt(3,MusicManager.genreStringToIntConverter(music.getStyle().getValue()));
            pst.execute();
        }catch (SQLException | NullPointerException ex){
            logger.log(Level.SEVERE,ex.getMessage());
        }
    }

    public void insertDisk(Disk disk)  {
        ResultSet rs = null;
        try(PreparedStatement pst = con.prepareStatement("INSERT INTO  mpdb.user_disks(name) VALUES(?)", Statement.RETURN_GENERATED_KEYS)){
            pst.setString(1, disk.getName());
            pst.execute();
            rs = pst.getGeneratedKeys();
        } catch (SQLException | NullPointerException e) {
            logger.log(Level.SEVERE,e.getMessage());
        }
        finally {
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE,e.getMessage());
                }
            }
        }
    }

    public List<Disk> findAllDisks() {
        List<Disk> disks = new ArrayList<>();
        ResultSet rs = null;
        try (Statement st = con.createStatement()){
            rs = st.executeQuery("SELECT id, name FROM mpdb.user_disks");
            while (rs.next()){
                disks.add(new Disk(rs.getInt("id"),rs.getString("name")));
            }
        } catch (SQLException | NullPointerException e) {
            logger.log(Level.SEVERE,e.getMessage());
        }
        finally {
            try {
                if(rs != null)
                    rs.close();
            } catch (SQLException e) {
                logger.log(Level.SEVERE,e.getMessage());
            }
        }
        return disks;
    }


    public void deleteDisk(int disk){
        try(PreparedStatement pst = con.prepareStatement("DELETE FROM mpdb.user_disks WHERE id = (?)")){
            pst.setInt(1,disk);
            pst.execute();
        }catch (SQLException | NullPointerException ex){
            logger.log(Level.SEVERE,ex.getMessage());
        }
    }
    
    public void clearDisk(int disk){
        try(PreparedStatement pst = con.prepareStatement("DELETE FROM mpdb.music_disk WHERE disk_id = (?)")){
            pst.setInt(1,disk);
            pst.execute();
        }catch (SQLException | NullPointerException ex){
            logger.log(Level.SEVERE,ex.getMessage());
        }
    }

    public void loadMusicOnDisk(List<Music> musicList){
        try(PreparedStatement pst = con.prepareStatement("INSERT INTO mpdb.music_disk (name, duration, style_id, disk_id) VALUE (?,?,?,?)")){
            con.setAutoCommit(false);
            for(Music music: musicList){
                pst.setString(1,music.getName().getValue());
                pst.setInt(2,music.getDuration().getValue());
                pst.setInt(3, MusicManager.genreStringToIntConverter(music.getStyle().getValue()));
                pst.setInt(4,Menu.getCurrentDisk());
                pst.addBatch();
                pst.executeBatch();
            }
            con.commit();
        }catch (SQLException | NullPointerException ex){
            logger.log(Level.SEVERE,ex.getMessage());
        }
    }

    public int findTheLongest(){
        ResultSet rs = null;
        int max = 0;
        try(PreparedStatement pst = con.prepareStatement("SELECT duration FROM mpdb.music_disk WHERE disk_id = (?) ORDER BY duration DESC LIMIT 1")){
            pst.setInt(1,Menu.getCurrentDisk());
            rs = pst.executeQuery();
            if (rs.next()){
                max = rs.getInt("duration");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE,e.getMessage());
        }finally {
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    logger.log(Level.SEVERE,e.getMessage());
                }
            }
        }
        return max;
    }
}