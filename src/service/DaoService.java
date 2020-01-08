package service;

public interface DaoService<E> {

    Long insert(E e);

    E findById(Long id);

}
