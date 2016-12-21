package gjcm.kxf.mytimelayout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by kxf on 2016/12/13.
 */
public class RecylerAdapter extends RecyclerView.Adapter<RecylerAdapter.ViewHolder> {
    private List<String> lists;
    private Context context;

    public RecylerAdapter(List<String> lists, Context context) {
        this.context = context;
        this.lists = lists;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(context).inflate(R.layout.recycler_item, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txt.setText(lists.get(position));
    }

    @Override
    public int getItemCount() {
        return (lists == null) ? 0 : lists.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
            txt = (TextView) itemView.findViewById(R.id.recycler_item_txt);
        }

        private TextView txt;

    }
}
