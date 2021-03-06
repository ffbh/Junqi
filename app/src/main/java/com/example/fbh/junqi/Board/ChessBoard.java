package com.example.fbh.junqi.Board;

import android.graphics.Color;
import android.util.Log;
import android.util.Pair;
import android.widget.Button;

import com.example.fbh.junqi.RankActivity;
import com.example.fbh.junqi.StartActivity;
import com.example.fbh.junqi.file.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

public class ChessBoard {
    public static int Ascore,Bscore;
    public static Chess[][] chessBoard = new Chess[13][5];
    private static ArrayList<Pair<Integer,Integer>>[][] VI = new ArrayList[13][5];
    private static boolean BoardMap[][] = new boolean[13][5];
    private static Pair dir[]={new Pair(1,0),new Pair(0,1),new Pair(-1,0),new Pair(0,-1),
            new Pair(1,1),new Pair(1,-1),new Pair(-1,1),new Pair(-1,-1)};
    private static boolean flag = false;
    public static int m1 = Color.parseColor("#CDAD00");
    public static int m2 = Color.parseColor("#B3EE3A");
    public static Chess old = null;
    public static Pair<Integer,Integer> old_pos;
    public static Stack<Pair<Pair<Integer,Integer>,Chess>> V = new Stack<>();

    public static String[] A,B;
    public static ArrayList<Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>> op = new ArrayList<>();

    static {
        for(int i=0;i<13;++i)
            for(int j=0;j<5;++j)
                VI[i][j] = new ArrayList<>();



        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 5; ++j) {
                if (i == 2 && j == 1 || i == 2 && j == 3 || i == 4 && j == 1 || i == 4 && j == 3 || i == 3 && j == 2) {
                    for (int k = 0; k < 8; ++k) {
                        int nk = i + (Integer)dir[k].first;
                        int mk = j + (Integer)dir[k].second;
                        if(IN(new Pair(nk,mk))) {
                            VI[i][j].add(new Pair<Integer, Integer>(nk, mk));
                            VI[nk][mk].add(new Pair<Integer, Integer>(i, j));
                        }
                    }
                } else if (i == 0 && (j == 1 || j == 3)) {

                } else {
                    for (int k = 0; k < 4; ++k) {
                        int nk = i + (Integer)dir[k].first;
                        int mk = j + (Integer)dir[k].second;
                        if(IN(new Pair(nk,mk))) {
                            VI[i][j].add(new Pair<Integer, Integer>(nk, mk));
                            VI[nk][mk].add(new Pair<Integer, Integer>(i, j));
                        }
                    }
                }
            }
        }



        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 5; ++j) {
                if (i == 1 && j == 1 || i == 1 && j == 3 || i == 3 && j == 1 || i == 3 && j == 3 || i == 2 && j == 2) {
                    for (int k = 0; k < 8; ++k) {

                        int nk = 7 + i + (Integer)dir[k].first;
                        int mk = j + (Integer)dir[k].second;
                        if(IN(new Pair(nk,mk))) {
                            VI[7+i][j].add(new Pair<Integer, Integer>(nk, mk));
                            VI[nk][mk].add(new Pair<Integer, Integer>(7 + i, j));
                        }


                    }
                } else if (i == 5 && (j == 1 || j == 3)) {

                } else {
                    for (int k = 0; k < 4; ++k) {

                        int nk = 7 + i + (Integer)dir[k].first;
                        int mk = j + (Integer)dir[k].second;
                        if(IN(new Pair(nk,mk))) {
                            VI[7+i][j].add(new Pair<Integer, Integer>(nk, mk));
                            VI[nk][mk].add(new Pair<Integer, Integer>(7 + i, j));
                        }
                    }
                }
            }
        }

        for (int i = 0; i < 5; ++i) {
            if(i==1||i==3){
                VI[6][i].clear();
                VI[6][i].add(new Pair(6,i-1));
                VI[6][i].add(new Pair(6,i+1));
            }
            else {
                for (int j = 0; j < 4; ++j) {
                    int nk = 6 + i + (Integer) dir[j].first;
                    int mk = j + (Integer) dir[j].second;
                    if (IN(new Pair(nk, mk))) {
                        VI[6+  i][j].add(new Pair<Integer, Integer>(nk, mk));
                        VI[nk][mk].add(new Pair<Integer, Integer>(6 + i, j));
                    }
                }
            }
        }


        for (int i = 0; i < 13; ++i)
            for (int j = 0; j < 5; ++j)
                BoardMap[i][j] = false;

        for(int k=0;k<5;++k){
            BoardMap[1][k] = BoardMap[5][k] = BoardMap[6][k] =
                    BoardMap[7][k] = BoardMap[11][k] = true;
        }
        for(int k=1;k<=11;++k)
            BoardMap[k][0] = BoardMap[k][4] = true;

