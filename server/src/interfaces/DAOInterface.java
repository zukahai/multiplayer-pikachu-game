package interfaces;

import models.Notification;

import java.util.ArrayList;

public interface DAOInterface<T> {
    public Notification<T> create(T data);

    public Notification<T> findOne(int id);

    public Notification<T> update(int id, T data);

    public ArrayList<T> findAll();

    public Notification<T> delete(int id);

}
