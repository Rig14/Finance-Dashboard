--changeset transactions
create table transactions(
  ${id},
  amount decimal not null,
  accountId uuid not null,
  userId bigint not null,
  hashCode int not null,
  creditDebitIndicator text not null check (creditDebitIndicator in ('CRDT', 'DBIT')),
  date date,
  creditor text,
  categoryCode text,
  note text,
  foreign key (userId) references users(id) on delete cascade,
  constraint uq_transactions_composite unique (accountId, userId, hashCode)
);



