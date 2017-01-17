/* THIS CODE IS MY OWN WORK.
IT WAS WRITTEN WITHOUT CONSULTING CODE WRITTEN BY OTHER STUDENTS.
Dong Sun Yoon */
use imdb;

select director.fname, director.lname, count(distinct movie.id)
from director join movie_director on director.id = movie_director.did
join movie on movie_director.mid = movie.id
where movie.year >= 2005 
and movie.year <= 2010
group by director.id
order by count(movie.id) desc
limit 100;