select m.title , r.rating from movies m 
join ratings r on m.id = r.movie_id 
where m.id IN (select s.movie_id from stars s
                      join people p on s.person_id = p.id 
                      where p.name = 'Chadwick Boseman')
order by 2 desc
limit 5;