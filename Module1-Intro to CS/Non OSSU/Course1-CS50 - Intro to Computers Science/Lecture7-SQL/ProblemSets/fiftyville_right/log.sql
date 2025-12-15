-- Keep a log of any SQL queries you execute as you solve the mystery.

------------------------------------------------1-------------------------------------------------------
--Understanding what is the crime from crime_scene_reports
-- Key info unveiled - 10:15AM incident, 3ivs , all mentioned bakery
select * from crime_scene_reports 
where year = 2021 
and month = 7 
and day = 28 
and street = 'Humphrey Street';


------------------------------------------------2------------------------------------------------
--Going through the iv transcripts
--Key info unveiled 
    -- Left bakery parking lot within 10 mins of theft from 10:15AM to 10:25AM
    -- Withdrew some money before the theft from ATM on Leggett Street
    -- Called someone after leaving bakery for < 1 min, 
    -- earliest flight out of fiftyville on 29th Jul 2021, purchase not completed yet


select * from interviews  
where year = 2021 
and month = 7 
and day = 28


------------------------------------------------3------------------------------------------------
-- Trying to check who left the bakery within the prescribed time
-- Key info unveiled 
    -- 8 cars exited within 10 minutes of the robbery ('5P2BI95', '94KL13X', '6P58WS2', '4328GD8',  'G412CB7', 'L93JTIZ', '322W7JE', '0NTHK55' )

select * from bakery_security_logs   
where year = 2021 
and month = 7 
and day = 28 
and hour = 10 
and minute >= 15 
and minute <= 25;


------------------------------------------------4------------------------------------------------
-- Checking who those 8 cars belonged to 
-- Key info unveiled
    
    -- 221103|Vanessa|(725) 555-4692|2963008352|5P2BI95
    -- 243696|Barry|(301) 555-4174|7526138472|6P58WS2
    -- 396669|Iman|(829) 555-5269|7049073643|L93JTIZ
    -- 398010|Sofia|(130) 555-0289|1695452385|G412CB7
    -- 467400|Luca|(389) 555-5198|8496433585|4328GD8
    -- 514354|Diana|(770) 555-1861|3592750733|322W7JE
    -- 560886|Kelsey|(499) 555-9472|8294398571|0NTHK55
    -- 686048|Bruce|(367) 555-5533|5773159633|94KL13X

select * from people 
where license_plate in ('5P2BI95', '94KL13X', '6P58WS2', '4328GD8',  'G412CB7', 'L93JTIZ', '322W7JE', '0NTHK55' )


------------------------------------------------5------------------------------------------------
-- Checking who all withdrew money on the same date and who also exited within 10 minutes of robbery 
-- Key info unvelied:
    -- 4 people left which are common (467400, 686048, 396669, 514354)

select b.person_id from atm_transactions  t 
join bank_accounts b on t.account_number = b.account_number 

where t.year = 2021 
and t.month = 7 
and t.day = 28 
and t.atm_location = 'Leggett Street' 
and t.transaction_type = 'withdraw'
and person_id in (select id from people 
                    where license_plate in ('5P2BI95', '94KL13X', '6P58WS2', '4328GD8',  'G412CB7', 'L93JTIZ', '322W7JE', '0NTHK55' ));



------------------------------------------------6------------------------------------------------
-- Checking who called for <1 min on that day 28th Jul 2021 along with the above filters of withdraw money + left bakery within 10 mins
-- Key info unveiled - 2 people id left (514354, 686048)
select p.id  from phone_calls c 
join people p on c.caller = p.phone_number 
where year = 2021 and month = 7 and day = 28 and duration < 60
and p.id IN (select b.person_id from atm_transactions  t 
                join bank_accounts b on t.account_number = b.account_number 

                where t.year = 2021 
                and t.month = 7 
                and t.day = 28 
                and t.atm_location = 'Leggett Street' 
                and t.transaction_type = 'withdraw'
                and person_id in (select id from people 
                                    where license_plate in ('5P2BI95', '94KL13X', '6P58WS2', '4328GD8',  'G412CB7', 'L93JTIZ', '322W7JE', '0NTHK55' )));


------------------------------------------------7------------------------------------------------
-- Final check for people who will be leaving next day from first flight out of flightville 
-- Final person id  = 686048

select p.id from people p 
join passengers pa on p.passport_number = pa.passport_number
and pa.flight_id in (select id from flights 
                        where year = 2021 and month = 7 and day = 29 
                        and origin_airport_id IN (select id from airports where city like 'Fifty%') 
                        order by hour asc limit 1)



and p.id IN (select p.id  from phone_calls c 
            join people p on c.caller = p.phone_number 
            where year = 2021 and month = 7 and day = 28 and duration < 60
            and p.id IN (select b.person_id from atm_transactions  t 
                        join bank_accounts b on t.account_number = b.account_number 
                        where t.year = 2021 
                        and t.month = 7 
                        and t.day = 28 
                        and t.atm_location = 'Leggett Street' 
                        and t.transaction_type = 'withdraw'
                        and person_id in (select id from people 
                                          where license_plate in ('5P2BI95', '94KL13X', '6P58WS2', '4328GD8',  'G412CB7', 'L93JTIZ', '322W7JE', '0NTHK55' ))));
                    


------------------------------------------------8------------------------------------------------
-- Name of the thief - Bruce
select name from people where id = 686048

------------------------------------------------9------------------------------------------------
-- Destination where thief was flying to - NYC

select city from airports 
where id in (select destination_airport_id from flights 
             where year = 2021 and month = 7 and day = 29 
             and origin_airport_id IN (select id from airports where city like 'Fifty%') 
             order by hour asc limit 1);




------------------------------------------------10------------------------------------------------
-- Accomplice - 864400, Robin

select p.id, p.name from people p 
where phone_number IN (
                        select receiver  from phone_calls c 
                        join people p on c.caller = p.phone_number 
                        where year = 2021 and month = 7 and day = 28 and duration < 60
                        and p.id = 686048) ;