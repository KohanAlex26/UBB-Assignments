package ro.ubb.core.validators;

public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}
