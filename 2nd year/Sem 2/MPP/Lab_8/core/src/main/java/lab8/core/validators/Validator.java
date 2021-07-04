package lab8.core.validators;

public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}