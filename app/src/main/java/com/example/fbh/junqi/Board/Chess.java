package com.example.fbh.junqi.Board;

import javax.crypto.interfaces.DHPublicKey;
import java.util.HashMap;
import java.util.Map;

public class Chess {
    private String name;
    private int color;
    private boolean can_move;

    private static Map<String,Integer> H = new HashMap<>();

    static {
        H.put("",0);
        H.put("军旗",0);
        H.put("地雷",1);
        H.put("工兵",2);
        H.put("排长",3);
        H.put("连长",4);
        H.put("营长",5);
        H.put("团长",6);
        H.put("旅长",7);
        H.put("师长",8);
        H.put("军长",9);
        H.put("司令",10);
    }

    public static int getScore(String name){
        if(H.containsKey(name)){
            return H.get(name);
        }
        else{
            return 0;
        }
    }

    public Chess(String name, int color) {
        this.name = name;
        this.color = color;
        this.can_move = true;
        if(name.equals("地雷") || name.equals("军旗")){
            can_move = false;
        }
        if(name.equals("空空") || name.equals("")){
            this.name = "";
            this.color = -1;
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getColor() {
        return color;
    }

    public boolean canMove() {
        return can_move&&!name.equals("");
    }


    int attack(Chess other){
        if(name.equals(other.name)||name.equals("炸弹")||other.name.equals("炸弹")){
            return 0;
        }
        if(other.name.equals("地雷")){
            if(name.equals("工兵")){
                return 1;
            }
            else{
                return -1;
            }
        }
        return ((Integer)H.get(name)).compareTo(((Integer)H.get(other.name)));
    }

    void dead(){
        name = "";
        color = -1;
    }

    void change(Chess chess){
        this.name = chess.name;
        this.color = chess.color;
        this.can_move = chess.can_move;
    }

    @Override
    public String toString() {
        return "Chess{" +
                "name='" + name + '\'' +
                ", color=" + color +
                ", can_move=" + can_move +
                '}';
    }
}
