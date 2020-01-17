package com.masquerade.skystonescoutingapp;

/**
 * Created by Keval Kataria on 9/11/2019
 */
public class MatchModel {
    public String tournament;
    public int matchNum, teamNum, autoSkystoneDelivery,
            autoStoneDelivery, autoFoundationStones, teleStoneDelivery,
            teleFoundationStones, teleSkyscraperHeight, endCappingHeight;
    public boolean autoFoundationMove, autoPark, endCap, endFoundationMove, endPark;

    public MatchModel(String tournament, int matchNum, int teamNum, int autoSkystoneDelivery,
                      int autoStoneDelivery, int autoFoundationStones, int teleStoneDelivery,
                      int teleFoundationStones, int teleSkyscraperHeight, int endCappingHeight,
                      boolean autoFoundationMove, boolean autoPark, boolean endCap,
                      boolean endFoundationMove,boolean endPark) {
    this.tournament = tournament;
    this.matchNum = matchNum;
    this.teamNum = teamNum;
    this.autoSkystoneDelivery = autoSkystoneDelivery;
    this.autoStoneDelivery = autoStoneDelivery;
    this.autoFoundationStones = autoFoundationStones;
    this.teleStoneDelivery = teleStoneDelivery;
    this.teleFoundationStones = teleFoundationStones;
    this.teleSkyscraperHeight = teleSkyscraperHeight;
    this.endCappingHeight = endCappingHeight;
    this.autoFoundationMove = autoFoundationMove;
    this.autoPark = autoPark;
    this.endCap = endCap;
    this.endFoundationMove = endFoundationMove;
    this.endPark = endPark;
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
