/* 테이블 칼럼별 제약조건 확인 쿼리문 */
select constraint_name, table_name, column_name
from user_cons_columns;

/* 테이블별 제야조건 확인 쿼리문 */
select constraint_name, table_name, constraint_type
  from user_constraints;
  
 /* index 생성 유무확인 */
  select table_name, index_name, column_name
    from user_ind_columns
 where table_name in ('BOOK');
 
 select * from user_indexes;
 