/* INTEREST 테이블 */


insert into INTEREST values ('I-001', 'food');
insert into INTEREST values ('I-002', 'entertain');
insert into INTEREST values ('I-003', 'experience');
insert into INTEREST values ('I-004', 'exercise');
insert into INTEREST values ('I-005', 'show');
insert into INTEREST values ('I-006', 'all');



/* LOCATION */

insert into LOCATION (INT_CODE, NAME, PHOTO, LAT, LNG)
values('I-001', '본차로드', 'default.png', 127.0276533, 37.4944045);

insert into LOCATION (INT_CODE, NAME, PHOTO, LAT, LNG)
values('I-005', '롯데시네마', 'default.png', 127.0274215, 37.4945622 );

insert into LOCATION (INT_CODE, NAME, PHOTO, LAT, LNG)
values('I-002', '노래방', 'default.png', 127.0270927, 37.4952234);


/* MEMBER */

insert into MEMBER (ID, PWD, NAME, EMAIL, GENDER, AGE)
values('a001', '1111', '권민수', 'a001@test.com', 'female', 23);

insert into MEMBER (ID, PWD, NAME, EMAIL, GENDER, AGE)
values('a002', '1111', '김응철', 'a002@test.com', 'male', 26);

insert into MEMBER (ID, PWD, NAME, EMAIL, GENDER, AGE)
values('a003', '1111', '황유정', 'a003@test.com', 'female', 23);

insert into MEMBER (ID, PWD, NAME, EMAIL, GENDER, AGE)
values('a004', '1111', '김종원', 'a004@test.com', 'male', 25);


/* MEM_INT 멤버 관심사*/

insert into MEM_INT values('a001', 'I-001');
insert into MEM_INT values('a001', 'I-003');
insert into MEM_INT values('a001', 'I-005');
insert into MEM_INT values('a002', 'I-002');
insert into MEM_INT values('a002', 'I-004');
insert into MEM_INT values('a002', 'I-005');
insert into MEM_INT values('a003', 'I-001');
insert into MEM_INT values('a003', 'I-002');


/* BOARD */


insert into BOARD (ID, TITLE, CONTENT, VIEWS, CRE_DT, LIKES, DISLIKES, HEART, ICON)
values ('a001', '강남역에갔어', '그랬는데...', 0, now(), 0, 0, 'N', 'default.png');

insert into BOARD (ID, TITLE, CONTENT, VIEWS, CRE_DT, LIKES, DISLIKES, HEART, ICON)
values ('a002', '길동역에갔어', '그랬는데...', 0, now(), 0, 0, 'N', 'default.png');

insert into BOARD (ID, TITLE, CONTENT, VIEWS, CRE_DT, LIKES, DISLIKES, HEART, ICON)
values ('a002', '금천구청역에갔어', '그랬는데...', 0, now(), 0, 0, 'N', 'default.png');

insert into BOARD (ID, TITLE, CONTENT, VIEWS, CRE_DT, LIKES, DISLIKES, HEART, ICON)
values ('a003', '역촌역에갔어', '그랬는데...', 0, now(), 0, 0, 'N', 'default.png');


/* COUR_LOC */

insert into COUR_LOC values(1,1);
insert into COUR_LOC values(1,2);
insert into COUR_LOC values(2,2);
insert into COUR_LOC values(2,3);


/* COMMENT */


insert into comment(BNO, ID, CONTENT, CRE_DT)
values (1, 'a001', '아 진짜 재밌었..', now());

insert into comment(BNO, ID, CONTENT, CRE_DT)
values (1, 'a002', '부러워요', now());

insert into comment(BNO, ID, CONTENT, CRE_DT)
values (2, 'a002', '역시', now());




/* PHOTO */

insert into PHOTO (BNO, PHOTO)
values(1, 'default.png');

insert into PHOTO (BNO, PHOTO)
values(2, 'default.png');

insert into PHOTO (BNO, PHOTO)
values(3, 'default.png');

insert into PHOTO (BNO, PHOTO)
values(3, 'default2.png');

insert into PHOTO (BNO, PHOTO)
values(3, 'default3.png');

alter table board add column PHOTO varchar(100);



