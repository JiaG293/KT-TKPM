package vn.edu.ueh.bit.pipes.core.entities;

public interface IFilter<T> {
    T execute(T message);
}
