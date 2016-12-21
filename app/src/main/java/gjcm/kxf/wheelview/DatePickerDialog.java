package gjcm.kxf.wheelview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import gjcm.kxf.mytimelayout.R;
import gjcm.kxf.wheelview.adapters.AbstractWheelTextAdapter;
import gjcm.kxf.wheelview.views.OnWheelChangedListener;
import gjcm.kxf.wheelview.views.OnWheelScrollListener;
import gjcm.kxf.wheelview.views.WheelView;


/**
 * 日期选择对话框
 *
 * @author pengjian
 */
public class DatePickerDialog extends BaseDialog implements
        View.OnClickListener {

    private static final int MIN_YEAR = 2014;
    private static final int MAX_YEAR = 2020;

    public static final int DIALOG_MODE_CENTER = 0;
    public static final int DIALOG_MODE_BOTTOM = 1;
    private Context context;
    private WheelView wvYear;
    private WheelView wvMonth;
    private WheelView wvDay;
    private WheelView beHour;


    private WheelView endYear;
    private WheelView endMonth;
    private WheelView endDay;
    private WheelView endHour;
    private WheelView endOther;


    private View vDialog;
    private View vDialogChild;
    private ViewGroup BeginPicker;//开始时间
    private ViewGroup EndPicker;//结束时间
    private TextView tvTitle;
    private TextView btnSure;
    private TextView btnCancel;
    private ArrayList<String> arry_years = new ArrayList<String>();
    private ArrayList<String> arry_months = new ArrayList<String>();
    private ArrayList<String> arry_days = new ArrayList<String>();
    private ArrayList<String> arry_hour = new ArrayList<String>();
    private CalendarTextAdapter mYearAdapter, endYeayAdapter;
    private CalendarTextAdapter mMonthAdapter, endMonthAdapter;
    private CalendarTextAdapter mDaydapter, endDayAdapter, behourAdapter, endHourAdapter;
    private int month;
    private int day;
    private int currentYear = getYear();
    private int currentMonth = getMonth();
    private int currentDay = getDay();

    private int maxTextSize = 24;
    private int minTextSize = 18;

    private boolean issetdata = false;

    private String selectYear, esYear;
    private String selectMonth, esMonth;
    private String selectDay, esDay, behour, endhour;
    private String strTitle = "开始时间";
    private OnDatePickListener onDatePickListener;
    private int setCount = 3;
    public DatePickerDialog(Context context) {
        super(context, R.layout.dialog_picker_center);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        BeginPicker = (ViewGroup) findViewById(R.id.ly_dialog_picker);
        EndPicker = (ViewGroup) findViewById(R.id.ly_dialog_endpick);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, 1.0f);
        LinearLayout.LayoutParams endlayou = new LinearLayout.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT, 1.0f);
        // 此处相当于布局文件中的Android:layout_gravity属性
        lp.gravity = Gravity.CENTER_VERTICAL;
        endlayou.gravity = Gravity.CENTER_VERTICAL;
        wvYear = new WheelView(context);
        wvYear.setLayoutParams(lp);
        BeginPicker.addView(wvYear);
        TextView ntxt = new TextView(context);
        ntxt.setText("年");
        ntxt.setGravity(Gravity.CENTER_VERTICAL);
        ntxt.setLayoutParams(lp);
        BeginPicker.addView(ntxt);
