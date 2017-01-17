/* THIS CODE IS MY OWN WORK.
IT WAS WRITTEN WITHOUT CONSULTING CODE WRITTEN BY OTHER STUDENTS.
Dong Sun Yoon */
use imdb;

select R1.id, R1.fname, R1.lname
from 
(select distinct actor.id, actor.fname, actor.lname
from actor, casts, movie, movie_director, director
where actor.id = casts.aid
and casts.mid = movie.id 
and movie.id = movie_director.mid
and  movie_director.did = director.id
and director.fname = 'Steven'
and director.lname = 'Soderbergh')as R1
left join
(select distinct actor.id
from actor, casts, movie, movie_director, director
where actor.id = casts.aid
and casts.mid = movie.id 
and movie.id = movie_director.mid
and  movie_director.did = director.id
and director.fname != 'Steven'
and director.lname != 'Soderbergh') as R2
on R1.id = R2.id 
where R2.id is null;