package dev.patika.VetClinic.core.config.ModelMapper;

import org.modelmapper.ModelMapper;

public interface IModelMapperService {

    ModelMapper forRequest();
    ModelMapper forResponse();
}
