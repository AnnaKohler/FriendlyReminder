package ru.workingcorgi.friendlyreminder;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ToDoListFragment extends Fragment {

    private ArrayList<ToDo> mToDoArrayList;
    private DBHandler mDBHandler;
    private ToDoArrayAdapter mToDoArrayAdapter;

    //Custom Adapter class


    public static ToDoListFragment newInstance() {

        Bundle args = new Bundle();

        ToDoListFragment fragment = new ToDoListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    private class ToDoArrayAdapter extends ArrayAdapter<ToDo>{
        private final  Context mContext;
        private final ArrayList<ToDo> mToDoArrayList;

        private ToDoArrayAdapter(Context context, ArrayList<ToDo> objects) {
            super(context, R.layout.todolist_row, objects);
            this.mContext=context;
            this.mToDoArrayList=objects;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater layoutInflater= (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = layoutInflater.inflate(R.layout.todolist_row, parent, false); //TODO: заменить на ViewHolder!

            TextView todoTitle=(TextView)rowView.findViewById(R.id.txtRowToDoTitle);
            CheckBox todoDone=(CheckBox) rowView.findViewById(R.id.checkBoxToDo);

            todoTitle.setText(mToDoArrayList.get(position).getTitle());
            todoDone.setActivated(mToDoArrayList.get(position).isDone());

            return rowView;
        }
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mDBHandler= new DBHandler(getContext());
        mToDoArrayList= mDBHandler.getAllToDos();//TODO: этот лист ссылается на те же toDo, как бы ошибки не вышло

        //Set Custom Adapter
        if(mToDoArrayList.size()!=0) {
            mToDoArrayAdapter = new ToDoArrayAdapter(getContext(), mToDoArrayList);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_no_todos, container, false);

        if(mToDoArrayList.size()!=0){
            v = inflater.inflate(R.layout.fragment_todolist, container, false); //TODO: прописать все компоненты фрагмента

            ListView listView =(ListView) v.findViewById(R.id.listToDo);
            listView.setAdapter(mToDoArrayAdapter);
            listView.setDivider(null);

            EditText AddToDo = (EditText)v.findViewById(R.id.txtAddToDo);

        }

        v.setBackgroundColor(Color.RED); //TODO: убрать после отладки
        return v;
    }
}
