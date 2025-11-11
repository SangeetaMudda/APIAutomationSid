package pojo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class postRequestBody {


    private String name;
    private String job;
    private List<String> languages;

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    private String updatedAt;

    public List<cityRequest> getCityRequests() {
        return cityRequests;
    }

    public void setCityRequests(List<cityRequest> cityRequests) {
        this.cityRequests = cityRequests;
    }

    private List<cityRequest> cityRequests;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }


    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }





}

