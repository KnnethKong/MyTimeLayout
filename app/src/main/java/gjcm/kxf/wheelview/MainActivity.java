package gjcm.kxf.wheelview;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Date;

import gjcm.kxf.mytimelayout.R;

public class MainActivity extends Activity {

    private TextView mDatePick, mTime, mAddress;
    private RadioButton rbnDialogMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wheel_main);
        mDatePick = (TextView) findViewById(R.id.tv_datePicker);
        mTime = (TextView) findViewById(R.id.tv_birth);
        mAddress = (TextView) findViewById(R.id.tv_address);

        rbnDialogMode = (RadioButton) findViewById(R.id.rbnDialogMode);
        rbnDialogMode.setChecked(true);

        mDatePick.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                DatePickerDialog mChangeBirthDialog = new DatePickerDialog(
                        MainActivity.this);
                // mChangeBirthDialog.setDate(2015, 03, 29);
//                mChangeBirthDialog.setDate(2016, 11, 21);
                if (rbnDialogMode.isChecked()) {
                    mChangeBirthDialog
                            .setDialogMode(DatePickerDialog.DIALOG_MODE_BOTTOM);
                }
                mChangeBirthDialog.show();
                mChangeBirthDialog
                        .setDatePickListener(new DatePickerDialog.OnDatePickListener() {

                            @Override
                            public void onClick(String beiginyear, String beiginmonth, String beiginday, String endyear, String endmonth, String endday,String bhour,String endhour) {

                                Toast.makeText(MainActivity.this,
                                        beiginyear + "-" + beiginmonth + "-" + beiginday+":"+bhour+" end:"+endyear+"-"+endmonth+"-"+endday+":"+endhour,
                                        Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });

        mTime.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                TimePickerDialog mTimePickerDialog = new TimePickerDialog(
                        MainActivity.this);
                mTimePickerDialog.setDate(new Date());
                if (rbnDialogMode.isChecked()) {
                    mTimePickerDialog
                            .setDialogMode(TimePickerDialog.DIALOG_MODE_BOTTOM);
                }
                mTimePickerDialog.show();
                mTimePickerDialog.setTimePickListener(new TimePickerDialog.OnTimePickListener() {

                    @Override
                    public void onClick(int year, int month, int day,
                                        String hour, String minute) {
                        Toast.makeText(
                                MainActivity.this,
                                year + "-" + month + "-" + day + " " + hour
                                        + ":" + minute, Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
        });

        mAddress.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                AddressPickerDialog mChangeAddressDialog = new AddressPickerDialog(
                        MainActivity.this);
                mChangeAddressDialog.setAddress("四川", "自贡");
                if (rbnDialogMode.isChecked()) {
                    mChangeAddressDialog
                            .setDialogMode(DatePickerDialog.DIALOG_MODE_BOTTOM);
                }
                mChangeAddressDialog.show();
                mChangeAddressDialog
                        .setAddresskListener(new AddressPickerDialog.OnAddressCListener() {

                            @Override
                            public void onClick(String province, String city) {
                                Toast.makeText(MainActivity.this,
                                        province + "-" + city,
                                        Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }
}
