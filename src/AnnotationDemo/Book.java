package AnnotationDemo;

public class Book {
	@BookName(bookName = "Iron Man")
	public String bookName;
	@BookInfo(bookInfo = "I like this book")
	public String bookInfo;
	@BookProvider(bookProvider = "DELL")
	public String bookProvider ;
	@BookType(bookType = BookType.Booktype.scient)
	public BookType.Booktype bookType;

}
