package com.intsoftdev.themesdemo;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Random;

public class SampleRecyclerAdapter
        extends RecyclerView.Adapter<SampleRecyclerAdapter.TextViewHolder> {

    protected int mCount;

    public SampleRecyclerAdapter(int count) {
        mCount = count;
    }

    @NonNull @Override
    public TextViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return TextViewHolder.createViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull TextViewHolder holder, int position) {
        holder.bindView(position);
    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    public void add() {
        int position = mCount;
        mCount++;
        notifyItemInserted(position);
    }

    public void remove() {
        if (mCount == 0) {
            return;
        }
        mCount--;
        int position = mCount;
        notifyItemRemoved(position);
    }

    static class TextViewHolder extends RecyclerView.ViewHolder {

        static TextViewHolder createViewHolder(@NonNull ViewGroup parent) {
            TextView textView = new TextView(parent.getContext());
            textView.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL);
            textView.setTextSize(48);
            textView.setLayoutParams(
                    new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT,
                            RecyclerView.LayoutParams.MATCH_PARENT));
            return new TextViewHolder(textView);
        }

        TextViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        void bindView(int position) {
            TextView textView = (TextView) itemView;
            textView.setText(String.valueOf(position + 1));
        }
    }
}