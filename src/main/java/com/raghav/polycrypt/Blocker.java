package com.raghav.polycrypt;

import org.unbescape.json.JsonEscapeType;

import java.util.ArrayList;

public class Blocker {
    static int MAX=test.MAX;
                        //Keys         //Messages
     ArrayList<ArrayList<Integer>[]> generateBlocks(ArrayList<Integer>[] XY,int height) {


         System.out.println(height);

         int width=MAX/height;
         int row=XY[1].size()/ height;


        //Y,X of Each Block;
        ArrayList<ArrayList<Integer>[]> blocks=new ArrayList<>();

        for(int j=0;j<XY[1].size();j++) {
            int rowNumber =j/row;
            int index=j-(row*rowNumber);
            int blockNumber= index /width;


            while (blocks.size() <= blockNumber) {

                ArrayList<Integer>[] block = new ArrayList[2];
                block[0] = new ArrayList<>(); // x
                block[1] = new ArrayList<>(); // y

                blocks.add(block);
            }

            blocks.get(blockNumber)[0].add(XY[0].get(j));

            if(XY[1].get(j)==test.START) {
                blocks.get(blockNumber)[1].add(width);
            }
            else
               blocks.get(blockNumber)[1].add(XY[1].get(j));

        }


        return blocks;


    }

    //a b c * *        1 2 3
    //d e f g h        4 5 6
    //i j k l *        7 8 9
}
