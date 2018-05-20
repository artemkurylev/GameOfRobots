/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameofrobots.fileRead;

import gameofrobots.Model.FieldDescription;
import gameofrobots.Model.pair;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author 1
 */
public class FileReader {
    public static FieldDescription readField() throws FileNotFoundException{
         	
        Scanner in = new Scanner(new File("Fields\\FieldOne.txt"));
        FieldDescription F = new FieldDescription();
        F.size = new pair(in.nextInt(),in.nextInt()); 
        F.targetPos = new pair(in.nextInt(),in.nextInt());
        F.SmallRobotPos = new pair(in.nextInt(),in.nextInt());
        F.BigRobotPos = new pair(in.nextInt(),in.nextInt());
        F.WallsPos = new ArrayList<>();
        F.WallDirections = new ArrayList<>();
        int WallsCount = in.nextInt();
        for(int i = 0; i < WallsCount; ++i){
            F.WallsPos.add(new pair(in.nextInt(),in.nextInt()));
            F.WallDirections.add(in.nextInt());
        }
        int BogsCount = in.nextInt();
        F.Bogs = new ArrayList<>();
        for(int i = 0; i < BogsCount; ++i){
            F.Bogs.add(new pair(in.nextInt(),in.nextInt()));
        }
        return F;
    }
}
