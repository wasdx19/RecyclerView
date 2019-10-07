package com.example.recyclerviewtest;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.recyclerviewtest.mock.MockAdapter;
import com.example.recyclerviewtest.mock.MockGenerator;

import java.util.Random;

public class RecyclerFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, LoaderManager.LoaderCallbacks<Cursor> {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View errorView;
    private final ContactsAdapter contactsAdapter=new ContactsAdapter();
    private ContactsAdapter.OnItemClickListener onItemClickListener;

    private Random random=new Random();

    public static RecyclerFragment newInstance() {
        return new RecyclerFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof ContactsAdapter.OnItemClickListener){
            onItemClickListener= (ContactsAdapter.OnItemClickListener) context;
        }
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fr_recycler,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler);
        errorView=view.findViewById(R.id.error_view);
        swipeRefreshLayout=view.findViewById(R.id.refresher);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(contactsAdapter);
        contactsAdapter.setListener(onItemClickListener);
    }

    @Override
    public void onRefresh() {
        getLoaderManager().restartLoader(0,null,this);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new CursorLoader(getActivity(),
                                ContactsContract.Contacts.CONTENT_URI,
                                new String[]{ContactsContract.Contacts._ID,ContactsContract.Contacts.DISPLAY_NAME},
                        null, null,
                                ContactsContract.Contacts._ID);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor cursor) {
        contactsAdapter.swapCursor(cursor);

        if(swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }

    @Override
    public void onDetach() {
        onItemClickListener=null;
        super.onDetach();
    }
}
