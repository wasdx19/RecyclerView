package com.example.recyclerviewtest;

import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recyclerviewtest.mock.Mock;
import com.example.recyclerviewtest.mock.MockHolder;

public class ContactsAdapter extends RecyclerView.Adapter<MockHolder> {

    private Cursor cursor;
    private OnItemClickListener onItemClickListener;

    @NonNull
    @Override
    public MockHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.li_mock, parent, false);

        return new MockHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MockHolder mockHolder, int position) {
        if(cursor.moveToPosition(position)){
            String name=cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            int id=cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            mockHolder.bind(new Mock(name,id));

            mockHolder.setListener(onItemClickListener);
        }
    }

    @Override
    public int getItemCount() {
        return cursor != null ? cursor.getCount():0;
    }

    public void swapCursor(Cursor cursor1){
        if(cursor1!=null && cursor1!=cursor){
            if(cursor!=null) cursor.close();
            cursor=cursor1;
            notifyDataSetChanged();
        }
    }

    public void setListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener=onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItemClick(String id);
    }
}
