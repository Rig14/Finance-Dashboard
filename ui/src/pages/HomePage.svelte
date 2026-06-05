<script lang="ts">
  import {t} from 'i18n'
  import api from 'src/api/api'
  import type {AuthorizationResponse} from 'src/api/types'

  async function onclick() {
    const res = await api.get<AuthorizationResponse>("enable-banking/auth")
    window.location.href = res.url
  }
</script>

<main class="text-center pt-10">
  <button {onclick}>
    {t.general.authenticate}
  </button>
</main>
