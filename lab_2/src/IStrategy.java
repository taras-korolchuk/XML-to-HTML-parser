import java.io.File;
import java.util.List;

public interface IStrategy {
     List<Teacher> parse(String search, File filepath);
}
