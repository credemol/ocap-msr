insert into user (email) values('younggyu.kim@oracle.com');
insert into user (email) values('jupil.hwang@oracle.com');
insert into user (email) values('sunghye.jeon@oracle.com');
insert into user (email) values('inho.kang@oracle.com');

insert into seat (seat_no, location) values('12341', '12F South');
insert into seat (seat_no, location) values('12343', '12F South');
insert into seat (seat_no, location) values('12345', '12F South');
insert into seat (seat_no, location) values('12347', '12F South');
insert into seat (seat_no, location) values('12349', '12F South');
insert into seat (seat_no, location) values('12351', '12F South');
insert into seat (seat_no, location) values('12353', '12F South');
insert into seat (seat_no, location) values('12355', '12F South');
insert into seat (seat_no, location) values('12357', '12F South');
insert into seat (seat_no, location) values('12359', '12F South');
insert into seat (seat_no, location) values('12361', '12F South');
insert into seat (seat_no, location) values('12363', '12F South');
#insert into seat (seat_no, location) values('12365', '12F South');
insert into seat (seat_no, location) values('12367', '12F South');
insert into seat (seat_no, location) values('12369', '12F South');
insert into seat (seat_no, location) values('12371', '12F South');
insert into seat (seat_no, location) values('12373', '12F South');
insert into seat (seat_no, location) values('12375', '12F South');
insert into seat (seat_no, location) values('12377', '12F South');
insert into seat (seat_no, location) values('12379', '12F South');
insert into seat (seat_no, location) values('12381', '12F South');
insert into seat (seat_no, location) values('12383', '12F South');
insert into seat (seat_no, location) values('12385', '12F South');
insert into seat (seat_no, location) values('12387', '12F South');
insert into seat (seat_no, location) values('12389', '12F South');
insert into seat (seat_no, location) values('12391', '12F South');
insert into seat (seat_no, location) values('12393', '12F South');
insert into seat (seat_no, location) values('12395', '12F South');
insert into seat (seat_no, location) values('12397', '12F South');
insert into seat (seat_no, location) values('12399', '12F South');
insert into seat (seat_no, location) values('12401', '12F South');
insert into seat (seat_no, location) values('12403', '12F South');
insert into seat (seat_no, location) values('12405', '12F South');
insert into seat (seat_no, location) values('12407', '12F South');
insert into seat (seat_no, location) values('12409', '12F South');
insert into seat (seat_no, location) values('12411', '12F South');
insert into seat (seat_no, location) values('12413', '12F South');
insert into seat (seat_no, location) values('12415', '12F South');
insert into seat (seat_no, location) values('12417', '12F South');
insert into seat (seat_no, location) values('12419', '12F South');
insert into seat (seat_no, location) values('12421', '12F South');
insert into seat (seat_no, location) values('12427', '12F South');
insert into seat (seat_no, location) values('12429', '12F South');
insert into seat (seat_no, location) values('12431', '12F South');
insert into seat (seat_no, location) values('12433', '12F South');
#insert into seat (seat_no, location) values('12435', '12F South');
insert into seat (seat_no, location) values('12437', '12F South');
insert into seat (seat_no, location) values('12439', '12F South');
insert into seat (seat_no, location) values('12441', '12F South');
insert into seat (seat_no, location) values('12443', '12F South');
insert into seat (seat_no, location) values('12445', '12F South');
insert into seat (seat_no, location) values('12447', '12F South');
insert into seat (seat_no, location) values('12449', '12F South');
insert into seat (seat_no, location) values('12451', '12F South');
#insert into seat (seat_no, location) values('12453', '12F South');
#insert into seat (seat_no, location) values('12455', '12F South');
#insert into seat (seat_no, location) values('12457', '12F South');
#insert into seat (seat_no, location) values('12459', '12F South');
#insert into seat (seat_no, location) values('12461', '12F South');
insert into seat (seat_no, location) values('12463', '12F South');
insert into seat (seat_no, location) values('12465', '12F South');
insert into seat (seat_no, location) values('12467', '12F South');
insert into seat (seat_no, location) values('12469', '12F South');
insert into seat (seat_no, location) values('12471', '12F South');
insert into seat (seat_no, location) values('12473', '12F South');
insert into seat (seat_no, location) values('12475', '12F South');




insert into seat (seat_no, location) values('12453', '12F South');
insert into seat (seat_no, location) values('12455', '12F South');
insert into seat (seat_no, location) values('12457', '12F South');
insert into seat (seat_no, location) values('12459', '12F South');
insert into seat (seat_no, location) values('12445', '12F South');

insert into reservation (user_id, seat_id, reservation_date, starting_time, ending_time, status, reserved_at, last_modified_at) 
    values (1, 1, STR_TO_DATE('2017-09-05', '%Y-%m-%d'), STR_TO_DATE('2017-09-05 09', '%Y-%m-%d %H'), STR_TO_DATE('2017-09-05 18', '%Y-%m-%d %H'), 1, now(), now());
insert into reservation (user_id, seat_id, reservation_date, starting_time, ending_time, status, reserved_at, last_modified_at) 
    values (2, 2, STR_TO_DATE('2017-09-05', '%Y-%m-%d'), STR_TO_DATE('2017-09-05 09', '%Y-%m-%d %H'), STR_TO_DATE('2017-09-05 18', '%Y-%m-%d %H'), 1, now(), now());
insert into reservation (user_id, seat_id, reservation_date, starting_time, ending_time, status, reserved_at, last_modified_at) 
    values (3, 4, STR_TO_DATE('2017-09-05', '%Y-%m-%d'), STR_TO_DATE('2017-09-05 09', '%Y-%m-%d %H'), STR_TO_DATE('2017-09-05 18', '%Y-%m-%d %H'), 1, now(), now());    


select * from seat where id not in 
	(select seat_id from reservation where starting_time >= str_to_date('2017-09-05 09', '%Y-%m-%d %H') and ending_time <= str_to_date('2017-09-05 18', '%Y-%m-%d %H'));
