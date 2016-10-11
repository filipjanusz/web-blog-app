package filip.janusz.contactbook.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CommentDTO {

    @NotEmpty(message = "The comment cannot be empty")
    @Size(min = 5, message = "The comment must contain at least 5 tokens")
    private String content;
    @NotNull
    private String newsId;

    public CommentDTO() {}

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
