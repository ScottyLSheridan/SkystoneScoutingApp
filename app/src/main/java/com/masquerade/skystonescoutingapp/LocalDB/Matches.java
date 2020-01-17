package com.masquerade.skystonescoutingapp.LocalDB;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "matches")
public class Matches {
    @PrimaryKey
    private int id;
    public String tournament;
    public int matchNum, teamNum, autoSkystoneDelivery,
            autoStoneDelivery, autoFoundationStones, teleStoneDelivery,
            teleFoundationStones, teleSkyscraperHeight, endCappingHeight;
    public boolean autoFoundationMove, autoPark, endCap, endFoundationMove, endPark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTournament() {
        return tournament;
    }

    public void setTournament(String tournament) {
        this.tournament = tournament;
    }

    public int getMatchNum() {
        return matchNum;
    }

    public void setMatchNum(int matchNum) {
        this.matchNum = matchNum;
    }

    public int getTeamNum() {
        return teamNum;
    }

    public void setTeamNum(int teamNum) {
        this.teamNum = teamNum;
    }

    public int getAutoSkystoneDelivery() {
        return autoSkystoneDelivery;
    }

    public void setAutoSkystoneDelivery(int autoSkystoneDelivery) {
        this.autoSkystoneDelivery = autoSkystoneDelivery;
    }

    public int getAutoStoneDelivery() {
        return autoStoneDelivery;
    }

    public void setAutoStoneDelivery(int autoStoneDelivery) {
        this.autoStoneDelivery = autoStoneDelivery;
    }

    public int getAutoFoundationStones() {
        return autoFoundationStones;
    }

    public void setAutoFoundationStones(int autoFoundationStones) {
        this.autoFoundationStones = autoFoundationStones;
    }

    public int getTeleStoneDelivery() {
        return teleStoneDelivery;
    }

    public void setTeleStoneDelivery(int teleStoneDelivery) {
        this.teleStoneDelivery = teleStoneDelivery;
    }

    public int getTeleFoundationStones() {
        return teleFoundationStones;
    }

    public void setTeleFoundationStones(int teleFoundationStones) {
        this.teleFoundationStones = teleFoundationStones;
    }

    public int getTeleSkyscraperHeight() {
        return teleSkyscraperHeight;
    }

    public void setTeleSkyscraperHeight(int teleSkyscraperHeight) {
        this.teleSkyscraperHeight = teleSkyscraperHeight;
    }

    public int getEndCappingHeight() {
        return endCappingHeight;
    }

    public void setEndCappingHeight(int endCappingHeight) {
        this.endCappingHeight = endCappingHeight;
    }

    public boolean isAutoFoundationMove() {
        return autoFoundationMove;
    }

    public void setAutoFoundationMove(boolean autoFoundationMove) {
        this.autoFoundationMove = autoFoundationMove;
    }

    public boolean isAutoPark() {
        return autoPark;
    }

    public void setAutoPark(boolean autoPark) {
        this.autoPark = autoPark;
    }

    public boolean isEndCap() {
        return endCap;
    }

    public void setEndCap(boolean endCap) {
        this.endCap = endCap;
    }

    public boolean isEndFoundationMove() {
        return endFoundationMove;
    }

    public void setEndFoundationMove(boolean endFoundationMove) {
        this.endFoundationMove = endFoundationMove;
    }

    public boolean isEndPark() {
        return endPark;
    }

    public void setEndPark(boolean endPark) {
        this.endPark = endPark;
    }
}
