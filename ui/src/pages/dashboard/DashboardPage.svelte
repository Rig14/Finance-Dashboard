<script lang="ts">
  import {t} from 'i18n'
  import {user} from 'src/stores/auth'
</script>

{#if !$user?.sessionId}
  {t.dashboard.bankNotConnected}
  <button>{t.dashboard.authorizeBank}</button>
{/if}

