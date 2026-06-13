--changeset transactions
create table transactions(
  ${id},
  amount decimal not null,
  creditor text not null,
  creditDebitIndicator text not null check (creditDebitIndicator in ('CRDT', 'DBIT')),
  date date not null,
  categoryCode text,
  note text,
  userId bigint not null
);

--changeset transactions.userId
alter table transactions add foreign key (userId) references users(id) on delete cascade;