//        EndPicker.addView(wvYear);
        setHour();
        wvMonth = new WheelView(context);
        wvMonth.setLayoutParams(lp);
        BeginPicker.addView(wvMonth);
        TextView ytxt = new TextView(context);
        ytxt.setText("月");
        ytxt.setGravity(Gravity.CENTER_VERTICAL);
        ytxt.setLayoutParams(lp);
        BeginPicker.addView(ytxt);
        wvDay = new WheelView(context);
        wvDay.setLayoutParams(lp);
        BeginPicker.addView(wvDay);
        TextView rtxt = new TextView(context);
        rtxt.setText("日");
        rtxt.setGravity(Gravity.CENTER_VERTICAL);
        rtxt.setLayoutParams(lp);
        BeginPicker.addView(rtxt);
        beHour = new WheelView(context);
        beHour.setLayoutParams(lp);
        BeginPicker.addView(beHour);
        TextView stxt = new TextView(context);
        stxt.setText("时");
        stxt.setGravity(Gravity.CENTER_VERTICAL);
        stxt.setLayoutParams(lp);
        BeginPicker.addView(stxt);

        ////end------
        endYear = new WheelView(context);
        endYear.setLayoutParams(endlayou);
        EndPicker.addView(endYear);
        ntxt = new TextView(context);
        ntxt.setText("年");
        ntxt.setGravity(Gravity.CENTER_VERTICAL);
        ntxt.setLayoutParams(endlayou);
        EndPicker.addView(ntxt);
        //月
//        endOther= new WheelView(context);
//        endOther.setLayoutParams(endlayou);
//        EndPicker.addView(endOther);
        endMonth = new WheelView(context);
        endMonth.setLayoutParams(endlayou);
        EndPicker.addView(endMonth);
        ytxt = new TextView(context);
        ytxt.setText("月");
        ytxt.setGravity(Gravity.CENTER_VERTICAL);
        ytxt.setLayoutParams(endlayou);
        EndPicker.addView(ytxt);
        //日
        endDay = new WheelView(context);
        endDay.setLayoutParams(endlayou);
        EndPicker.addView(endDay);
        rtxt = new TextView(context);
        rtxt.setText("日");
        rtxt.setGravity(Gravity.CENTER_VERTICAL);
        rtxt.setLayoutParams(endlayou);
        EndPicker.addView(rtxt);
        endHour = new WheelView(context);
        endHour.setLayoutParams(lp);
        EndPicker.addView(endHour);
        stxt = new TextView(context);
        stxt.setText("时");
        stxt.setGravity(Gravity.CENTER_VERTICAL);
        stxt.setLayoutParams(endlayou);
        EndPicker.addView(stxt);
        ///end EndPicker
        vDialog = findViewById(R.id.ly_dialog);//最外层Liner
        vDialogChild = findViewById(R.id.ly_dialog_child);
        tvTitle = (TextView) findViewById(R.id.tv_dialog_title);
        btnSure = (TextView) findViewById(R.id.btn_dialog_sure);
        btnCancel = (TextView) findViewById(R.id.btn_dialog_cancel);
        tvTitle.setText(strTitle);
        vDialog.setOnClickListener(this);
        vDialogChild.setOnClickListener(this);
        btnSure.setOnClickListener(this);
        if (null != btnCancel) {
            btnCancel.setOnClickListener(this);
        }
        if (!issetdata) {////frist go
            initData();
        }
        initYears();
        mYearAdapter = new CalendarTextAdapter(context, arry_years,
                setYear(currentYear), maxTextSize, minTextSize);
        wvYear.setVisibleItems(setCount);
        wvYear.setViewAdapter(mYearAdapter);
        wvYear.setCurrentItem(setYear(currentYear));
