select distinct p.name  from movies m 
join stars s on s.movie_id = m.id 
join people p on s.person_id =  p.id 
where m.id IN (select s.movie_id from stars s
                      join people p on s.person_id = p.id 
                      where p.name = 'Kevin Bacon'
                      and p.birth = 1958) ;