//
//        Log.e("size",VI[6][2].size()+"");
//        Log.e("val",VI[6][2].toString());


    }

    static boolean vis[][];
    private static boolean IN(Pair p){
        return (Integer)p.first>=0&&(Integer)p.first<13
                &&(Integer)p.second>=0&&(Integer)p.second<5;
    }
    private static boolean dfs(Pair p,int ex,int ey){

        if(p.first.equals(ex) && p.second.equals(ey)){
            return true;
        }

        if(!IN(p) || !BoardMap[(Integer)p.first][(Integer)p.second]){
            Log.e("fail1",p.toString());
            return false;
        }

        if(vis[(Integer)p.first][(Integer)p.second] ||!chessBoard[(Integer)p.first][(Integer)p.second].getName().equals("")) {
            Log.e("fail2",p.toString());
            return false;
        }
        Log.e("pos",p.toString());
        vis[(Integer)p.first][(Integer)p.second] = true;
        for(Pair son : VI[(Integer)p.first][(Integer)p.second]){
            if(dfs(son,ex,ey))
                return true;
        }
        return false;
    }

    public static boolean getFlag(){
        return flag;
    }

    private static boolean can_move(int sx,int sy,int ex,int ey,boolean turn){
        boolean ok = false;
        Log.e("edge size",VI[sx][sy].size()+" ");
        for(Pair p : VI[sx][sy]){
        //    Log.e("vetes",((Integer)p.first+sx)+" "+((Integer)p.second+sy));
            if(((Integer)p.first) == (ex) && ((Integer)p.second)==(ey)) {
                ok = true;
            }
        }
        if(BoardMap[sx][sy]){
            if(turn){
                Log.e("start_dfs",sx+" "+sy+" "+ex+" "+ey);
                vis = new boolean[13][5];
                String t = chessBoard[sx][sy].getName();
                chessBoard[sx][sy].setName("");
                boolean aaa =  dfs(new Pair(sx,sy),ex,ey);
                chessBoard[sx][sy].setName(t);
                return aaa;
            }
            else{
                if(sx==ex) {
                    ok = true;
                    int minx = Math.min(sy,ey);
                    int maxx = Math.max(sy,ey);
                    for(int k=minx+1;k<maxx;++k){
                        if(BoardMap[sx][k] && chessBoard[sx][k].getName().equals("")){

                        }
                        else{
                            ok = false;
                            break;
                        }
                    }
                }
                else if(sy == ey&&(sy==0||sy==2||sy==4)) {
                    ok = true;
                    int minx = Math.min(sx,ex);
                    int maxx = Math.max(sx,ex);
                    for(int k=minx+1;k<maxx;++k){
                        if(BoardMap[k][sy] && chessBoard[k][sy].getName().equals("")){

                        }
                        else{
                            ok = false;
                            break;
                        }
                    }
                }
            }
        }

        return ok;
    }


    public static boolean click(Pair p){
        int now = m2;
        if(flag){
            now = m1;
        }
        Chess chess = chessBoard[(Integer)p.first][(Integer)p.second];
        if(chess.getColor() == now && chess.canMove()){
            old = chess;
            old_pos = p;
            return true;
        }
        else{
            return false;
        }

    }


    public static Pair<Boolean,Integer> move(Pair p){
        Chess click = chessBoard[(Integer)p.first][(Integer)p.second];
        Boolean v = false;
        int k = 0;
        if(click.getColor() == old.getColor()){
            return new Pair<>(false,0);
        }
        else if(click.getName().equals("")){
            k = 1;
        }
        else{
            k = old.attack(click);
        }
        int sx = old_pos.first;
        int sy = old_pos.second;
        int ex = (Integer)p.first;
        int ey = (Integer)p.second;
        boolean turn = false;
        if(old.getName().equals("工兵")){
            turn = true;
        }
        Log.e("judge_start","canmove");
        v = can_move(sx,sy,ex,ey,turn);
        Log.e("judge_end","canmove");
        if(v){
            V.add(new Pair(new Pair(old_pos.first,old_pos.second), new Chess(old.getName(),old.getColor())));
            V.add(new Pair(new Pair(p.first,p.second),new Chess(click.getName(),click.getColor())));
            op.add(new Pair(new Pair(old_pos.first,old_pos.second),new Pair(p.first,p.second)));
            if(k==1){
                if(flag){
                    Ascore += Chess.getScore(click.getName());
                }
                else{
                    Bscore += Chess.getScore(click.getName());
                }
                click.change(old);
                old.dead();



            }
            else if(k==0){
                click.dead();
                old.dead();
                if(flag){
                    Ascore += Chess.getScore(click.getName());
                    Bscore += Chess.getScore(old.getName());
                }
                else{
                    Bscore += Chess.getScore(click.getName());
                    Ascore += Chess.getScore(old.getName());
                }
            }
            else{
                if(flag){
                    Bscore += Chess.getScore(old.getName());
                }
                else{
                    Ascore += Chess.getScore(old.getName());
                }
                old.dead();
            }


            next_round();
        }



        return new Pair<>(v,k);
    }



    private static void next_round(){
        flag = !flag;
        Log.e("next_round",flag + "");
    }

    public static void Init(String[] ch1,String[] ch2){
        A = ch1;
        B = ch2;
        op.clear();

        for(int i=0;i<6;++i)
            for(int j=0;j<5;++j)
            chessBoard[i][j] = new Chess(ch1[i*5+j],m2);
        for(int i=0;i<5;++i)
            chessBoard[6][i] = new Chess("",0);
        for(int i=0;i<6;++i)
            for(int j=0;j<5;++j)
                chessBoard[7+i][j] = new Chess(ch2[i*5+j],m1);

        flag = true;
        V.clear();

    }

    public static boolean goBack(){
        if(V.empty())
            return false;
        Pair<Pair<Integer,Integer>,Chess> a = V.pop();
        Pair<Pair<Integer,Integer>,Chess> b = V.pop();
        StartActivity.mapAll.setChess(a.first,a.second);
        StartActivity.mapAll.setChess(b.first,b.second);
        chessBoard[a.first.first][a.first.second].change(a.second);
        chessBoard[b.first.first][b.first.second].change(b.second);
        op.remove(op.size()-1);
        next_round();
        return true;
    }


    public static void saveToFile(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String name = sdf.format(new Date());

        String all = "";
        for(String s : A){
            if(s.length() != 0){
                all += s;
            }
            else{
                all += "空空";
            }
            all+=" ";
        }
        for(String s : B){
            if(s.length() != 0){
                all += s;
            }
            else{
                all += "空空";
            }
            all+=" ";
        }

        for(Pair<Pair<Integer,Integer>,Pair<Integer,Integer>> p : op){
            all += p.first.first +" "+p.first.second +" "+p.second.first +" "+p.second.second+" ";
        }

        File file = new File("/storage/emulated/0/junqi/replay/"+name);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);

            Util.Write(fos,all);

        } catch (FileNotFoundException e) {
            Log.e("file_error",e.toString());
            e.printStackTrace();
        }

        saveRank();
    }


    public static void replay_Init(String s){
        String[] ch1 = new String[30];
        String[] ch2 = new String[30];
        Scanner sc = new Scanner(s);
        for(int i=0;i<30;++i){
            ch1[i] = sc.next();
        }

        for(int i=0;i<30;++i){
            ch2[i] = sc.next();
        }

        Init(ch1,ch2);
    }

    public static void saveRank(){
        int score = 0;
        if(!flag){
            score = Ascore;
        }
        else {
            score = Bscore;
        }
        String ans = null;

        try {
            FileInputStream fis = new FileInputStream(RankActivity.path);
            //      ans = Util.Read(openFileInput(path));
            ans = Util.Read(fis);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Log.e("gameend","score:"+score);

        String[] scores = ans.split("\\s+");
        ArrayList<Integer> ss = new ArrayList<>();
        for(int i=0;i<scores.length;++i){
            if(scores[i].trim().length() != 0)
                ss.add(Integer.parseInt(scores[i]));
            else
                ss.add(0);
        }
        ss.add(score);
        while(ss.size()<4)
            ss.add(0);
        Collections.sort(ss);

        int len = ss.size();
        String fff = "";
        for(int i=-1;i>=-4;--i)
            fff = fff+" "+ss.get(len + i);

        try {
            FileOutputStream fos = new FileOutputStream(RankActivity.path);
            Log.e("score",""+fff);
            Util.Write(fos,fff);

        } catch (FileNotFoundException e) {
            Log.e("file_error",e.toString());
            e.printStackTrace();
        }
    }














}
