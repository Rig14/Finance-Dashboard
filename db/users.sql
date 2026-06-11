--changeset users
create table users(
  ${id},
  email text unique,
  secretHash text not null,
  sessionId uuid
);
