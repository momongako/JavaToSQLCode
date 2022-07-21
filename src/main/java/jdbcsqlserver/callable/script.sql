use SampleDB;

 CREATE TABLE authors (
    author_id int identity(1,1) NOT NULL  ,
    name varchar(45) NOT NULL, 
    email varchar(45) NOT NULL,
    PRIMARY KEY (author_id)
);

CREATE TABLE books (
    book_id int identity(1,1) NOT NULL  ,
    title varchar(200) NOT NULL,
    description varchar(200) NOT NULL,
    published date  NULL,
    author_id int   NULL,
    price float,
    rating int  ,
    PRIMARY KEY (book_id)
);
go
 

CREATE PROCEDURE Create_author  (@name VARCHAR(45), @email VARCHAR(45))
As 
BEGIN
    DECLARE @newAuthorID INT;
    INSERT INTO authors (name, email) VALUES (@name, @email);
     
    SET @newAuthorID = (SELECT author_id FROM authors a WHERE a.name = @name);
     
    INSERT INTO books (title, description, published, author_id, price, rating)
        VALUES (CONCAT('Life Story of ', @name),
                CONCAT('Personal Stories of ', @name),
               '2016-12-30' , @newAuthorID, 10.00, 0);
    
END
go
CREATE PROCEDURE get_books(@rate INT)
as BEGIN
    SELECT * FROM books WHERE rating >= @rate;
END
go
-- Cac tham so phai viet tren 1 dongs
CREATE PROCEDURE summary_report( @title VARCHAR(45)  , @totalBooks INT Output, @totalValue float Output,  @highPrice float Output )
As BEGIN
    DECLARE @maxPrice float;
     
    SELECT @totalBooks=COUNT(*)   , @totalValue=SUM(price)  
        FROM books b JOIN authors a ON b.author_id = a.author_id
        AND b.title LIKE CONCAT('%', @title, '%')

  
     
    SELECT @maxPrice=MAX(price) FROM books  
     
    IF (@maxPrice > -1)  
        SET @highPrice = @maxPrice;
    
END

 