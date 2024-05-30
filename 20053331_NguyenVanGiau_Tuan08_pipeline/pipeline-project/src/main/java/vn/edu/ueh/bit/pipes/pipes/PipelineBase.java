package vn.edu.ueh.bit.pipes.pipes;

import vn.edu.ueh.bit.pipes.core.entities.IFilter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public abstract class PipelineBase<T> {
    private final List<IFilter<T>> filters = new ArrayList<>();

    public void RegisterFilter(IFilter<T> filter) {
        filters.add(filter);
    }


    public T ProcessFilters(T message) {
        System.out.println("\t processing " + message.toString());
        //return this._filters.Aggregate(message, (current, operation) => operation.Execute(current));
//        return filters.stream().reduce((tiFilter, tiFilter2) -> tiFilter).get().execute(message);
        AtomicReference<T> s =new AtomicReference<>();
        filters.forEach(tiFilter -> {
            s.set(tiFilter.execute(message));
//            System.out.println(s);
        });
        return s.get();
    }

}

