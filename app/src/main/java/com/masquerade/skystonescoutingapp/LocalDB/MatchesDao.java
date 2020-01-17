package com.masquerade.skystonescoutingapp.LocalDB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MatchesDao {

    @Insert
    void addMatch(Matches matches);

    @Query("SELECT * FROM Matches")
    List<Matches> getAll();

    @Query("Select * FROM Matches WHERE :matchNumber = matchNumber")
    List<Matches> getMatch(int matchNumber);

    @Update
    void updateMatch(Matches matches);

    @Delete
    void deleteMatch(Matches matches);

}
