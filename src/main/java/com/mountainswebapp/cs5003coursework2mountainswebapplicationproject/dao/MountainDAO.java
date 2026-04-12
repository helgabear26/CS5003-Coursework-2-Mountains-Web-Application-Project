package com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.dao;

import com.mountainswebapp.cs5003coursework2mountainswebapplicationproject.entities.Mountain;

import java.util.List;

public interface MountainDAO {

    void createMountain(Mountain mountain);

    Mountain getMountainByID(Integer id);

    List<Mountain> getAllMountains();

    List<Mountain> searchMountainsByName(String name);

    List<Mountain> getMountainsByDifficultyRating(String difficultyRating);

    void updateMountain(Mountain mountain);

    void deleteMountain(Integer id);
}



