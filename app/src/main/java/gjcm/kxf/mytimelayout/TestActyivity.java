package gjcm.kxf.mytimelayout;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kxf on 2016/12/16.
 */
public class TestActyivity extends AppCompatActivity {
    private ListView listView;
    private List<String> lists;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout);
        listView = (ListView) findViewById(R.id.other_listview);
        lists = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            lists.add("sdsdeee" + i);
        }
        MyAdapter adapter = new MyAdapter(this);
        listView.setAdapter(adapter);
    }


    class MyAdapter extends BaseAdapter {
        Context mContext;
        LayoutInflater inflater;
        final int TYPE_1 = 0;
        final int TYPE_2 = 1;

        public MyAdapter(Context context) {
            mContext = context;
            inflater = LayoutInflater.from(mContext);
        }

        @Override
        public int getCount() {
            return lists.size() + lists.size() / 6 + 1;
        }

        //每个convert view都会调用此方法，获得当前所需要的view样式
        @Override
        public int getItemViewType(int position) {
            int p = position % 6;
            if (p == 0)
                return TYPE_1;
            else if (p < 6)
                return TYPE_2;
            else
                return TYPE_1;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public Object getItem(int arg0) {
            return lists.get(arg0);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            viewHolder1 holder1 = null;
            viewHolder2 holder2 = null;
            int type = getItemViewType(position);
            //无convertView，需要new出各个控件
            if (convertView == null) {
                //按当前所需的样式，确定new的布局
                switch (type) {
                    case TYPE_1:
                        convertView = inflater.inflate(R.layout.recycler_item, parent, false);
                        holder1 = new viewHolder1();
                        holder1.textView = (TextView) convertView.findViewById(R.id.recycler_item_txt);
                        convertView.setTag(holder1);
                        break;
                    case TYPE_2:
                        convertView = inflater.inflate(R.layout.two_layout, parent, false);
                        holder2 = new viewHolder2();
                        holder2.textView = (TextView) convertView.findViewById(R.id.two_layout_txt);
                        convertView.setTag(holder2);
                        break;
                }
            } else {
                //有convertView，按样式，取得不同的布局
                switch (type) {
                    case TYPE_1:
                        holder1 = (viewHolder1) convertView.getTag();
                        break;
                    case TYPE_2:
                        holder2 = (viewHolder2) convertView.getTag();
                        break;
                }
            }

            //赋值
            switch (type) {
                case TYPE_1:
                    //holder1.textView.setText(Integer.toString(position));
                    holder1.textView.setText(lists.get(position));
                    break;
                case TYPE_2:
                    holder2.textView.setText(lists.get(position - position / 6 - 1));
                    break;
            }
            return convertView;
        }
    }
    class viewHolder1 {
        TextView textView;
    }

    class viewHolder2 {
        TextView textView;
    }
}
