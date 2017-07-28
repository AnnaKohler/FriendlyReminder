package ru.workingcorgi.friendlyreminder;

import java.util.List;

/**
 * Created by Anna on 26.06.2017.
 */

public interface IDBHandler {

    void addToDo(ToDo toDo);
    void updateToDo(ToDo toDo);
    void deleteToDo(ToDo toDo);
    void clearAll();
    List<ToDo> getAllToDos();
}
