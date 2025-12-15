-- movies where BC starred 

select title from movies m 
where m.id IN (select s.movie_id from stars s
                      join people p on s.person_id = p.id 
                      where p.name = 'Bradley Cooper') 
AND m.id in (select s.movie_id from stars s
                      join people p on s.person_id = p.id 
                      where p.name = 'Jennifer Lawrence');