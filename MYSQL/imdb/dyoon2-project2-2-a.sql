/* THIS CODE IS MY OWN WORK.
IT WAS WRITTEN WITHOUT CONSULTING CODE WRITTEN BY OTHER STUDENTS.
Dong Sun Yoon */
use imdb;

select distinct actor.fname, actor.lname, movie.year, count(distinct movie.id)
from actor join casts on actor.id = casts.aid
join movie on casts.mid = movie.id
where movie.year = 2004
group by actor.id 
having count(distinct movie.id) >= 10
order by lname= ' ' , lname;
