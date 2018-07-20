package com.app.aadhi.common;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.app.aadhi.R;

import java.util.Arrays;
import java.util.List;

public class SpinnerAdapter extends ArrayAdapter {

    private List<String> mItems;

    public SpinnerAdapter(@NonNull Context context, int stringArrayResourceId) {
        super(context, R.layout.spinner_layout_simple_item);
        this.mItems = Arrays.asList(context.getResources().getStringArray(stringArrayResourceId));
    }

    @Override
    public int getCount() {
        return mItems.size() - 1;
    }


    @Nullable
    @Override
    public Object getItem(int position) {
            return mItems.get(position + 1);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    public static void bind(Spinner spinner, SpinnerAdapter adapter) {
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        //spinner.setSelection(adapter.mItems.size() - 1);
    }
}
