/* THIS CODE IS MY OWN WORK.
IT WAS WRITTEN WITHOUT CONSULTING CODE WRITTEN BY OTHER STUDENTS.
Dong Sun Yoon */
use imdb;


select movie.name, movie.year
from movie,(select distinct movie.id 
			from movie join casts on movie.id = casts.mid
            join actor on casts.aid = actor.id
            where actor.fname = 'Matt'
            and actor.lname = 'Damon')R1,
            (select distinct movie.id 
			from movie join casts on movie.id = casts.mid
            join actor on casts.aid = actor.id
            and actor.fname= 'George'
            and actor.lname = 'Clooney')R2,
            (select distinct movie.id 
			from movie join casts on movie.id = casts.mid
            join actor on casts.aid = actor.id
            and actor.fname = 'Brad'
            and actor.lname = 'Pitt') R3
where movie.id = R1.id
and movie.id = R2.id
and movie.id = R3.id
order by movie.name;


