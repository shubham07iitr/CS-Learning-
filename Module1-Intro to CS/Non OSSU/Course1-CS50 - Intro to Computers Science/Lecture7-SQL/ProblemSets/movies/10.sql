select p.name from ratings r 
join directors d on r.movie_id = d.movie_id
join people p on d.person_id = p.id 
where r.rating >= 9.0;