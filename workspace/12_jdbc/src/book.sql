CREATE TABLE book
(
	bookNo NUMBER,
	bookTitle VARCHAR2(100),
	bookWriter VARCHAR2(100),
	pubDate DATE,
	PRIMARY KEY(bookNo)
);

SELECT bookNo, bookTitle, bookWriter, pubDate FROM book;