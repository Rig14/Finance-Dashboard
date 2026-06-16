<script lang="ts">
  import {t} from 'i18n'
  import {user} from 'src/stores/auth'
  import api from 'src/api/api'
  import {CountryCode, type GetAspspsResponse, type StartAuthorizationResponse} from 'src/api/types'
  import Spinner from 'src/components/Spinner.svelte'
  import {showToast} from 'src/stores/toasts'

  async function initiateAuth(bankName: string, country: CountryCode) {
    const query = new URLSearchParams({bankName, country})
    const res = await api.get<StartAuthorizationResponse>(`enablebanking/auth?${query}`)
    window.location.href = res.url
  }

  async function refreshTransactions() {
    const res = await api.get<{imported: string, skipped: string}>("transactions/refresh")
    showToast(`${t.dashboard.importComplete}. ${res.imported} ${t.dashboard.newTransactionsAdded}. ${res.skipped} ${t.dashboard.transactionsSkipped}`)
  }
</script>

{#if $user && !$user?.sessionId}
  {t.dashboard.bankNotConnected}

  {#await api.get<GetAspspsResponse>('enablebanking/banks?country=EE')}
    <Spinner/>
  {:then data}
    <ul class="grid grid-cols-3">
      {#each data.aspsps as aspsp}
        <li>
          <button onclick={() => initiateAuth(aspsp.name, aspsp.country as CountryCode)} class="bg-gray-100 cursor-pointer hover:bg-gray-200 rounded-lg p-2 flex flex-col items-center justify-center w-full">
            <img class="h-7" src={aspsp.logo} alt={aspsp.name}>
            {aspsp.name}
          </button>
        </li>
      {/each}
    </ul>
  {/await}
{:else}
  <button onclick={refreshTransactions}>button</button>
{/if}

