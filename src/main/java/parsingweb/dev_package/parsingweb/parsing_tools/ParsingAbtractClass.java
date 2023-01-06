package parsingweb.dev_package.parsingweb.parsing_tools;

import lombok.Data;

import java.math.BigInteger;

@Data
public abstract class ParsingAbtractClass {
    protected String AbsBookName;
    protected String AbsAuthor;
    protected String AbsDescription;
    protected Integer AbsYearStart;
    protected String AbsImageBook;
    protected String AbsBookISBN;
    protected Integer AbsCountPages;
    protected String AbsGenre;
    protected String AbsSite;
    abstract public void parsingSite();
}
