package parsingweb.dev_package.parsingweb.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AuthorModDTO {
    private String author;
}
