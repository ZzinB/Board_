create table tbl_todo(
    tno int auto_increment primary key ,
    title varchar(100) not null ,
    dueDate date not null ,
    writer varchar(50) not null ,
    finished tinyint default 0
);