/* THIS CODE IS MY OWN WORK.
IT WAS WRITTEN WITHOUT CONSULTING CODE WRITTEN BY OTHER STUDENTS.
Dong Sun Yoon */
use imdb;


select X1.year, count(X1.id)
from
/* actor, casts, movie*/
(select R1.year, R1.id 
from (select distinct movie.year, movie.id
from actor, casts, movie
where actor.id = casts.aid
and casts.mid = movie.id
and actor.gender = 'F') as R1 /* movie.id with actors with female */
left Join
(select distinct movie.id
from actor, casts, movie
where actor.id = casts.aid
and casts.mid = movie.id
and actor.gender = 'M') as R2 /* movie.id with male actors */
on R1.id = R2.id 
where R2.id is NULL) as X1 /* movie.id of female only */
left join
(select R3.id
from (select distinct movie.id
		from movie 
        )as R3
left join
(select distinct movie.id
		from movie join casts on movie.id = casts.mid)as R4
        on R3.id = R4.id 
        where R4.id is NULL) as R5 /*movie.id of no casts */
on X1.id = R5.id
and R5.id is NULL /* movie.id(female) - movie.id(nocast) */
group by X1.year
order by X1.year;
