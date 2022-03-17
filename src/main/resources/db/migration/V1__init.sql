CREATE TABLE app_users (
  user_id bigint(20) NOT NULL AUTO_INCREMENT,
  username varchar(100) NOT NULL,
  password varchar(100) NOT NULL,
  active int not null default 0,
  created_date timestamp not null default now(),
  updated_date timestamp not null default now() on update now(),
  PRIMARY KEY (user_id),
  UNIQUE KEY UK_name (username)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
