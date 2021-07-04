package web.converter;

/**
 * Created by radu.
 */
public interface ConverterForManyToMany<Model, Dto> {
    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);
}
