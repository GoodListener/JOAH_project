select * from location;
select * from board;


select c.cno, c.content, c.cre_dt
from comment c join board b on b.bno = c.bno;
