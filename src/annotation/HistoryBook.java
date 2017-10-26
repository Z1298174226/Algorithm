package annotation;

public class HistoryBook extends Book{
	@BookName(bookName = "history")
	public String bookName;
	@BookType(bookType = BookType.Booktype.art)
	public BookType.Booktype bookType;

}