//        initYears();
/**
 */endYeayAdapter = new CalendarTextAdapter(context, arry_years,
                setYear(currentYear), maxTextSize, minTextSize);
        endYear.setVisibleItems(setCount);
        endYear.setViewAdapter(endYeayAdapter);
        endYear.setCurrentItem(setYear(currentYear));
        Log.i("kxflog", "currentYear:  " + (currentYear));

        initMonths(month);
        mMonthAdapter = new CalendarTextAdapter(context, arry_months,
                setMonth(currentMonth), maxTextSize, minTextSize);
        wvMonth.setVisibleItems(setCount);
        wvMonth.setCyclic(true);
        wvMonth.setViewAdapter(mMonthAdapter);
        wvMonth.setCurrentItem(setMonth(currentMonth));
        initMonths(month);


        endMonthAdapter = new CalendarTextAdapter(context, arry_months,
                setMonth(currentMonth), maxTextSize, minTextSize);
        endMonth.setVisibleItems(setCount);
        endMonth.setCyclic(true);
        endMonth.setViewAdapter(endMonthAdapter);
        endMonth.setCurrentItem(setMonth(currentMonth));
        initDays(day);
        mDaydapter = new CalendarTextAdapter(context, arry_days,
                currentDay - 1, maxTextSize, minTextSize);
        wvDay.setVisibleItems(setCount);
        wvDay.setCyclic(true);
        wvDay.setViewAdapter(mDaydapter);
        wvDay.setCurrentItem(currentDay - 1);

        initDays(day);
        endDayAdapter = new CalendarTextAdapter(context, arry_days,
                currentDay - 1, maxTextSize, minTextSize);
        endDay.setVisibleItems(setCount);
        endDay.setCyclic(true);
        endDay.setViewAdapter(endDayAdapter);
        endDay.setCurrentItem(currentDay - 1);
//        Log.i("kxflog", "nowHou   `rs: " + nowHours);
////////0--------
        //
        int nowh = getHourss();
        behour = nowh + "";
        endhour = (nowh + 2) + "";
        behourAdapter = new CalendarTextAdapter(context, arry_hour,
                nowh, 24, 0);
        beHour.setVisibleItems(setCount);
        beHour.setCyclic(true);
        beHour.setViewAdapter(behourAdapter);
        beHour.setCurrentItem(nowh);
        endHourAdapter = new CalendarTextAdapter(context, arry_hour, nowh + 2, 24, 0);
        endHour.setVisibleItems(setCount);
        endHour.setCyclic(true);
        endHour.setViewAdapter(endHourAdapter);
        endHour.setCurrentItem(nowh + 2);
        /**listener
         */
        wvYear.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentText = (String) mYearAdapter.getItemText(wheel
                        .getCurrentItem());
                selectYear = currentText;
                setTextviewSize(currentText, mYearAdapter);
                currentYear = Integer.parseInt(currentText);
                setYear(currentYear);
                initMonths(month);
                mMonthAdapter = new CalendarTextAdapter(context, arry_months,
                        0, maxTextSize, minTextSize);
                wvMonth.setVisibleItems(setCount);
                wvMonth.setViewAdapter(mMonthAdapter);
                wvMonth.setCurrentItem(0);
            }
        });

        wvYear.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) mYearAdapter.getItemText(wheel
                        .getCurrentItem());
                setTextviewSize(currentText, mYearAdapter);
            }
        });

        wvMonth.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentText = (String) mMonthAdapter.getItemText(wheel
                        .getCurrentItem());
                selectMonth = currentText;
                setTextviewSize(currentText, mMonthAdapter);
                setMonth(Integer.parseInt(currentText));
                initDays(day);
                mDaydapter = new CalendarTextAdapter(context, arry_days, 0,
                        maxTextSize, minTextSize);
                wvDay.setVisibleItems(setCount);
                wvDay.setViewAdapter(mDaydapter);
                wvDay.setCurrentItem(0);
            }
        });

        wvMonth.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) mMonthAdapter.getItemText(wheel
                        .getCurrentItem());
                setTextviewSize(currentText, mMonthAdapter);
            }
        });

        wvDay.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentText = (String) mDaydapter.getItemText(wheel
                        .getCurrentItem());
                setTextviewSize(currentText, mDaydapter);
                selectDay = currentText;
            }
        });

        wvDay.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) mDaydapter.getItemText(wheel
                        .getCurrentItem());
                setTextviewSize(currentText, mDaydapter);
            }
        });

        beHour.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentText = (String) behourAdapter.getItemText(wheel
                        .getCurrentItem());
                behour = currentText;
                setTextviewSize(currentText, behourAdapter);
            }
        });
        beHour.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) behourAdapter.getItemText(wheel
                        .getCurrentItem());
                setTextviewSize(currentText, behourAdapter);
            }
        });
        endHour.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentText = (String) endHourAdapter.getItemText(wheel
                        .getCurrentItem());
                endhour = currentText;
                setTextviewSize(currentText, endHourAdapter);
