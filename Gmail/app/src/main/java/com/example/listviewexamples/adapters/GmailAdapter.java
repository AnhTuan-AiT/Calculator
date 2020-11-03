package com.example.listviewexamples.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.listviewexamples.R;
import com.example.listviewexamples.models.GmailModel;

import java.util.List;

public class GmailAdapter extends BaseAdapter {

    Context context;

    List<GmailModel> gmails;

    public GmailAdapter(Context context, List<GmailModel> gmails) {
        this.context = context;
        this.gmails = gmails;
    }

    @Override
    public int getCount() {
        return gmails.size();
    }

    @Override
    public Object getItem(int i) {
        return gmails.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.layout_gmail_item, viewGroup, false);
            viewHolder = new ViewHolder();

            viewHolder.avatar = view.findViewById(R.id.avatar);
            viewHolder.sender = view.findViewById(R.id.sender);
            viewHolder.title = view.findViewById(R.id.mail_title);
            viewHolder.content = view.findViewById(R.id.mail_content);
            viewHolder.time = view.findViewById(R.id.mail_time);

            view.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) view.getTag();

        GmailModel mail = gmails.get(i);

        viewHolder.avatar.setText(String.valueOf(Character.toUpperCase(mail.getSender().charAt(0))));
        viewHolder.sender.setText(mail.getSender());
        viewHolder.title.setText(mail.getTitle());
        viewHolder.content.setText(mail.getContent());
        viewHolder.time.setText(mail.getTime());

        if (mail.isReaded()) {
            viewHolder.sender.setTypeface(null, Typeface.NORMAL);
            viewHolder.title.setTypeface(null, Typeface.NORMAL);
        }

        if (mail.isiStarred()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                viewHolder.time.setCompoundDrawablesRelativeWithIntrinsicBounds(0, 0, 0, R.drawable.stared);
            }
        }

        return view;
    }

    private class ViewHolder {

        TextView avatar;

        TextView sender;

        TextView title;

        TextView content;

        TextView time;
    }
}
