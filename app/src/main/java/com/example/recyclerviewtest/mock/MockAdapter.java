package com.example.recyclerviewtest.mock;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recyclerviewtest.R;

import java.util.ArrayList;
import java.util.List;

public class MockAdapter extends RecyclerView.Adapter<MockHolder> {

    private final List<Mock> mockList=new ArrayList<>();

    @NonNull
    @Override
    public MockHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.li_mock, parent, false);

        return new MockHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MockHolder mockHolder, int position) {
        mockHolder.bind(mockList.get(position));
    }

    @Override
    public int getItemCount() {
        return mockList.size();
    }

    public void addData(List<Mock> mocks, boolean refresh){
        if(refresh) {
            mockList.clear();
        }
        mockList.addAll(mocks);
        notifyDataSetChanged();
    }
}
