package advisor.service;

import java.util.List;

public interface Service {
    public List<String> getResult();

    public void execute(String token);
}
