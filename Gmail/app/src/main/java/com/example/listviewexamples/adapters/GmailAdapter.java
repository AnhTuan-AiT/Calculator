package com.example.listviewexamples.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listviewexamples.R;
import com.example.listviewexamples.models.GmailModel;

import java.util.ArrayList;
import java.util.List;

public class GmailAdapter extends BaseAdapter implements Filterable {

    Context context;

    List<GmailModel> gmails;

    List<GmailModel> filtered;

    public GmailAdapter(Context context, List<GmailModel> gmails) {
        this.context = context;
        this.gmails = gmails;
        this.filtered = gmails;
    }

    @Override
    public int getCount() {
        return filtered.size();
    }

    @Override
    public Object getItem(int i) {
        return filtered.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        GmailModel mail = filtered.get(i);

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.gmail_item, viewGroup, false);
            viewHolder = new ViewHolder();

            viewHolder.avatar = view.findViewById(R.id.avatar);
            viewHolder.sender = view.findViewById(R.id.sender);
            viewHolder.title = view.findViewById(R.id.mail_title);
            viewHolder.content = view.findViewById(R.id.mail_content);
            viewHolder.time = view.findViewById(R.id.mail_time);

            ImageView starIcon = view.findViewById(R.id.startIcon);
            viewHolder.startIcon = starIcon;

            starIcon.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mail.isStarred()) {
                        mail.setiStarred(false);
                        starIcon.setImageResource(R.drawable.unstared);
                    } else {
                        mail.setiStarred(true);
                        starIcon.setImageResource(R.drawable.stared);
                    }
                }
            });

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.avatar.setText(String.valueOf(Character.toUpperCase(mail.getSender().charAt(0))));
        viewHolder.sender.setText(mail.getSender());
        viewHolder.title.setText(mail.getTitle());
        viewHolder.content.setText(mail.getContent());
        viewHolder.time.setText(mail.getTime());

        if (mail.isReaded()) {
            viewHolder.sender.setTypeface(null, Typeface.NORMAL);
            viewHolder.title.setTypeface(null, Typeface.NORMAL);
        }

        if (mail.isStarred()) {
            viewHolder.startIcon.setImageResource(R.drawable.stared);
        }

        return view;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults results = new FilterResults();

                if (charSequence == null || charSequence.length() == 0) {
                    //no constraint given, just return all the data. (no search)
                    results.count = gmails.size();
                    results.values = gmails;
                } else {//do the search
                    List<GmailModel> resultsData = new ArrayList<>();
                    CharSequence txtSearch = charSequence.toString().toUpperCase();

                    for (GmailModel mail : gmails) {
                        if (mail.getSender().toUpperCase().contains(txtSearch) ||
                                mail.getTitle().toUpperCase().contains(txtSearch)) {
                            resultsData.add(mail);

                        }
                    }

                    results.count = resultsData.size();
                    results.values = resultsData;
                }

                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filtered = (List<GmailModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public void remove(int position) {
        GmailModel removedItem = filtered.remove(position);
        gmails.remove(removedItem);
        notifyDataSetChanged();
    }

    private class ViewHolder {

        TextView avatar;

        TextView sender;

        TextView title;

        TextView content;

        TextView time;

        ImageView startIcon;
    }
}
