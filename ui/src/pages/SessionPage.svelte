<script lang="ts">
  import {onMount} from 'svelte'
  import api from 'src/api/api'
  import type {AuthorizeSessionResponse} from 'src/api/types'
  import {navigate} from '@keksworks/svelte-tiny-router'

  onMount(async () => {
    const urlParams = new URLSearchParams(window.location.search)
    const code = urlParams.get('code')

    if (code) {
      await api.get<AuthorizeSessionResponse>(`session?code=${code}`)
    }

    navigate('/dashboard')
  })
</script>
