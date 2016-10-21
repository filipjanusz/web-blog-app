package filip.janusz.webblog.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class NewsDTO {

    @NotEmpty(message = "The title cannot be empty")
    @Size(min = 5, message = "The title must contain at least 5 tokens")
    private String title;
    @NotEmpty(message = "The description cannot be empty")
    @Size(min = 5, message = "The description must contain at least 5 tokens")
    private String description;

    public NewsDTO() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
