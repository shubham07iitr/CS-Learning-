select p.name, p.birth from movies m 
join stars s on s.movie_id = m.id 
join people p on p.id = s.person_id 

where m.year = 2004 
order by p.birth asc;