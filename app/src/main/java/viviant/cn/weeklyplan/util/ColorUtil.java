package viviant.cn.weeklyplan.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by weiwei.huang on 16-6-1.
 */
public class ColorUtil {
    public static String getRandomColor () {
        List<String> colorList = new ArrayList<String>();

        colorList.add("#303F9F");
        colorList.add("#FF4081");
        colorList.add("#59dbe0");
        colorList.add("#f57f68");
        colorList.add("#87d288");
        colorList.add("#f8b552");
        colorList.add("#990099");
        colorList.add("#90a4ae");
        colorList.add("#7baaf7");
        colorList.add("#4dd0e1");
        colorList.add("#4db6ac");
        colorList.add("#aed581");
        colorList.add("#fdd835");
        colorList.add("#f2a600");
        colorList.add("#ff8a65");
        colorList.add("#f48fb1");
        colorList.add("#7986cb");

        Random rand = new Random();
        int i = rand.nextInt(); //int范围类的随机数
        i = rand.nextInt(16); //生成0-100以内的随机数

        i = (int)(Math.random() * 16); //0-100以内的随机数，用Matn.random()方式

        return colorList.get(i);
    }
}
