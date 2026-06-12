<script lang="ts">
  import {onMount} from 'svelte'
  import api from 'src/api/api'
  import type {AuthorizeSessionResponse, User} from 'src/api/types'
  import {navigate} from '@keksworks/svelte-tiny-router'
  import {initSession} from 'src/stores/auth'

  onMount(async () => {
    const urlParams = new URLSearchParams(window.location.search)
    const code = urlParams.get('code')

    if (code) {
      await api.get<AuthorizeSessionResponse>(`enablebanking/session?code=${code}`)
      const user = await api.get<User>('users/user')
      initSession(user)
    }

    navigate('/dashboard')
  })
</script>
