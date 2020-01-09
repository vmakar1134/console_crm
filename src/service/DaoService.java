package service;

import java.util.List;

public interface DaoService<E> {

    Long insert(E e);

    List<E> findAll();

}