//                int currenthour = Integer.parseInt(currentText);
//                setYear(currentYear);
            }
        });
        endHour.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) endHourAdapter.getItemText(wheel
                        .getCurrentItem());
                setTextviewSize(currentText, endHourAdapter);
            }
        });
        /**
         * end--- pick
         */
        endYear.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentText = (String) endYeayAdapter.getItemText(wheel
                        .getCurrentItem());
                esYear = currentText;
                setTextviewSize(currentText, endYeayAdapter);
                currentYear = Integer.parseInt(currentText);
                setYear(currentYear);
                initMonths(month);
                endMonthAdapter = new CalendarTextAdapter(context, arry_months,
                        0, maxTextSize, minTextSize);
                endMonth.setVisibleItems(setCount);
                endMonth.setViewAdapter(endMonthAdapter);
                endMonth.setCurrentItem(0);
            }
        });

        endYear.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) endYeayAdapter.getItemText(wheel
                        .getCurrentItem());
                setTextviewSize(currentText, endYeayAdapter);
            }
        });

        endMonth.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentText = (String) endMonthAdapter.getItemText(wheel
                        .getCurrentItem());
                esMonth = currentText;
                setTextviewSize(currentText, endMonthAdapter);
                setMonth(Integer.parseInt(currentText));
                initDays(day);
                endDayAdapter = new CalendarTextAdapter(context, arry_days, 0,
                        maxTextSize, minTextSize);
                endDay.setVisibleItems(setCount);
                endDay.setViewAdapter(endDayAdapter);
                endDay.setCurrentItem(0);
            }
        });

        endMonth.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) endMonthAdapter.getItemText(wheel
                        .getCurrentItem());
                setTextviewSize(currentText, endMonthAdapter);
            }
        });

        endDay.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                String currentText = (String) endDayAdapter.getItemText(wheel
                        .getCurrentItem());
                setTextviewSize(currentText, endDayAdapter);
                esDay = currentText;
            }
        });

        endDay.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                String currentText = (String) endDayAdapter.getItemText(wheel
                        .getCurrentItem());
                setTextviewSize(currentText, endDayAdapter);
            }
        });

    }

    public void initData() {
        setDate(getYear(), getMonth(), getDay());
    }

    public void initYears() {
        for (int i = MIN_YEAR; i < MAX_YEAR; i++) {
            arry_years.add(i + "");
        }
    }

    public void initMonths(int months) {
        arry_months.clear();
        for (int i = 1; i <= months; i++) {
            arry_months.add(i + "");
        }
    }

    public void initDays(int days) {
        arry_days.clear();
        for (int i = 1; i <= days; i++) {
            arry_days.add(i + "");
        }
    }

    /**
     * 设置dialog弹出框模式
     *
     * @param dialogMode
     * @param -DIALOG_MODE_BOTTOM 从屏幕底部弹出
     * @param- DIALOG_MODE_CENTER
     * 从屏幕中间弹出
     */
    public void setDialogMode(int dialogMode) {
        if (dialogMode == DIALOG_MODE_BOTTOM) {
            resetContent(R.layout.dialog_picker_bottom);
            setAnimation(R.style.AnimationBottomDialog);
            setGravity(Gravity.BOTTOM);
        }
    }

    public void setTitle(String title) {
        this.strTitle = title;
    }

    @Override
    protected int dialogWidth() {
        DisplayMetrics metric = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay()
                .getMetrics(metric);
        return metric.widthPixels;
    }

    public void setDatePickListener(OnDatePickListener onDatePickListener) {
        this.onDatePickListener = onDatePickListener;
    }

    @Override
    public void onClick(View v) {

        if (v == btnSure) {
            if (onDatePickListener != null) {
                onDatePickListener.onClick(selectYear, selectMonth, selectDay, esYear, esMonth, esDay, behour, endhour);
            }
        } else if (v == btnCancel) {

        } else if (v == vDialogChild) {
            return;
        } else {
            dismiss();
        }
        dismiss();
    }

    public interface OnDatePickListener {
        /**
         * @param beiginyear
         * @param beiginmonth
         * @param beiginday
         */
        void onClick(String beiginyear, String beiginmonth, String beiginday, String endyear, String endmonth, String endday, String behours, String endhour);


    }

    /**
     * 设置字体大小
     *
     * @param curriteItemText
     * @param adapter
     */
    public void setTextviewSize(String curriteItemText,
                                CalendarTextAdapter adapter) {
        ArrayList<View> arrayList = adapter.getTestViews();
        int size = arrayList.size();
        String currentText;
        for (int i = 0; i < size; i++) {
            TextView textvew = (TextView) arrayList.get(i);
            currentText = textvew.getText().toString();
            if (curriteItemText.equals(currentText)) {
                textvew.setTextSize(maxTextSize);
            } else {
                textvew.setTextSize(minTextSize);
            }
        }
    }

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

    public int getHourss() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 设置年月日
     *
     * @param year
     * @param month
     * @param day
     */
    public void setDate(int year, int month, int day) {
        selectYear = year + "";
        selectMonth = month + "";
        selectDay = day + "";
        esYear = year + "";
        esMonth = month + "";
        esDay = day + "";
        issetdata = true;
        this.currentYear = year;
        this.currentMonth = month;
        this.currentDay = day;
        this.month = 12;
        calDays(year, month);
    }

    /**
     * 设置年份
     *
     * @param year
     */
    private int setYear(int year) {
        int yearIndex = 0;
        for (int i = MIN_YEAR; i < MAX_YEAR; i++) {
            if (i == year) {
                return yearIndex;
            }
            yearIndex++;
        }
        return yearIndex;
    }

    /**
     * 设置月份
     *
     * @param month
     * @return
     * @param- year
     */
    private int setMonth(int month) {
        int monthIndex = 0;
        calDays(currentYear, month);
        for (int i = 1; i < this.month; i++) {
            if (month == i) {
                return monthIndex;
            } else {
                monthIndex++;
            }
        }
        return monthIndex;
    }

    /**
     * 计算每月多少天
     *
     * @param month
     * @param -leayyear
     */
    public void calDays(int year, int month) {
        boolean leayyear = false;
        if (year % 4 == 0 && year % 100 != 0) {
            leayyear = true;
        } else {
            leayyear = false;
        }
        for (int i = 1; i <= 12; i++) {
            switch (month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    this.day = 31;
                    break;
                case 2:
                    if (leayyear) {
                        this.day = 29;
                    } else {
                        this.day = 28;
                    }
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    this.day = 30;
                    break;
            }
        }
//        if (year == getYear() && month == getMonth()) {
//            this.day = getDay();
//        }

    }

    public void setHour() {
//        nowHours = getHours();
//        behour = nowHours + "";
//        endhour = nowHours + "";
        arry_hour.clear();
        for (int i = 0; i < 24; i++) {
            arry_hour.add(i + "");
        }

    }

    private class CalendarTextAdapter extends AbstractWheelTextAdapter {
        ArrayList<String> list;

        protected CalendarTextAdapter(Context context, ArrayList<String> list,
                                      int currentItem, int maxsize, int minsize) {
            super(context, R.layout.item_birth_year, NO_RESOURCE, currentItem,
                    maxsize, minsize);
            this.list = list;
            setItemTextResource(R.id.tempValue);
        }

        @Override
        public View getItem(int index, View cachedView, ViewGroup parent) {
            View view = super.getItem(index, cachedView, parent);
            return view;
        }

        @Override
        public int getItemsCount() {
            return list.size();
        }

        @Override
        protected CharSequence getItemText(int index) {
            return list.get(index) + "";
        }
    }

}