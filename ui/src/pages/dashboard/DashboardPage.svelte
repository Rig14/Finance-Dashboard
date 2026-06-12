<script lang="ts">
  import {t} from 'i18n'
  import {user} from 'src/stores/auth'
  import api from 'src/api/api'
  import type {GetAspspsResponse} from 'src/api/types'
</script>

{#if !$user?.sessionId}
  {t.dashboard.bankNotConnected}
  {#await api.get<GetAspspsResponse>('enablebanking/banks?countryCode=EE')}

  {:then data}
    <ul class="grid grid-cols-3">
      {#each data.aspsps as aspsp}
        <li>
          <button class="bg-gray-100 hover:bg-gray-200 rounded-lg p-2 flex flex-col items-center justify-center w-full">
            <img class="h-7" src={aspsp.logo} alt={aspsp.name}>
            {aspsp.name}
          </button>
        </li>
      {/each}
    </ul>
  {/await}
{/if}

