package gjcm.kxf.wheelview;

import java.util.Calendar;

/**
 * Created by kxf on 2016/12/21.
 */
public class OtherTools {


    public int getYear() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.YEAR);
    }

    public int getMonth() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.MONTH) + 1;
    }

    public int getDay() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.DATE);
    }

    public static void main(String[] arg) {
        Calendar c = Calendar.getInstance();
        int m = (c.get(Calendar.MONTH) + 1);
        int y = c.get(Calendar.YEAR);
        int d = c.get(Calendar.DATE);
        int h = c.get(Calendar.HOUR_OF_DAY);

        int minute = c.get(Calendar.MINUTE);
        System.out.print(y + "年" + m + "月" + d + "日" + h + "时"+minute+"分");


    }
}
