package com.example.fbh.junqi.view;

public class Chess {
    private String name;
    private int color;
    private boolean can_move;


    public Chess(String name, int color) {
        this.name = name;
        this.color = color;
        this.can_move = true;
        if(name.equals("地雷") || name.equals("军旗")){
            can_move = false;
        }
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public boolean canMove() {
        return can_move;
    }




}
