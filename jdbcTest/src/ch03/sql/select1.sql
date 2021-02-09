select c.custid id, name, orderid,   b.bookname, saleprice, publisher  
  from orders o, customer c, book b
where o.custid = c.custid
    and o.bookid = b.bookid
    and c.name like '박%'
    and b.bookname like '축구%'
    and o.saleprice < 15000
    ;
    
   select COUNT(*) from user_tables where table_name='MEMBERS';
   select * from members;

   create table members(
   id varchar2(20) primary key,
   pwd varchar2(20) not null,
   name varchar2(30)
   );
   
   
   select bookname,publisher,price from book order by bookid;
   select bookid, bookname, price, price*1.1 from book;
   
   
   