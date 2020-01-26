package tennis.model.scoreFactory;

public interface AbstractFactory<T> {
    T create (String scoreType);

}
