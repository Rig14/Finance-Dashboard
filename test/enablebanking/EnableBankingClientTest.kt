package enablebanking

import db.TestData.enableBankingTransaction
import enablebanking.TransactionStatus.SCHD
import io.mockk.coEvery
import io.mockk.mockk
import klite.json.JsonHttpClient
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate.now
import java.util.UUID.randomUUID

class EnableBankingClientTest {
  val jsonHttpClient = mockk<JsonHttpClient>()
  val client = EnableBankingClient(mockk()).apply {
    val field = EnableBankingClient::class.java.getDeclaredField("http")
    field.isAccessible = true
    field.set(this, jsonHttpClient)
  }

  @Test
  fun `transactions fetches all pages and returns combined list`() {
    val accountId = randomUUID()
    val from = now().minusMonths(1)
    val to = now()
    val tx1 = enableBankingTransaction
    val tx2 = enableBankingTransaction.copy(status = SCHD)

    val page1 = HalTransactions(transactions = listOf(tx1), continuationKey = "next_page_key")
    val page2 = HalTransactions(transactions = listOf(tx2), continuationKey = null)

    coEvery { jsonHttpClient.get<HalTransactions>("/accounts/$accountId/transactions?date_from=$from&date_to=$to") } returns page1
    coEvery { jsonHttpClient.get<HalTransactions>("/accounts/$accountId/transactions?continuation_key=next_page_key&date_from=$from&date_to=$to") } returns page2

    val result = runBlocking { client.transactions(accountId, from..to) }

    assertEquals(2, result.size)
    assertEquals(tx1, result[0])
    assertEquals(tx2, result[1])
  }
}
