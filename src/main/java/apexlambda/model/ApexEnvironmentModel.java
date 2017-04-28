package apexlambda.model;

import java.util.Map;

public class ApexEnvironmentModel {

    private Map<String, String> environment;

    public ApexEnvironmentModel(Map<String, String> environment) {
        this.environment = environment;
    }

    public Map<String, String> getEnvironment() {
        return environment;
    }

}
