package com.masquerade.skystonescoutingapp.LocalDB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Matches.class}, version = 1)
public abstract class MatchesDatabase extends RoomDatabase {

    public abstract MatchesDao matchesDao();

}
