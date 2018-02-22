package jp.palamfairy.project.android.palamfairygame;

import java.util.ArrayList;

/**
 * Created by appu2 on 2018/01/20.
 */

public class ToileRoop {
    private int mCount;
    private ArrayList<Integer> roopArrayList= new ArrayList<Integer>();

    int roop;
    public void setRoopList(int count){
        mCount =count;
        roopArrayList.add(mCount);
    }
    public ArrayList<Integer> getRoopList(){
        return roopArrayList;
    }
}
