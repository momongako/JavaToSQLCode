 
 
use SampleDB; 
CREATE TABLE orders (
  order_id int identity(1,1) NOT NULL  ,
  product_id int DEFAULT NULL,
  amount float DEFAULT NULL,
  order_date datetime DEFAULT NULL,
  PRIMARY KEY (order_id)
) ;
CREATE TABLE products (
  product_id int identity(1,1) NOT NULL  ,
  product_name varchar(45) NOT NULL,
  price float DEFAULT NULL,
  PRIMARY KEY (product_id)
);  

CREATE TABLE monthly_sales ( 
    report_month int,
    product_id int,
    total_amount float  
);
