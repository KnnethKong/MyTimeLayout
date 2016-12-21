package gjcm.kxf.mytimelayout;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private PickView pickYue, pickDay;
    private int datevalue = 31;
    private String slectYue, selectRi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pickDay = (PickView) findViewById(R.id.main_pickview_day);
        pickYue = (PickView) findViewById(R.id.main_pickview_yue);
        Button tbnOk = (Button) findViewById(R.id.main_pickview_ok);
        tbnOk.setOnClickListener(this);
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            list.add("" + i);
        }
        Time time = new Time("GT+8");
        final int year = time.year;
        pickYue.setData(list);
        pickYue.setOnSelectListener(new PickView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                slectYue = text;
                if (text.equals("4") || text.equals("6") || text.equals("8") || text.equals("10")) {
                    datevalue = 30;
                }
                if (text.equals("2")) {
                    if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
                        datevalue = 29;
                    } else {
                        datevalue = 28;
                    }
                }
            }
        });

        pickYue.setSelected(0);
        ArrayList<String> datelist = new ArrayList<>();
        for (int i = 0; i < datevalue; i++) {
            datelist.add(i + "");
        }
        pickDay.setData(datelist);
        pickDay.setOnSelectListener(new PickView.onSelectListener() {
            @Override
            public void onSelect(String text) {
                selectRi = text;
            }
        });

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_pickview_ok:
                TextView txt = (TextView) findViewById(R.id.showmsg);
                txt.setText(slectYue + "  ----   " + selectRi);
                break;
        }

    }
}
