USE apsys;

create table t_user(
                       `id` int primary key auto_increment,
                       `username` varchar(20) not null unique,
                       `password` varchar(32) not null,
                       `email` varchar(200)
);

insert into t_user(`username`,`password`,`email`) values('yyin','123456','1398035515@qq.com');

select * from t_user;