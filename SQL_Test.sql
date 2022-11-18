select * 
from student 
where name like "a%" or "%A"; 





select s.studentId  , s.name , (m.english + m.maths + m.science) as total
from student s
	inner join mark m on s.studentId = m.studentId
order by total DESC;




select name 
from student
where studentId in (
	select m1.studentId
	from mark m1
	where 1 > (
		select count(m2.studentId)
		from mark m2
		where (m1.english + m1.maths + m1.science) < (m2.english + m2.maths + m2.science)
	)
); 
