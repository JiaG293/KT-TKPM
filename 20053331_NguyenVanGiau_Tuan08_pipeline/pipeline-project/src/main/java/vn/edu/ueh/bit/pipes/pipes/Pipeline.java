package vn.edu.ueh.bit.pipes.pipes;

import vn.edu.ueh.bit.pipes.core.entities.IFilter;
import vn.edu.ueh.bit.pipes.core.entities.IMessage;

import java.util.List;

public class Pipeline extends PipelineBase<IMessage> {
    public Pipeline(List<IFilter<IMessage>> filters) {
        filters.forEach(this::RegisterFilter);
    }
}
