package transactions

import db.CrudRepository
import javax.sql.DataSource

class TransactionsRepository(
  db: DataSource,
): CrudRepository<Transaction>(db, "transactions")
